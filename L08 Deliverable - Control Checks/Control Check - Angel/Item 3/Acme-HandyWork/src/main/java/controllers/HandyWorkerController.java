
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
import services.HandyWorkerService;
import utilities.Utiles;
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
		model.addObject("view", false);
		model.addObject("prefix", Utiles.phonePrefix);
		return model;

	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam(defaultValue = "false") final boolean check, @RequestParam final int tutorial) {
		ModelAndView result;

		result = new ModelAndView("handyworker/edit");
		result.addObject("view", true);
		final HandyWorker hw = this.serviceHandyWorker.findHandyWorkerByTutorial(check, tutorial);

		result.addObject("handyWorker", hw);

		return result;
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
		result.addObject("view", false);
		result.addObject("prefix", Utiles.phonePrefix);
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
		result = new ModelAndView("handyworker/edit");

		result.addObject("handyWorker", handyWorker);
		result.addObject("message", message);
		if (handyWorker.getId() == 0)
			result.addObject("view", false);

		return result;
	}
}
