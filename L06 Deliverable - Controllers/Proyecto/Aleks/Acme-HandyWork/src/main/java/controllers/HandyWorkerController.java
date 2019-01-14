
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
import domain.HandyWorker;

@Controller
@RequestMapping("/handyworker")
public class HandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService	serviceHandyWorker;


	// Constructors -----------------------------------------------------------

	public HandyWorkerController() {
		super();
	}
	//Create HandyWorker
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView model;

		model = this.createEditModelAndView(this.serviceHandyWorker.createHandyWorker());

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

		find = this.serviceHandyWorker.findByUserAccount(LoginService.getPrincipal().getId());

		result = this.createEditModelAndView(find);

		return result;
	}
	// Create edit model and view
	protected ModelAndView createEditModelAndView(final HandyWorker handyworker) {
		ModelAndView model;
		model = this.createEditModelAndView(handyworker, null);
		return model;
	}

	protected ModelAndView createEditModelAndView(final HandyWorker handyworker, final String message) {
		ModelAndView result;
		result = new ModelAndView("handyworker/edit");

		result.addObject("handyWorker", handyworker);
		result.addObject("message", message);
		return result;
	}
}
