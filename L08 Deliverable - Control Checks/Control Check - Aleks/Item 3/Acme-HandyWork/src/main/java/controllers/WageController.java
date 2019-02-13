
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.FixUpTaskService;
import services.WageService;
import domain.FixUpTask;
import domain.Wage;

@Controller
@RequestMapping(value = {
	"/wage/customer", "/wage/handyworker"
})
public class WageController extends AbstractController {

	@Autowired
	WageService			wageService;
	@Autowired
	FixUpTaskService	fixUpService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		Collection<Wage> w;
		result = new ModelAndView("wage/list");
		if (lang == null)
			result.addObject("lang", "en");
		else
			result.addObject("lang", lang);

		w = this.wageService.getAllByUser(LoginService.getPrincipal().getId());
		Assert.notNull(w);
		result.addObject("wages", w);
		result.addObject("requestURI", "wage/customer/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(defaultValue = "false") final String view, @RequestParam final int fixId) {

		ModelAndView model;
		model = new ModelAndView("wage/edit");

		model.addObject("wage", this.wageService.create());
		model.addObject("view", view);
		model.addObject("fixId", fixId);
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Wage wage, final BindingResult bind, @RequestParam final int fixId) {
		ModelAndView result;

		if (bind.hasErrors()) {
			result = this.createEditModelAndView(wage);
			result.addObject("errors", bind.getAllErrors());
		} else
			try {
				FixUpTask f;
				f = this.fixUpService.findOne(fixId);
				Assert.notNull(f);
				this.wageService.save(wage, f);
				result = new ModelAndView("redirect:/wage/customer/list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(wage, "wage.commit.error");
				result.addObject("oops", oops.getMessage());
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		Wage w;
		FixUpTask f;
		w = this.wageService.findOne(id);
		f = this.fixUpService.getFixUpTaskByWage(id);
		Assert.notNull(w);
		Assert.isTrue(this.wageService.getAllByUser(LoginService.getPrincipal().getId()).contains(this.wageService.findOne(id)));
		result = this.createEditModelAndView(w);
		result.addObject("fixId", f.getId());

		return result;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewQuolet(@RequestParam final int id, @RequestParam(defaultValue = "false") final String view, @CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		Wage w;
		FixUpTask f;
		f = this.fixUpService.getFixUpTaskByWage(id);
		w = this.wageService.findOne(id);
		result = this.createEditModelAndView(w);
		result.addObject("view", view);
		result.addObject("fixId", f.getId());

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Wage wage) {
		ModelAndView result;

		try {
			this.wageService.delete(wage.getId());
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(wage, "wage.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Wage wage) {
		ModelAndView result;
		result = this.createEditModelAndView(wage, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Wage wage, final String message) {
		ModelAndView result;
		result = new ModelAndView("wage/edit");
		result.addObject("wage", wage);
		result.addObject("message", message);
		return result;
	}

}
