/*
 * WelcomeController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.CustomisationSystemService;
import utilities.Utiles;
import domain.CustomisationSystem;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private CustomisationSystemService	serviceCustom;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");

		final CustomisationSystem custom = this.serviceCustom.findUnique();

		result.addObject("moment", moment);
		result.addObject("banner", custom.getBanner());
		result.addObject("systemName", custom.getSystemName());
		result.addObject("mess", custom.getMessage());

		Utiles.goodWords.addAll(custom.getGoodWords());
		Utiles.badWords.addAll(custom.getBadWords());
		Utiles.spamWords.addAll(custom.getSpamWords());
		Utiles.setParameters(custom.getHoursFinder(), custom.getResultFinder(), custom.getVat(), custom.getPhonePrefix());

		return result;
	}
}
