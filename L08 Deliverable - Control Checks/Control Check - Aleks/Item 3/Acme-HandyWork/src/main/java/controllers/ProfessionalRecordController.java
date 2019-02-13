
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ProfessionalRecordService;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("/professionalrecord/handyworker")
public class ProfessionalRecordController extends AbstractController {

	@Autowired
	private ProfessionalRecordService	professionalService;


	public ProfessionalRecordController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = this.createEditModelAndView(this.professionalService.createProfessionalRecord());
		result.addObject("requestURI", "professionalrecord/handyworker/create.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final ProfessionalRecord professionalRecord, final BindingResult binding) {
		ModelAndView model;

		if (binding.hasErrors()) {
			model = this.createEditModelAndView(professionalRecord);
			model.addObject("errors", binding.getAllErrors());
			model.addObject("errores", binding);
		} else
			try {
				this.professionalService.save(professionalRecord);
				model = new ModelAndView("redirect:/curriculum/handyworker/list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(professionalRecord, "professional.commit.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}

	protected ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord) {
		ModelAndView result;
		result = this.createEditModelAndView(professionalRecord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord, final String message) {
		ModelAndView result;
		result = new ModelAndView("professionalrecord/edit");
		result.addObject("professionalRecord", professionalRecord);
		result.addObject("message", message);
		return result;
	}

}
