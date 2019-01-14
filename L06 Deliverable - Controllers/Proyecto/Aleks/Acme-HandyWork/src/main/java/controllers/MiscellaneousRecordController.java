
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MiscellaneousRecordService;
import domain.MiscellaneousRecord;

@Controller
@RequestMapping("/miscellaneousrecord/handyworker")
public class MiscellaneousRecordController extends AbstractController {

	@Autowired
	private MiscellaneousRecordService	miscellaneousService;


	public MiscellaneousRecordController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = this.createEditModelAndView(this.miscellaneousService.createMiscellaneousRecord());
		result.addObject("requestURI", "miscellaneousrecord/handyworker/create.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final MiscellaneousRecord miscellaneous, final BindingResult binding) {
		ModelAndView model;

		if (binding.hasErrors()) {
			model = this.createEditModelAndView(miscellaneous);
			System.out.println(miscellaneous);
			System.out.println(miscellaneous.getId());
			model.addObject("errors", binding.getAllErrors());
			model.addObject("errores", binding);
		} else
			try {
				this.miscellaneousService.save(miscellaneous);
				model = new ModelAndView("redirect:/curriculum/handyworker/list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(miscellaneous, "miscellaneous.commit.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}
	protected ModelAndView createEditModelAndView(final MiscellaneousRecord miscellaneous) {
		ModelAndView result;
		result = this.createEditModelAndView(miscellaneous, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MiscellaneousRecord miscellaneous, final String message) {
		ModelAndView result;
		result = new ModelAndView("miscellaneousrecord/edit");
		result.addObject("miscellaneousrecord", miscellaneous);
		result.addObject("message", message);
		return result;
	}
}
