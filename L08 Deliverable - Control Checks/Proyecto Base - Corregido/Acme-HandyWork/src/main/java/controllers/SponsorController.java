
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.SponsorService;
import utilities.Utiles;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	@Autowired
	private SponsorService	sponsorService;


	// Constructors -----------------------------------------------------------
	public SponsorController() {
		super();
	}
	// Create ---------------------------------------------------------------		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model;

		model = this.createEditModelAndView(this.sponsorService.createSponsor());
		model.addObject("prefix", Utiles.phonePrefix);
		return model;
	}

	// Edit ---------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Sponsor sponsor, final BindingResult binding) {
		ModelAndView model;

		if (binding.hasErrors()) {
			model = this.createEditModelAndView(sponsor);
			model.addObject("errors", binding.getAllErrors());
		} else
			try {
				this.sponsorService.save(sponsor);
				model = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(sponsor, "sponsor.commit.error");
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

		Sponsor find;

		find = this.sponsorService.findByUserAccount(LoginService.getPrincipal().getId());

		model = this.createEditModelAndView(find);
		model.addObject("prefix", Utiles.phonePrefix);
		return model;
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {
		ModelAndView model;
		model = this.createEditModelAndView(sponsor, null);
		return model;
	}
	protected ModelAndView createEditModelAndView(final Sponsor sponsor, final String message) {
		ModelAndView model;
		model = new ModelAndView("sponsor/edit");
		model.addObject("sponsor", sponsor);
		model.addObject("message", message);
		return model;
	}

}
