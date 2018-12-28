/*
 * ProfileController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ProfileService;
import utilities.Utiles;
import domain.Profile;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	@Autowired
	private ProfileService	serviceProfile;


	// Action-1 ---------------------------------------------------------------		

	public ProfileController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = this.createEditModelAndView(Utiles.createProfile());
		System.out.println(Utiles.createProfile());

		return result;
	}
	// Action-2 ---------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Profile profile, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(profile);
			result.addObject("errores", binding.getAllErrors());
			result.addObject("errores", binding);
			System.out.println("mierda");
			System.out.println(binding);
			System.out.println(binding.getAllErrors());
		} else
			try {
				System.out.println(profile);
				this.serviceProfile.save(profile);
				result = new ModelAndView("redirect:../welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(profile, "user.commit.error");
				result.addObject("oops", oops.getMessage());
				result.addObject("errors", binding.getAllErrors());
			}
		return result;
	}

	// Action-2 ---------------------------------------------------------------		

	//	@RequestMapping("/action-3")
	//	public ModelAndView action3() {
	//		throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
	//	}

	// Create edit model and view
	protected ModelAndView createEditModelAndView(final Profile profile) {
		ModelAndView model;
		model = this.createEditModelAndView(profile, null);
		return model;
	}

	protected ModelAndView createEditModelAndView(final Profile profile, final String message) {
		ModelAndView result;
		result = new ModelAndView("profile/edit");
		result.addObject("profile", profile);
		result.addObject("message", message);
		return result;
	}

}
