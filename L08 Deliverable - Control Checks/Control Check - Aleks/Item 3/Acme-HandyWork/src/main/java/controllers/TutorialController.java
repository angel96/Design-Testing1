
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import services.TutorialService;
import utilities.Utiles;
import domain.Tutorial;

@Controller
@RequestMapping(value = {
	"/tutorial/handyworker", "/tutorial/sponsor", "/tutorial"
})
public class TutorialController extends AbstractController {

	@Autowired
	private TutorialService	serviceTutorial;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		result = new ModelAndView("tutorial/list");
		result.addObject("tutorials", this.serviceTutorial.findAll());
		result.addObject("requestURI", "tutorial/list.do");
		return result;
	}

	@RequestMapping(value = "/listhandy", method = RequestMethod.GET)
	public ModelAndView listByHandyWorker() {
		ModelAndView result;
		result = new ModelAndView("tutorial/list");
		result.addObject("tutorials", this.serviceTutorial.findAllHandyWorkerlog());
		result.addObject("requestURI", "tutorial/listhandy.do");
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		result = this.createEditModelAndView(this.serviceTutorial.createTutorial());
		result.addObject("requestURI", "tutorial/handyworker/edit.do");
		result.addObject("view", false);
		return result;
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		ModelAndView result;
		final Tutorial t = this.serviceTutorial.findOne(id);
		result = this.createEditModelAndView(t);

		result.addObject("requestURI", "tutorial/edit.do");
		result.addObject("sponsorships", t.getSponsorship());
		result.addObject("view", true);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		result = this.createEditModelAndView(this.serviceTutorial.findOne(id));

		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.SPONSOR)) {
			result.addObject("requestURI", "tutorial/sponsor/edit.do");
			result.addObject("view", true);
		} else if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER))
			result.addObject("requestURI", "tutorial/handyworker/edit.do");

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(tutorial);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				this.serviceTutorial.save(tutorial);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(tutorial, "tutorial.commit.error");
				result.addObject("oops", oops.getMessage());
				result.addObject("errors", binding.getAllErrors());
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView edit(final Tutorial tutorial) {
		ModelAndView result;

		try {
			this.serviceTutorial.delete(tutorial.getId());
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(tutorial, "tutorial.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Tutorial tutorial) {
		ModelAndView result;
		result = this.createEditModelAndView(tutorial, null);
		return result;
	}
	protected ModelAndView createEditModelAndView(final Tutorial tutorial, final String message) {
		ModelAndView result;
		result = new ModelAndView("tutorial/edit");
		result.addObject("tutorial", tutorial);
		result.addObject("message", message);
		return result;
	}
}
