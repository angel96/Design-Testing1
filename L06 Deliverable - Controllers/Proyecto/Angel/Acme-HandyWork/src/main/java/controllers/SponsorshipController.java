
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
import services.SponsorshipService;
import services.TutorialService;
import utilities.Utiles;
import domain.Sponsor;
import domain.Sponsorship;

@Controller
@RequestMapping(value = {
	"/sponsorship/sponsor", "/sponsorship/handyworker"
})
public class SponsorshipController extends AbstractController {

	@Autowired
	private SponsorshipService	serviceSponsorship;

	@Autowired
	private TutorialService		serviceTutorial;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0") final int tutorial) {
		ModelAndView result;
		result = new ModelAndView("sponsorship/list");
		if (tutorial != 0)
			result.addObject("sponsorships", this.serviceTutorial.findOne(tutorial).getSponsorship());
		else
			result.addObject("sponsorships", ((Sponsor) this.serviceTutorial.findActorByUserAccount(LoginService.getPrincipal().getId())).getSponsorship());
		result.addObject("requestURI", "sponsorship/**/list.do");
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tutorial) {
		ModelAndView result;

		result = this.createEditModelAndView(this.serviceSponsorship.createSponsorship((Sponsor) this.serviceTutorial.findActorByUserAccount(LoginService.getPrincipal().getId()), this.serviceTutorial.findOne(tutorial)));
		result.addObject("requestURI", "sponsorship/sponsor/edit.do");
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		result = this.createEditModelAndView(this.serviceSponsorship.findOne(id));
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Sponsorship sponsorship, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(sponsorship);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				if (Boolean.valueOf(Utiles.checkCreditCard(sponsorship.getCreditCard().getNumber())[1])) {
					sponsorship.getCreditCard().setType(Utiles.checkCreditCard(sponsorship.getCreditCard().getNumber())[0]);
					this.serviceSponsorship.save(sponsorship);
					result = new ModelAndView("redirect:list.do");
				} else
					result = this.createEditModelAndView(sponsorship, "sponsorship.error.creditcard");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(sponsorship, "sponsorship.commit.error");
				result.addObject("errors", binding.getAllErrors());
				result.addObject("oops", oops.getMessage());
			}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView edit(final Sponsorship sponsorship) {
		ModelAndView result;

		try {
			this.serviceSponsorship.delete(sponsorship.getId());
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(sponsorship, "sponsorship.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsorship sponsorship) {
		ModelAndView result;
		result = this.createEditModelAndView(sponsorship, null);
		return result;
	}
	protected ModelAndView createEditModelAndView(final Sponsorship sponsorship, final String message) {
		ModelAndView result;
		result = new ModelAndView("sponsorship/edit");
		result.addObject("sponsorship", sponsorship);
		result.addObject("message", message);
		return result;
	}
}
