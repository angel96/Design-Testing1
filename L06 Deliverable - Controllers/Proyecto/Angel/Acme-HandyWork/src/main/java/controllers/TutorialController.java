
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.TutorialService;
import domain.Tutorial;

@Controller
@RequestMapping(value = {
	"/tutorial/handyworker", "/tutorial/sponsor"
})
public class TutorialController extends AbstractController {

	@Autowired
	private TutorialService	serviceTutorial;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list() {
		return null;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		return null;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		return null;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Tutorial tutorial, final BindingResult binding) {
		return null;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView edit(final Tutorial tutorial) {
		return null;
	}

	protected ModelAndView editAndCreateTutorialModelAndView(final Tutorial tutorial) {
		return null;
	}
	protected ModelAndView editAndCreateTutorialModelAndView(final Tutorial tutorial, final String message) {
		return null;
	}
}
