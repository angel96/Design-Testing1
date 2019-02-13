
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
import security.UserAccount;
import services.SectionService;
import services.TutorialService;
import utilities.Utiles;
import domain.Section;
import domain.Tutorial;

@Controller
@RequestMapping("/section/**")
public class SectionController extends AbstractController {

	@Autowired
	private SectionService	serviceSection;

	@Autowired
	private TutorialService	serviceTutorial;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tutorial) {
		ModelAndView result;
		result = new ModelAndView("section/list");
		final Tutorial t = this.serviceTutorial.findOne(tutorial);
		result.addObject("sections", t.getSection());
		result.addObject("requestURI", "section/list.do");
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tutorial) {
		ModelAndView result;
		result = new ModelAndView("section/edit");
		result.addObject("section", this.serviceSection.createSection());
		result.addObject("requestURI", "section/edit.do?tutorial=" + tutorial);
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		final UserAccount user = LoginService.getPrincipal();
		result = new ModelAndView("section/edit");
		result.addObject("section", this.serviceSection.findOne(id));
		if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER))
			result.addObject("view", false);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@RequestParam final int tutorial, @Valid final Section section, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(section);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				final Tutorial t = this.serviceTutorial.findOne(tutorial);
				this.serviceSection.save(t, section);
				result = new ModelAndView("redirect:list.do?tutorial=" + t.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(section, "section.commit.error");
				result.addObject("errors", binding.getAllErrors());
				result.addObject("oops", oops.getMessage());
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView edit(final Section section) {
		ModelAndView result;
		final Tutorial t = this.serviceSection.findBySection(section.getId());
		try {

			this.serviceSection.delete(t, section);
			result = new ModelAndView("redirect:list.do?tutorial=" + t.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(section, "section.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Section section) {
		ModelAndView result;
		result = this.createEditModelAndView(section, null);
		return result;
	}
	protected ModelAndView createEditModelAndView(final Section section, final String message) {
		ModelAndView result;
		result = new ModelAndView("section/edit");
		result.addObject("section", section);
		result.addObject("message", message);
		if (section.getId() > 0)
			result.addObject("requestURI", "section/edit.do?tutorial=" + this.serviceSection.findBySection(section.getId()));
		return result;
	}
}
