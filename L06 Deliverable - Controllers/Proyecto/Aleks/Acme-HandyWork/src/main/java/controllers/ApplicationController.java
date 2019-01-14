
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import services.ApplicationService;
import services.FixUpTaskService;
import utilities.Utiles;
import domain.Application;
import domain.FixUpTask;

@Controller
@RequestMapping(value = {
	"application/customer", "application/handyworker"
})
public class ApplicationController {

	@Autowired
	private ApplicationService	appService;
	@Autowired
	private FixUpTaskService	fixUpService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		result = new ModelAndView("application/list");
		result.addObject("applications", this.appService.getApplicationsByHandyWorker(LoginService.getPrincipal().getId()));
		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER))
			result.addObject("requestURI", "application/customer/list.do");
		else if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER))
			result.addObject("requestURI", "application/handyworker/list.do");
		return result;
	}

	@RequestMapping(value = "/listByFixUp", method = RequestMethod.GET)
	public ModelAndView listByFixUp(@RequestParam final int fixUpId) {
		ModelAndView result;
		result = new ModelAndView("application/list");
		result.addObject("applications", this.appService.getApplicationsByFixUp(LoginService.getPrincipal().getId(), fixUpId));
		result.addObject("requestURI", "application/customer/listByFixUp.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int fixUpId) {
		ModelAndView result;
		FixUpTask fut;
		fut = this.fixUpService.findOne(fixUpId);
		result = new ModelAndView("application/edit");
		result.addObject("application", this.appService.createApplication(fut));
		result.addObject("status", Utiles.status());

		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER))
			result.addObject("requestURI", "application/customer/edit.do");
		else if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER))
			result.addObject("requestURI", "application/handyworker/edit.do");
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Application application, final BindingResult bind) {
		ModelAndView result;
		result = new ModelAndView();
		FixUpTask fut;
		if (bind.hasErrors()) {
			result = this.createEditModelAndView(application);
			result.addObject("errors", bind.getAllErrors());
		} else
			try {
				if (Boolean.valueOf(Utiles.checkCreditCard(application.getCreditCard().getNumber())[1])) {
					fut = this.fixUpService.findOne(application.getFixUpTask().getId());
					this.appService.save(fut, application);
					if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER))
						result = new ModelAndView("redirect:/fixuptask/customer/list.do");
					else if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER))
						result = new ModelAndView("redirect:/fixuptask/handyworker/list.do");
				} else
					result = this.createEditModelAndView(application, "app.commit.tarjeta.error");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(application, "app.commit.error");
				result.addObject("oops", oops.getMessage());
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		Application a;
		a = this.appService.findOne(id);
		Utiles.statusTEMP = a.getStatus();
		Assert.notNull(a);
		result = this.createEditModelAndView(a);
		result.addObject("vat", a.getOfferedPrice() + " with " + Utiles.vat + " = " + Utiles.priceWithVat(a.getOfferedPrice()));
		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;
		result = this.createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String message) {
		ModelAndView result;
		result = new ModelAndView("application/edit");
		result.addObject("types", Utiles.cards());
		result.addObject("application", application);
		result.addObject("status", Utiles.status());
		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER)) {
			result.addObject("requestURI", "application/customer/edit.do");
			result.addObject("URI", "/fixuptask/customer/list.do");
		} else if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER)) {
			result.addObject("requestURI", "application/handyworker/edit.do");
			result.addObject("URI", "application/handyworker/list.do");
		}
		result.addObject("message", message);
		return result;
	}

}
