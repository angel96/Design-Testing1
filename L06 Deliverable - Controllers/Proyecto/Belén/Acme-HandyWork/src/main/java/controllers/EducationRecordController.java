
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.EducationRecordService;
import domain.EducationRecord;

@Controller
@RequestMapping("/educationrecord/handyworker")
public class EducationRecordController {

	@Autowired
	private EducationRecordService	educationService;


	public EducationRecordController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = this.createEditModelAndView(this.educationService.createEducationRecord());
		//	result = new ModelAndView("educationrecord/edit");
		//	result.addObject("educationrecord", this.educationService.createEducationRecord());
		//result.addObject("requestURI", "educationrecord/handyworker/create.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final EducationRecord education, final BindingResult binding) {
		ModelAndView model;

		if (binding.hasErrors()) {
			model = this.createEditModelAndView(education);
			model.addObject("errors", binding.getAllErrors());
			model.addObject("errores", binding);
		} else
			try {
				this.educationService.save(education);
				model = new ModelAndView("redirect:/list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(education, "education.commit.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}
	protected ModelAndView createEditModelAndView(final EducationRecord education) {
		ModelAndView result;
		result = this.createEditModelAndView(education, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final EducationRecord education, final String message) {
		ModelAndView result;
		result = new ModelAndView("educationrecord/edit");
		result.addObject("educationrecord", education);
		result.addObject("message", message);
		return result;
	}
}
