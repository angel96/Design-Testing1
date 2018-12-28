
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
import services.MessageService;
import utilities.Utiles;
import domain.Message;

@Controller
@RequestMapping("/box/message")
public class MessageController extends AbstractController {

	@Autowired
	private MessageService	serviceMessage;

	@Autowired
	private BoxService		serviceBox;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listMessage(@RequestParam final int boxId) {
		ModelAndView result;
		result = new ModelAndView("message/list");
		result.addObject("messages", this.serviceMessage.getMessagesByBox(boxId));
		result.addObject("requestURI", "message/list.do");
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createMessage() {
		ModelAndView result;
		result = new ModelAndView("message/edit");
		result.addObject("message", Utiles.createMessage(this.serviceBox.findActorByUserAccount(LoginService.getPrincipal().getId())));
		return result;
	}
	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "send")
	public ModelAndView sendMessage(@Valid final Message message, final BindingResult result) {
		return null;
	}

	@RequestMapping(value = "/trashbox", method = RequestMethod.POST, params = "move")
	public ModelAndView moveToTrashBox(final Message message) {
		return null;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteFromSystem(final Message message) {
		return null;
	}

}
