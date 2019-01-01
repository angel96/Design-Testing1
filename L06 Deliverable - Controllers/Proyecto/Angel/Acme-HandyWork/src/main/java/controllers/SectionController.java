
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SectionService;
import domain.Section;

@Controller
@RequestMapping("")
public class SectionController extends AbstractController {

	@Autowired
	private SectionService	serviceSection;


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
	public ModelAndView submit(@Valid final Section section, final BindingResult binding) {
		return null;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView edit(final Section section) {
		return null;
	}

	protected ModelAndView editAndCreateSectionModelAndView(final Section section) {
		return null;
	}
	protected ModelAndView editAndCreateSectionModelAndView(final Section section, final String message) {
		return null;
	}
}
