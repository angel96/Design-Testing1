
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.NoteService;
import utilities.Utiles;
import domain.Note;

@Controller
@RequestMapping("/note/customer/")
public class NoteController extends AbstractController {

	@Autowired
	private NoteService	noteService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("note/list");
		result.addObject("notes", this.noteService.findAll());
		result.addObject("requestURI", "note/customer/list.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		result = new ModelAndView("note/edit");
		result.addObject("note", Utiles.createNote());
		result.addObject("requestURI", "note/customer/create.do");
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Note note, final BindingResult bind) {
		ModelAndView model;
		if (bind.hasErrors())
			model = this.createEditModelAndView(note);
		else
			try {
				this.noteService.save(note);
				model = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(note, "note.commit.error");
			}
		return model;
	}

	protected ModelAndView createEditModelAndView(final Note note) {
		ModelAndView result;
		result = this.createEditModelAndView(note, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Note note, final String message) {
		ModelAndView result;
		result = new ModelAndView("note/edit");
		result.addObject("note", note);
		result.addObject("message", message);
		return result;
	}

}
