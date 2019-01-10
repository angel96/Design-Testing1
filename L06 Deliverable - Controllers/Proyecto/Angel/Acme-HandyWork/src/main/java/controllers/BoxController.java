
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.BoxService;
import utilities.Utiles;
import domain.Box;

@Controller
@RequestMapping("/box")
public class BoxController {

	@Autowired
	private BoxService	serviceBox;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView boxesActorLogged() {
		ModelAndView result;
		result = new ModelAndView("box/list");
		result.addObject("boxes", this.serviceBox.getBoxesFromActor(LoginService.getPrincipal().getId()));
		result.addObject("requestURI", "box/list.do");
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		result = this.createEditModelAndView(Utiles.createBox(false, ""));
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(final Box box, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(box);
		else
			try {
				this.serviceBox.save(box);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(box, "box.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int id) {

		ModelAndView result;
		final Box box = this.serviceBox.findOne(id);
		if (box.getFromSystem()) {
			result = new ModelAndView("redirect:list.do");
			result.addObject("message", "box.commit.fromsystem");
		} else {
			this.serviceBox.deleteBox(box);
			result = new ModelAndView("redirect:list.do");
			result.addObject("message", "box.commit.fromsystemno");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Box box) {
		ModelAndView result;
		result = this.createEditModelAndView(box, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Box box, final String message) {
		ModelAndView result;
		result = new ModelAndView("box/edit");
		result.addObject("box", box);
		result.addObject("message", message);
		return result;
	}

}
