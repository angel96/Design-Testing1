
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import services.CategoryService;
import services.FinderService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.WarrantyService;
import utilities.Utiles;
import domain.Finder;
import domain.FixUpTask;

@Controller
@RequestMapping(value = {
	"/fixuptask/handyworker", "/fixuptask/customer", "/fixuptask/referee"
})
public class FixUpTaskController {// extends AbstractController {

	@Autowired
	private FinderService		serviceFinder;

	@Autowired
	private HandyWorkerService	serviceHandyWorker;
	@Autowired
	private WarrantyService		serviceWarranty;

	@Autowired
	private CategoryService		serviceCategory;

	@Autowired
	private FixUpTaskService	fixUpService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		result = new ModelAndView("fixuptask/list");
		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER)) {
			result.addObject("fixuptasks", this.fixUpService.findAllByUser(LoginService.getPrincipal().getId()));
			result.addObject("requestURI", "fixuptask/customer/list.do");
			result.addObject("URI", "fixuptask/customer/");
		} else if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER)) {
			result.addObject("fixuptasks", this.fixUpService.findAll());
			result.addObject("requestURI", "fixuptask/handyworker/list.do");
			result.addObject("URI", "fixuptask/handyworker/");
		}
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		result = new ModelAndView("fixuptask/edit");
		result.addObject("fixuptask", this.fixUpService.createFixUpTask());
		result.addObject("lang", lang);
		result.addObject("categories", this.serviceCategory.findAll());
		result.addObject("warranties", this.serviceWarranty.findAllFinalWarranties());
		result.addObject("requestURI", "fixuptask/customer/create.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final FixUpTask fixuptask, final BindingResult bind) {
		ModelAndView result;
		if (bind.hasErrors())
			result = this.createEditModelAndView(fixuptask);
		else
			try {
				this.fixUpService.save(fixuptask);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(fixuptask, "fixuptask.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		FixUpTask fut;
		fut = this.fixUpService.findOne(id);
		Assert.notNull(fut);
		result = this.createEditModelAndView(fut);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final FixUpTask fixUp) {
		ModelAndView result;

		try {
			this.fixUpService.delete(fixUp.getId());
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(fixUp, "fixuptask.commit.error");
		}

		return result;
	}

	//FINDER
	@RequestMapping(value = "/finder", method = RequestMethod.GET)
	public ModelAndView finderForm(@CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		result = this.editAndCreateFinderModelAndView(this.serviceHandyWorker.findByUserAccount(LoginService.getPrincipal().getId()).getFinder());
		result.addObject("lang", lang);
		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.editAndCreateFinderModelAndView(finder);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				final Finder aux = this.serviceFinder.save(finder);
				result = new ModelAndView("fixuptask/list");
				result.addObject("fixuptasks", aux.getFixUpTask());
			} catch (final Throwable oops) {
				result = this.editAndCreateFinderModelAndView(finder, "finder.commit.error");
				result.addObject("errors", binding.getAllErrors());
				result.addObject("oops", oops.getMessage());
			}
		return result;
	}

	protected ModelAndView editAndCreateFinderModelAndView(final Finder finder) {
		ModelAndView result;

		result = this.editAndCreateFinderModelAndView(finder, null);

		return result;
	}

	private ModelAndView editAndCreateFinderModelAndView(final Finder finder, final String message) {
		ModelAndView result;
		result = new ModelAndView("fixuptask/find");
		result.addObject("finder", finder);
		result.addObject("message", message);
		result.addObject("warranties", this.serviceWarranty.findAllFinalWarranties());
		result.addObject("categories", this.serviceCategory.findAll());
		return result;
	}

	protected ModelAndView createEditModelAndView(final FixUpTask fixUp) {
		ModelAndView result;
		result = this.createEditModelAndView(fixUp, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final FixUpTask fixUp, final String message) {
		ModelAndView result;
		result = new ModelAndView("fixuptask/edit");
		result.addObject("fixuptask", fixUp);
		result.addObject("categories", this.serviceCategory.findAll());
		result.addObject("warranties", this.serviceWarranty.findAllFinalWarranties());
		result.addObject("message", message);
		return result;
	}

	//A+

	@RequestMapping(value = "/finderAjax", method = RequestMethod.GET)
	public ModelAndView finderFormAjax(@CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		result = new ModelAndView("fixuptask/find-ajax");
		result.addObject("finder", this.serviceHandyWorker.findByUserAccount(LoginService.getPrincipal().getId()).getFinder());
		result.addObject("lang", lang);
		result.addObject("warranties", this.serviceWarranty.findAllFinalWarranties());
		result.addObject("categories", this.serviceCategory.findAll());
		return result;
	}
	@RequestMapping(value = "/searchAjax", method = RequestMethod.POST, params = "search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Collection<FixUpTask> searchAjax(@RequestBody final Finder finder, final BindingResult binding) {
		final Finder aux = this.serviceFinder.save(finder);
		return aux.getFixUpTask();
	}

}
