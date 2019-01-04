
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.EducationRecordService;
import services.HandyWorkerService;
import services.ProfessionalRecordService;

@Controller
@RequestMapping("/curriculum/handyworker")
public class CurriculumController extends AbstractController {

	@Autowired
	private HandyWorkerService			handyWorkerService;

	@Autowired
	private EducationRecordService		educationRecordService;

	@Autowired
	private ProfessionalRecordService	professionalRecordService;


	public CurriculumController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("curriculum/list");
		result.addObject("educationrecord", this.educationRecordService.findAll());
		result.addObject("professionalrecord", this.professionalRecordService.findAll());
		result.addObject("requestURI", "curriculum/handyworker/list.do");
		return result;
	}

}
