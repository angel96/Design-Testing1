
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import utilities.Utiles;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryController extends AbstractController {

	@Autowired
	private CategoryService	serviceCategory;


	public CategoryController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		result = new ModelAndView("category/list");
		result.addObject("categories", this.serviceCategory.findAll());
		result.addObject("requestURI", "category/administrator/list.do");
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(defaultValue = "0") final int parent) {
		ModelAndView result;

		result = new ModelAndView("category/edit");
		result.addObject("category", Utiles.createCategory());
		result.addObject("parent", parent);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@RequestParam final int parent, final Category category, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(category);
		else
			try {
				if (parent == 0)
					this.serviceCategory.saveParent(category);
				else
					this.serviceCategory.saveSubCategory(parent, category);

				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(category, "category.commit.error");
			}
		return result;
	}

	//Delete a category
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Category category) {
		ModelAndView result;

		try {
			this.serviceCategory.deleteCategory(category.getId());
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(category, "category.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category) {
		ModelAndView result;
		result = this.createEditModelAndView(category, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(final Category category, final String message) {
		ModelAndView result;

		result = new ModelAndView("category/edit");
		result.addObject("category", category);
		result.addObject("message", message);

		return result;
	}
}