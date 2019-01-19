
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.EndorserRecordService;
import domain.EndorserRecord;

@Controller
@RequestMapping("/endorserrecord/handyworker")
public class EndorserRecordController extends AbstractController {

	@Autowired
	private EndorserRecordService	endorserService;


	public EndorserRecordController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = this.createEditModelAndView(this.endorserService.createEndorserRecord());
		result.addObject("requestURI", "endorserrecord/handyworker/create.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final EndorserRecord endorserRecord, final BindingResult binding) {
		ModelAndView model;

		if (binding.hasErrors()) {
			model = this.createEditModelAndView(endorserRecord);
			model.addObject("errors", binding.getAllErrors());
			model.addObject("errores", binding);
		} else
			try {
				this.endorserService.save(endorserRecord);
				model = new ModelAndView("redirect:/curriculum/handyworker/list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(endorserRecord, "endorser.commit.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}

	protected ModelAndView createEditModelAndView(final EndorserRecord endorserRecord) {
		ModelAndView result;
		result = this.createEditModelAndView(endorserRecord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final EndorserRecord endorserRecord, final String message) {
		ModelAndView result;
		result = new ModelAndView("endorserrecord/edit");
		result.addObject("endorserRecord", endorserRecord);
		result.addObject("message", message);
		return result;
	}
}
