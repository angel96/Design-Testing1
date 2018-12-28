
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FixUpTaskService;

@Controller
@RequestMapping("fixuptask/customer")
public class FixUpTaskController {

	@Autowired
	private FixUpTaskService	fixUpService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("note/list");
		result.addObject("fixuptask", this.fixUpService.findAll());
		result.addObject("requestURI", "fixptask/customer/list.do");
		return result;
	}

	//FINDER
}
