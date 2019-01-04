
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.HandyWorkerService;
import utilities.Utiles;
import domain.HandyWorker;

@Controller
@RequestMapping("/handyWorker")
public class HandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService	serviceHandyWorker;


	// Constructors -----------------------------------------------------------

	public HandyWorkerController() {
		super();
	}
	//Create HandyWorker
	@RequestMapping(value = "/createHandy", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView model;

		model = this.createEditModelAndView(Utiles.createHandyWorker());
		System.out.println(Utiles.createHandyWorker());

		return model;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final HandyWorker handyWorker, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(handyWorker);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				System.out.println(handyWorker);
				this.serviceHandyWorker.save(handyWorker);

				result = new ModelAndView("redirect:../welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(handyWorker, "handy.commit.error");
				result.addObject("oops", oops.getMessage());
				result.addObject("errors", binding.getAllErrors());
			}
		return result;

	}
	// Update personal data
	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public ModelAndView editPersonalData() {
		ModelAndView result;

		HandyWorker find;
		System.out.println(LoginService.getPrincipal().getId());
		find = this.serviceHandyWorker.findByUserAccount(LoginService.getPrincipal().getId());
		System.out.println("El handy:" + find);
		result = this.createEditModelAndView(find);
		System.out.println("despues del cratemodel and view: " + result);
		return result;
	}
	// Create edit model and view
	protected ModelAndView createEditModelAndView(final HandyWorker handyWorker) {
		ModelAndView model;
		model = this.createEditModelAndView(handyWorker, null);
		return model;
	}

	protected ModelAndView createEditModelAndView(final HandyWorker handyWorker, final String message) {
		ModelAndView result;
		result = new ModelAndView("handyWorker/edit");
		result.addObject("handyWorker", handyWorker);
		result.addObject("message", message);
		return result;
	}
}
