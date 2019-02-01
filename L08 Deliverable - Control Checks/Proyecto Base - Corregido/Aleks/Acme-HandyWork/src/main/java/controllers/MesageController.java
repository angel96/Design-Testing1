
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
	public ModelAndView createMessage(@RequestParam(defaultValue = "0") final int id) {
		ModelAndView result;
		if (id == 0) {
			result = this.createEditModelAndView(this.serviceMessage.createMessage(this.serviceBox.findActorByUserAccount(LoginService.getPrincipal().getId())));
			result.addObject("requestURI", "box/mess/send.do");
			result.addObject("view", false);
		} else {
			result = this.createEditModelAndView(this.serviceMessage.findOne(id));
			result.addObject("boxesOptional", this.serviceBox.getBoxesFromActorNoSystem(LoginService.getPrincipal().getId()));
			result.addObject("view", true);
			result.addObject("requestURI", "box/mess/dbox.do");
		}

		return result;
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "send")
	public ModelAndView sendMessage(@Valid final Mesage mesage, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(mesage);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				this.serviceMessage.sendMessage(mesage);
				final Box box = this.serviceBox.getActorSendedBox(this.serviceBox.findActorByUserAccount(LoginService.getPrincipal().getId()).getId());
				result = new ModelAndView("redirect:list.do?boxId=" + box.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(mesage, "message.commit.error");
				result.addObject("oops", oops.getMessage());
				result.addObject("errors", binding.getAllErrors());
			}
		return result;
	}

	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public ModelAndView moveToInBox(@RequestParam final int id) {
		ModelAndView result;
		Mesage mesage = null;
		try {
			mesage = this.serviceMessage.findOne(id);
			final int box = this.serviceMessage.moveTo("In Box", mesage);
			result = new ModelAndView("redirect:list.do?boxId=" + box);
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(mesage, "message.commit.error");
			System.out.println("=============================OOPS\n" + oops.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/dbox", method = RequestMethod.POST, params = "move")
	public ModelAndView moveToTrashBox(@RequestParam(defaultValue = "0") final int boxId, @Valid final Mesage mesage) {
		ModelAndView result;
		try {
			final int box = this.serviceMessage.moveTo("Trash Box", mesage);
			result = new ModelAndView("redirect:list.do?boxId=" + box);
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(mesage, "message.commit.error");
			System.out.println("=============================OOPS\n" + oops.getMessage());
		}

		return result;
	}
	@RequestMapping(value = "/dbox", method = RequestMethod.GET)
	public ModelAndView moveToOtherBox(@RequestParam final int boxId, @RequestParam(value = "mess") final int mess) {
		ModelAndView result;
		Mesage mesage = null;
		try {
			mesage = this.serviceMessage.findOne(mess);
			final int box = this.serviceMessage.moveTo(String.valueOf(boxId), mesage);
			result = new ModelAndView("redirect:list.do?boxId=" + box);
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(mesage, "message.commit.error");
			System.out.println("=============================OOPS\n" + oops.getMessage());
		}

		return result;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteFromSystem(@RequestParam final int id) {
		ModelAndView result;
		Mesage mesage = null;
		try {
			final Box box = this.serviceBox.getActorTrashBox(this.serviceBox.findActorByUserAccount(LoginService.getPrincipal().getId()).getId());
			mesage = this.serviceMessage.findOne(id);
			this.serviceMessage.deleteFromSystem(mesage);
			result = new ModelAndView("redirect:list.do?boxId=" + box.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(mesage, "message.commit.error");
			result.addObject("oops", oops.getMessage());
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Mesage mesage) {
		ModelAndView result;

		result = this.createEditModelAndView(mesage, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(final Mesage mesage, final String message) {
		ModelAndView result;

		result = new ModelAndView("mess/edit");
		result.addObject("mesage", mesage);
		result.addObject("message", message);
		result.addObject("priorities", Utiles.priorities());
		result.addObject("actors", this.serviceBox.findAllActorsSystem());

		return result;
	}

}
