/*
 * AdministratorController.java
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

import services.AdministratorService;
import utilities.Utiles;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	serviceAdministrator;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView model;

		model = this.createEditModelAndView(Utiles.createAdministrator());

		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Administrator administratorObject, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(administratorObject);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				this.serviceAdministrator.save(administratorObject);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(administratorObject, "El formulario no se ha procesado correctamente");

			}

		return result;
	}
	protected ModelAndView createEditModelAndView(final Administrator administratorObject) {
		ModelAndView model;
		model = this.createEditModelAndView(administratorObject, null);
		return model;
	}
	protected ModelAndView createEditModelAndView(final Administrator administratorObject, final String message) {
		ModelAndView result;
		result = new ModelAndView("administrator/edit");
		result.addObject("administratorObject", administratorObject);
		result.addObject("message", message);
		return result;

	}

}
