
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.CategoryService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.WarrantyService;
import domain.Finder;
import domain.FixUpTask;

@Controller
@RequestMapping(value = {
	"/fixuptask/handyworker", "/fixuptask/customer"
})
public class FixUpTaskController {

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
		result.addObject("requestURI", "fixptask/customer/list.do");
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
}
