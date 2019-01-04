
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.CategoryService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.WarrantyService;
import utilities.Utiles;
import domain.Finder;
import domain.FixUpTask;

@Controller
@RequestMapping(value = {
	"/fixuptask/handyworker", "/fixuptask/customer"
})
public class FixUpTaskController extends AbstractController {

	@Autowired
	private FixUpTaskService	serviceFixUpTask;
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
		result.addObject("fixuptasks", this.fixUpService.findAll());
		result.addObject("requestURI", "fixuptask/customer/list.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		result = new ModelAndView("fixuptask/edit");
		result.addObject("fixuptask", Utiles.createFixUpTask());
		result.addObject("categories", this.serviceCategory.findAll());
		result.addObject("warranties", this.serviceWarranty.findAll());
		result.addObject("requestURI", "fixuptask/customer/create.do");
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final FixUpTask fixuptask, final BindingResult bind) {
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
		fut = this.serviceFixUpTask.findOne(id);
		Assert.notNull(fut);
		result = this.createEditModelAndView(fut);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final FixUpTask fixUp) {
		ModelAndView result;

		try {
			this.serviceFixUpTask.delete(fixUp.getId());
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(fixUp, "fixuptask.commit.error");
		}

		return result;
	}

	//FINDER
	@RequestMapping(value = "/finder", method = RequestMethod.GET)
	public ModelAndView finderForm() {
		ModelAndView result;
		result = new ModelAndView("fixuptask/finder");
		result.addObject("finder", this.serviceHandyWorker.findByUserAccount(LoginService.getPrincipal().getId()).getFinder());
		result.addObject("warranties", this.serviceWarranty.findAllFinalWarranties());
		result.addObject("categories", this.serviceCategory.findAll());
		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.editAndCreateFinderModelAndView(finder);
		else
			try {
				final Collection<FixUpTask> fixuptasks = this.serviceFixUpTask.findAllByFinder(finder.getSingleKey(), finder.getStartDate(), finder.getEndDate(), finder.getWarranty(), finder.getCategory(), finder.getPrice1(), finder.getPrice2());
				result = new ModelAndView("fixuptask/list");
				result.addObject("fixuptask", fixuptasks);
			} catch (final Throwable oops) {
				result = this.editAndCreateFinderModelAndView(finder, oops.getMessage());
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
		result = new ModelAndView("fixuptask/finder");
		result.addObject("finder", finder);
		result.addObject("message", message);
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
		result.addObject("message", message);
		return result;
	}
}
