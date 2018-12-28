
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomisationSystemService;
import domain.CustomisationSystem;

@Controller
@RequestMapping("/customisation/administrator")
public class CustomisationSystemController {

	@Autowired
	private CustomisationSystemService	serviceCustom;


	@RequestMapping(value = "/custom", method = RequestMethod.GET)
	public ModelAndView custom() {

		ModelAndView result;

		result = new ModelAndView("custom/edit");

		result.addObject("customisationsystem", this.serviceCustom.findUnique());

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final CustomisationSystem customisationsystem, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.editAndCreateModelAndView(customisationsystem);
		else
			try {
				this.serviceCustom.save(customisationsystem);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.editAndCreateModelAndView(customisationsystem, "custom.commit.error");
			}

		return result;
	}
	protected ModelAndView editAndCreateModelAndView(final CustomisationSystem customisationsystem) {

		ModelAndView result;
		result = this.editAndCreateModelAndView(customisationsystem, null);
		return result;
	}
	protected ModelAndView editAndCreateModelAndView(final CustomisationSystem customisationsystem, final String message) {

		ModelAndView result;
		result = new ModelAndView("custom/edit");
		result.addObject("customisationsystem", customisationsystem);
		result.addObject("message", message);
		return result;
	}
}
