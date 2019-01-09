
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
import services.NoteService;
import domain.Note;

@Controller
@RequestMapping(value = {
	"/note/customer/", "/note/handyworker", "/note/referee"
})
public class NoteController extends AbstractController {

	@Autowired
	private NoteService	noteService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("note/list");
		if (LoginService.getPrincipal().getAuthorities().toString().equals("[CUSTOMER]")) {
			result.addObject("notes", this.noteService.selectNoteReferenceCustomer(this.noteService.finCustomerByUsarAccountId(LoginService.getPrincipal().getId()).getId()));
			result.addObject("requestURI", "note/customer/list.do");
		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[HANDY_WORKER]")) {
			result.addObject("requestURI", "note/handyworker/list.do");
			result.addObject("notes", this.noteService.selectNoteReferenceHandy(this.noteService.findHandyWorkerByUserAccountId(LoginService.getPrincipal().getId()).getId()));
		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[REFEREE]")) {
			result.addObject("requestURI", "note/referee/list.do");
			result.addObject("notes", this.noteService.selectNoteReferenceReferee(this.noteService.findRefereeByUserAccountId(LoginService.getPrincipal().getId()).getId()));
		}
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int idRep) {
		ModelAndView result;
		result = this.createEditModelAndView(this.noteService.createNote());
		if (LoginService.getPrincipal().getAuthorities().toString().equals("[CUSTOMER]"))
			result.addObject("requestURI", "note/customer/edit.do");
		else if (LoginService.getPrincipal().getAuthorities().toString().equals("[HANDY_WORKER]"))
			result.addObject("requestURI", "note/handyworker/edit.do");
		else if (LoginService.getPrincipal().getAuthorities().toString().equals("[REFEREE]"))
			result.addObject("requestURI", "note/referee/edit.do");
		result.addObject("idRep", idRep);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Note note, final BindingResult binding, @RequestParam final int idRep) {
		ModelAndView model;
		System.out.println(note);
		if (binding.hasErrors()) {
			model = this.createEditModelAndView(note);
			model.addObject("errores", binding.getAllErrors());
			model.addObject("errores", binding);
		} else
			try {
				this.noteService.save(note, idRep);
				model = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(note, "note.commit.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}

	@RequestMapping(value = "/addComment", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int idNote) {
		ModelAndView result;
		Note note;
		note = this.noteService.findOne(idNote);
		result = this.createEditModelAndView(note);
		result.addObject("idRep", this.noteService.findReportByNote(idNote).getId());
		//		Report r;
		//		r = this.noteService.findReportByNote(idNote);
		//		this.noteService.update(note, idNote);
		if (LoginService.getPrincipal().getAuthorities().toString().equals("[CUSTOMER]"))
			result.addObject("requestURI", "note/customer/edit.do");
		else if (LoginService.getPrincipal().getAuthorities().toString().equals("[HANDY_WORKER]"))
			result.addObject("requestURI", "note/handyworker/edit.do");
		else if (LoginService.getPrincipal().getAuthorities().toString().equals("[REFEREE]"))
			result.addObject("requestURI", "note/referee/edit.do");

		return result;
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
