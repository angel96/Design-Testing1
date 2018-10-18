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

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ShoutService;
import domain.Shout;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Constructors -----------------------------------------------------------
	@Autowired
	private ShoutService	shoutService;


	public CustomerController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value = "/user-shouts", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Collection<Shout> shouts;
		shouts = this.shoutService.findAll();
		result = new ModelAndView("customer/user-shouts");
		result.addObject("shouts", shouts);
		return result;
	}

	// Action-2 ---------------------------------------------------------------		

	@RequestMapping(value = "/enter-a-shout", method = RequestMethod.GET)
	public ModelAndView action2() {
		ModelAndView result;
		Shout shout;
		shout = this.shoutService.create();
		result = new ModelAndView("customer/enter-a-shout");
		result.addObject("shout", shout);
		return result;
	}
	@RequestMapping(value = "/enter-a-shout", method = RequestMethod.POST)
	public ModelAndView action2Post(@Valid final Shout shout, final BindingResult binding) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			this.shoutService.save(shout);
			result = new ModelAndView("redirect:user-shouts.do");
		} else {
			result = new ModelAndView("customer/enter-a-shout");
			result.addObject("shout", shout);
		}

		return result;
	}
}
