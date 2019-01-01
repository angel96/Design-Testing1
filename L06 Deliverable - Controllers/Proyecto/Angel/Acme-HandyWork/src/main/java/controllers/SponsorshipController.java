
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorshipService;
import domain.Sponsorship;

@Controller
@RequestMapping("")
public class SponsorshipController extends AbstractController {

	@Autowired
	private SponsorshipService	serviceSponsorship;


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
	public ModelAndView submit(@Valid final Sponsorship sponsorship, final BindingResult binding) {
		return null;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView edit(final Sponsorship sponsorship) {
		return null;
	}

	protected ModelAndView editAndCreateSponsorshipModelAndView(final Sponsorship sponsorship) {
		return null;
	}
	protected ModelAndView editAndCreateSponsorshipModelAndView(final Sponsorship sponsorship, final String message) {
		return null;
	}
}
