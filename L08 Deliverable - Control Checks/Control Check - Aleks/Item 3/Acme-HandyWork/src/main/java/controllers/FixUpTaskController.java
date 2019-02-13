
package controllers;

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

import security.Authority;
import security.LoginService;
import services.CategoryService;
import services.FinderService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.WageService;
import services.WarrantyService;
import utilities.Utiles;
import domain.Finder;
import domain.FixUpTask;

@Controller
@RequestMapping(value = {
	"/fixuptask/handyworker", "/fixuptask/customer", "/fixuptask/referee"
})
public class FixUpTaskController extends AbstractController {

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

	@Autowired
	private WageService			wageService;


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
	@RequestMapping(value = "/searchList", method = RequestMethod.GET)
	public ModelAndView listFinder(@RequestParam final int id) {
		ModelAndView result;
		result = new ModelAndView("fixuptask/list");
		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER)) {
			final Finder f = this.serviceFinder.findOne(id);
			result.addObject("fixuptasks", f.getFixUpTask());
			result.addObject("requestURI", "fixuptask/handyworker/searchList.do?id=" + f.getId());
			result.addObject("URI", "fixuptask/handyworker/");
		}
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@CookieValue(value = "language", required = false) final String lang, @RequestParam(defaultValue = "false") final String view) {
		ModelAndView result;
		result = new ModelAndView("fixuptask/edit");
		result.addObject("fixUpTask", this.fixUpService.createFixUpTask());
		if (lang == null)
			result.addObject("lang", "en");
		else
			result.addObject("lang", lang);
		result.addObject("view", view);
		result.addObject("categories", this.serviceCategory.findAll());
		result.addObject("warranties", this.serviceWarranty.findAllFinalWarranties());
		result.addObject("requestURI", "fixuptask/customer/create.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final FixUpTask fixUpTask, final BindingResult bind, @CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		if (bind.hasErrors()) {
			result = this.createEditModelAndView(fixUpTask);
			result.addObject("errors", bind.getAllErrors());
			if (lang == null)
				result.addObject("lang", "en");
			else
				result.addObject("lang", lang);
		} else
			try {
				this.fixUpService.save(fixUpTask);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(fixUpTask, "fixuptask.commit.error");
				result.addObject("oops", oops.getMessage());
				result.addObject("errors", bind.getAllErrors());
			}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id, @CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		FixUpTask fut;
		fut = this.fixUpService.findOne(id);
		Assert.notNull(fut);
		result = this.createEditModelAndView(fut);
		if (lang == null)
			result.addObject("lang", "en");
		else
			result.addObject("lang", lang);
		return result;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewFixUpTask(@RequestParam final int id, @RequestParam(defaultValue = "false") final String view, @CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		FixUpTask find;
		boolean res;
		if (this.fixUpService.getAcceptedAppsByFixUp(id) == null)
			res = true;
		else
			res = false;

		find = this.fixUpService.findOne(id);
		result = this.createEditModelAndView(find);
		result.addObject("view", view);
		if (lang == null)
			result.addObject("lang", "en");
		else
			result.addObject("lang", lang);
		result.addObject("res", res);
		result.addObject("wages", this.wageService.getAllByFixUp(id));
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final FixUpTask fixUpTask) {
		ModelAndView result;

		try {
			this.fixUpService.delete(fixUpTask.getId());
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(fixUpTask, "fixUpTask.commit.error");
		}

		return result;
	}

	//FINDER
	@RequestMapping(value = "/finder", method = RequestMethod.GET)
	public ModelAndView finderForm(@CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		result = this.editAndCreateFinderModelAndView(this.serviceHandyWorker.findByUserAccount(LoginService.getPrincipal().getId()).getFinder());
		if (lang == null)
			result.addObject("lang", "en");
		else
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
				result.addObject("requestURI", "fixuptask/handyworker/searchList.do?id=" + aux.getId());
				result.addObject("fixuptasks", this.fixUpService.findAllByFinder(aux));
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

	protected ModelAndView createEditModelAndView(final FixUpTask fixUpTask) {
		ModelAndView result;
		result = this.createEditModelAndView(fixUpTask, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final FixUpTask fixUpTask, final String message) {
		ModelAndView result;
		result = new ModelAndView("fixuptask/edit");
		result.addObject("fixUpTask", fixUpTask);
		result.addObject("categories", this.serviceCategory.findAll());
		result.addObject("warranties", this.serviceWarranty.findAllFinalWarranties());
		result.addObject("message", message);
		return result;
	}

}
