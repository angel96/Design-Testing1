
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.RefereeService;
import utilities.Utiles;
import domain.Referee;

@Controller
@RequestMapping("/referee")
public class RefereeController {

	@Autowired
	private RefereeService	refereeService;


	// Constructors -----------------------------------------------------------
	public RefereeController() {
		super();
	}

	// Create ---------------------------------------------------------------		
	@RequestMapping(value = "/createReferee", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model;

		model = this.createEditModelAndView(Utiles.createReferee());

		return model;
	}

	// Edit ---------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Referee referee, final BindingResult binding) {
		ModelAndView model;

		if (binding.hasErrors()) {
			model = this.createEditModelAndView(referee);
			model.addObject("errors", binding.getAllErrors());
		} else
			try {
				this.refereeService.save(referee);
				model = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(referee, "referee.commit.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}

	//Personal data ---------------------------------------------------------------		
	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public ModelAndView editPersonalData() {
		ModelAndView model;
		//This method is used for personal data edition

		Referee find;

		find = this.refereeService.findByUserAccount(LoginService.getPrincipal().getId());

		model = this.createEditModelAndView(find);

		return model;
	}
	protected ModelAndView createEditModelAndView(final Referee referee) {
		ModelAndView model;
		model = this.createEditModelAndView(referee, null);
		return model;
	}
	protected ModelAndView createEditModelAndView(final Referee referee, final String message) {
		ModelAndView model;
		model = new ModelAndView("referee/edit");
		model.addObject("referee", referee);
		model.addObject("message", message);
		return model;
	}
}
