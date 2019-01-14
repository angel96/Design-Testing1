/*
 * CustomerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.CustomerService;
import domain.Customer;

@Controller
@RequestMapping(value = {
	"/customer", "/customer/handyworker"
})
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerService	customerService;


	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Create ---------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model;

		model = this.createEditModelAndView(this.customerService.createCustomer());

		return model;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int fixup) {
		ModelAndView result;

		result = new ModelAndView("customer/edit");
		result.addObject("view", true);
		result.addObject("customer", this.customerService.findByFixUp(fixup));

		return result;
	}

	// Edit ---------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView model;

		if (binding.hasErrors()) {
			model = this.createEditModelAndView(customer);
			model.addObject("errors", binding.getAllErrors());
		} else
			try {
				this.customerService.save(customer);
				model = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(customer, "customer.commit.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}

	//Personal data ---------------------------------------------------------------		
	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public ModelAndView editPersonalData() {
		ModelAndView model;

		//This method is used for personal data edition

		Customer find;

		find = this.customerService.findByUserAccount(LoginService.getPrincipal().getId());

		model = this.createEditModelAndView(find);

		return model;
	}

	protected ModelAndView createEditModelAndView(final Customer customer) {
		ModelAndView model;
		model = this.createEditModelAndView(customer, null);
		return model;
	}
	protected ModelAndView createEditModelAndView(final Customer customer, final String message) {
		ModelAndView model;
		model = new ModelAndView("customer/edit");
		model.addObject("customer", customer);
		model.addObject("message", message);
		return model;
	}

}
