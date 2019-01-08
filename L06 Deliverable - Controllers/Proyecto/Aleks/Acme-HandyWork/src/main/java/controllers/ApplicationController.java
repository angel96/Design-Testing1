
package controllers;

import java.util.Arrays;

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
		result.addObject("application", Utiles.createApplication(fut));
		result.addObject("status", Arrays.asList("pending", "accepted", "rejected"));
		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER))
			result.addObject("requestURI", "application/customer/edit.do");
		else if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER))
			result.addObject("requestURI", "application/handyworker/edit.do");
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Application app, final BindingResult bind) {
		ModelAndView result;
		FixUpTask fut;
		if (bind.hasErrors())
			result = this.createEditModelAndView(app);
		else
			try {
				fut = this.fixUpService.findOne(app.getFixUpTask().getId());
				this.appService.save(fut, app);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(app, "app.commit.error");
			}
		return result;
	}

	/*
	 * @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "addApplication")
	 * public ModelAndView addApp(@Valid final Application app, final BindingResult bind) {
	 * ModelAndView result;
	 * FixUpTask fut;
	 * if (bind.hasErrors())
	 * result = this.createEditModelAndView(app);
	 * else
	 * try {
	 * fut = this.fixUpService.findOne(app.getFixUpTask().getId());
	 * this.appService.save(fut, app);
	 * result = new ModelAndView("redirect:list.do");
	 * } catch (final Throwable oops) {
	 * result = this.createEditModelAndView(app, "fixuptask.commit.error");
	 * }
	 * return result;
	 * }
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		Application a;
		a = this.appService.findOne(id);
		Assert.notNull(a);
		result = this.createEditModelAndView(a);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Application app) {
		ModelAndView result;
		result = this.createEditModelAndView(app, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application app, final String message) {
		ModelAndView result;
		result = new ModelAndView("application/edit");
		result.addObject("application", app);
		result.addObject("status", Arrays.asList("pending", "accepted", "rejected"));
		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER))
			result.addObject("requestURI", "application/customer/edit.do");
		else if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER))
			result.addObject("requestURI", "application/handyworker/edit.do");
		result.addObject("message", message);
		return result;
	}

}
