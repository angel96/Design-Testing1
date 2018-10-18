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

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ShoutService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	@Autowired
	private ShoutService	shoutService;


	public AdministratorController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value = "/type-shouts", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Map<String, Double> statistics;

		statistics = this.shoutService.computeStatistics();
		result = new ModelAndView("administrator/type-shouts");
		result.addObject("statistics", statistics);

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/chart")
	public ModelAndView action2() {
		ModelAndView result;

		Map<String, Double> statistics;
		statistics = this.shoutService.computeStatistics();
		result = new ModelAndView("administrator/chart");
		result.addObject("statistics", statistics);
		return result;
	}

}
