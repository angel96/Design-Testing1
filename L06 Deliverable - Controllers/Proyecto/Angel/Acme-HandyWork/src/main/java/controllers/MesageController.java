
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.BoxService;
import services.MesageService;
import utilities.Utiles;
import domain.Box;
import domain.Mesage;

@Controller
@RequestMapping("/box/mess")
public class MesageController extends AbstractController {

	@Autowired
	private MesageService	serviceMessage;

	@Autowired
	private BoxService		serviceBox;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listMessage(@RequestParam final int boxId) {
		final Box box = this.serviceBox.findOne(boxId);

		ModelAndView result;
		result = new ModelAndView("mess/list");

		result.addObject("messages", this.serviceMessage.getMessagesByBox(boxId));
		result.addObject("boxName", box.getName());

		result.addObject("requestURI", "message/list.do");
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createMessage() {
		ModelAndView result;
		result = new ModelAndView("mess/edit");
		result.addObject("mesage", Utiles.createMessage(this.serviceBox.findActorByUserAccount(LoginService.getPrincipal().getId())));
		result.addObject("priorities", Utiles.priorities());
		result.addObject("actors", this.serviceBox.findAllActorsSystem());
		return result;
	}
	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "send")
	public ModelAndView sendMessage(@Valid final Mesage mesage, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.editAndCreateModelAndView(mesage);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				this.serviceMessage.sendMessage(mesage);
				result = new ModelAndView("box/list");
			} catch (final Throwable oops) {
				result = this.editAndCreateModelAndView(mesage, "message.commit.error");
				result.addObject("oops", oops.getMessage());
				result.addObject("errors", binding.getAllErrors());
			}
		return result;
	}

	@RequestMapping(value = "/trashbox", method = RequestMethod.POST, params = "move")
	public ModelAndView moveToTrashBox(final Mesage mesage) {
		ModelAndView result;
		result = new ModelAndView("box/list");
		return result;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteFromSystem(final Mesage mesage) {
		ModelAndView result;

		try {
			this.serviceMessage.deleteFromSystem(mesage);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.editAndCreateModelAndView(mesage, "mesage.commit.error");
		}

		return result;
	}

	protected ModelAndView editAndCreateModelAndView(final Mesage mesage) {
		ModelAndView result;

		result = this.editAndCreateModelAndView(mesage, null);

		return result;
	}
	protected ModelAndView editAndCreateModelAndView(final Mesage mesage, final String message) {
		ModelAndView result;

		result = new ModelAndView("mess/edit");
		result.addObject("mesage", mesage);
		result.addObject("message", message);

		return result;
	}

}
