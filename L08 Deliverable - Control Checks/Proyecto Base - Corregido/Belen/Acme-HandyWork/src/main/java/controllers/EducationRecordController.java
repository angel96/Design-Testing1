
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
public class EducationRecordController extends AbstractController {

	@Autowired
	private EducationRecordService	educationService;


	public EducationRecordController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = this.createEditModelAndView(this.educationService.createEducationRecord());
		result.addObject("requestURI", "educationrecord/handyworker/edit.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final EducationRecord educationRecord, final BindingResult binding) {
		ModelAndView model;

		if (binding.hasErrors()) {
			model = this.createEditModelAndView(educationRecord);
			model.addObject("errors", binding.getAllErrors());
		} else
			try {
				this.educationService.save(educationRecord);
				model = new ModelAndView("redirect:/curriculum/handyworker/list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(educationRecord, "education.commit.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}
	protected ModelAndView createEditModelAndView(final EducationRecord educationRecord) {
		ModelAndView result;
		result = this.createEditModelAndView(educationRecord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final EducationRecord educationRecord, final String message) {
		ModelAndView result;
		result = new ModelAndView("educationrecord/edit");
		result.addObject("educationRecord", educationRecord);
		result.addObject("message", message);
		return result;
	}
}
