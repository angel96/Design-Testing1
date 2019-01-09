
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
import services.CustomerService;
import services.EndorsementService;
import services.HandyWorkerService;
import utilities.Utiles;
import domain.Actor;
import domain.Customer;
import domain.Endorsable;
import domain.Endorsement;
import domain.HandyWorker;

@Controller
@RequestMapping(value = {
	"/endorsement/customer", "/endorsement/handyworker"
})
public class EndorsementController extends AbstractController {

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private HandyWorkerService	handyService;

	@Autowired
	private EndorsementService	endorsementService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "false") final Boolean own) {
		ModelAndView result;

		final UserAccount user;
		user = LoginService.getPrincipal();

		Actor a;
		a = this.endorsementService.findActorByUserId(user.getId());

		result = new ModelAndView("endorsement/list");
		result.addObject("own", own);

		if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) == true && own == false) {
			result.addObject("actors", this.handyService.findCustomerByHandyWorkerId(a.getId()));
			result.addObject("requestURI", "endorsement/handyworker/list.do");
		} else if (Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) == true && own == false) {
			result.addObject("actors", this.customerService.findHandyByCustomerId(a.getId()));
			result.addObject("requestURI", "endorsement/customer/list.do");
		} else if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) == true && own == true) {
			result.addObject("endorsements", this.endorsementService.findEndorsementsByActorSended((Endorsable) a));
			result.addObject("requestURI", "endorsement/handyworker/list.do");
		} else if (Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) == true && own == true) {
			result.addObject("endorsements", this.endorsementService.findEndorsementsByActorSended((Endorsable) a));
			result.addObject("requestURI", "endorsement/customer/list.do");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int idActor) {
		ModelAndView result;
		UserAccount user;
		user = LoginService.getPrincipal();
		result = new ModelAndView("endorsement/list");
		System.out.println(idActor);

		if (Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) == true) {
			HandyWorker h;
			h = this.handyService.findOne(idActor);
			System.out.println(h.getAccount().getId());
			result = this.createEditModelAndView(this.endorsementService.createEndorsement(h.getAccount().getId()));
			result.addObject("role", "customer");
			result.addObject("idActor", h.getAccount().getId());
			result.addObject("requestURI", "endorsement/customer/edit.do");
		} else if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) == true) {
			Customer c;
			c = this.customerService.findOne(idActor);
			result = this.createEditModelAndView(this.endorsementService.createEndorsement(c.getAccount().getId()));
			result.addObject("role", "handyworker");
			result.addObject("idActor", c.getAccount().getId());
			result.addObject("requestURI", "endorsement/handyworker/edit.do");

		}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Endorsement endorsement, final BindingResult binding, @RequestParam final int idActor) {
		ModelAndView model;
		UserAccount user;
		user = LoginService.getPrincipal();
		System.out.println(idActor);
		if (binding.hasErrors()) {
			model = this.createEditModelAndView(endorsement);
			model.addObject("errors", binding.getAllErrors());
			model.addObject("errores", binding);
		} else
			try {
				model = new ModelAndView("redirect:list.do");
				if (Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) == true) {
					HandyWorker h;
					h = this.handyService.findOne(idActor);
					this.endorsementService.save(endorsement, h.getAccount().getId());
					model = new ModelAndView("redirect:/endorsement/customer/list.do");
					model.addObject("idActor", h.getAccount().getId());
				} else if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) == true) {
					Customer c;
					c = this.customerService.findOne(idActor);
					this.endorsementService.save(endorsement, c.getAccount().getId());
					model = new ModelAndView("redirect:/endorsement/handyworker/list.do");
					model.addObject("idActor", c.getAccount().getId());
				}

			} catch (final Throwable oops) {
				model = this.createEditModelAndView(endorsement, "endorsement.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int idEndorsement) {
		ModelAndView result;
		Endorsement e;
		e = this.endorsementService.findOne(idEndorsement);
		result = this.createEditModelAndView(e);
		result.addObject("idActor", this.endorsementService.findOne(idEndorsement).getUserReceived().getId());
		result.addObject("requestURI", "endorsement/handyworker/edit.do");
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int idEndorsement) {
		ModelAndView result;
		try {
			this.endorsementService.delete(idEndorsement);
			result = new ModelAndView("redirect:list.do?own=true");
		} catch (final Throwable oops) {
			Endorsement e;
			e = this.endorsementService.findOne(idEndorsement);
			result = this.createEditModelAndView(e, "endorsement.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Endorsement endorsement) {
		ModelAndView result;
		result = this.createEditModelAndView(endorsement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Endorsement endorsement, final String message) {
		ModelAndView result;
		result = new ModelAndView("endorsement/edit");
		result.addObject("endorsement", endorsement);
		result.addObject("message", message);
		return result;
	}
}
