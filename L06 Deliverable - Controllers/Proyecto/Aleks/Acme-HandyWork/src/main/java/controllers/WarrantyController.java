
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

import services.WarrantyService;
import domain.Warranty;

@Controller
@RequestMapping("/warranty/administrator")
public class WarrantyController extends AbstractController {

	@Autowired
	private WarrantyService	serviceWarranty;


	public WarrantyController() {
		super();
	}

	//Listing all the warranties
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("warranty/list");
		result.addObject("warranties", this.serviceWarranty.findAll());
		result.addObject("requestURI", "warranty/administrator/list.do");
		return result;
	}
	//Creating a warranty

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		result = this.createEditModelAndView(this.serviceWarranty.createWarranty());
		result.addObject("requestURI", "warranty/administrator/create.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Warranty warranty, final BindingResult binding) {
		ModelAndView model;

		if (binding.hasErrors())
			model = this.createEditModelAndView(warranty);
		else
			try {
				this.serviceWarranty.save(warranty);
				model = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(warranty, "warranty.commit.error");
			}

		return model;
	}

	//Updating a warranty
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		Warranty aux;
		aux = this.serviceWarranty.findOne(id);
		Assert.notNull(aux);
		result = this.createEditModelAndView(aux);
		return result;
	}
	//Delete a warranty
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Warranty warranty) {
		ModelAndView result;

		try {
			this.serviceWarranty.deleteWarranty(warranty.getId());
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(warranty, "warranty.commit.error");
		}

		return result;
	}
	// Create edit model and view
	protected ModelAndView createEditModelAndView(final Warranty warranty) {
		ModelAndView result;
		result = this.createEditModelAndView(warranty, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Warranty warranty, final String message) {
		ModelAndView result;
		result = new ModelAndView("warranty/edit");
		result.addObject("warranty", warranty);
		result.addObject("message", message);
		return result;
	}
}
