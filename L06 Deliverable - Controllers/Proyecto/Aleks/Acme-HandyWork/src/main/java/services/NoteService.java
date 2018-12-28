
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Customer;
import domain.HandyWorker;
import domain.Note;
import domain.Referee;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteRepository		noteRepository;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private RefereeService		refereeService;


	public Note findOne(final int noteId) {
		return this.noteRepository.findOne(noteId);
	}

	public Collection<Note> findAll() {
		Collection<Note> notes;
		notes = this.noteRepository.findAll();
		Assert.notNull(notes);
		return notes;
	}

	public Note create() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue((Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) || Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) || Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE)));
		Note res;
		res = new Note();
		res.setMoment(new Date());
		res.setComment("");
		res.setOtherComments(new ArrayList<String>());
		return res;
	}

	public Note save(final Note note) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);
		Assert.notNull(this.noteRepository.findReportByNoteId(note.getId()));
		Note saved;
		if (Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER)) {
			Customer c;
			c = this.customerService.findByUserAccount(user.getId());
			Collection<Note> notesPerCustomer;
			notesPerCustomer = this.noteRepository.findNotesByCustomerId(c.getId());
			saved = this.noteRepository.save(note);
			notesPerCustomer.add(saved);
			c.setNotes(notesPerCustomer);
			this.customerService.update(c);
		}
		if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER)) {
			HandyWorker h;
			h = this.handyWorkerService.findByUserAccount(user.getId());
			Collection<Note> notesPerHandyWorker;
			notesPerHandyWorker = this.noteRepository.findNotesByCustomerId(h.getId());
			saved = this.noteRepository.save(note);
			notesPerHandyWorker.add(saved);
			h.setNotes(notesPerHandyWorker);
			this.handyWorkerService.update(h);
		}
		if (Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE)) {
			Assert.isTrue(this.noteRepository.findReportByNoteId(note.getId()).getFinalMode(), "Report hasn't been saved in final mode");
			Referee r;
			r = this.refereeService.findByUserAccount(user.getId());
			Collection<Note> notesPerReferee;
			notesPerReferee = this.noteRepository.findNotesByCustomerId(r.getId());
			saved = this.noteRepository.save(note);
			notesPerReferee.add(saved);
			r.setNotes(notesPerReferee);
			this.refereeService.update(r);
		}
		saved = this.noteRepository.save(note);
		return saved;
	}

	public void addCommentToNote(final String comment, final Note n) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Collection<String> comments;
		Note toAddComments;
		toAddComments = this.findOne(n.getId());
		comments = toAddComments.getOtherComments();
		if (comment.equals("") == false || comment != null) {
			Assert.isTrue((Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER)) || (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER)) || (Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE)),
				"An actor with authority " + user.getAuthorities() + " can't add comments");
			comments.add(comment);
		}
		toAddComments.setOtherComments(comments);
		this.save(toAddComments);
	}

	public Double min() {
		return this.noteRepository.findMinimumOfNotesPerReport();
	}

	public Double max() {
		return this.noteRepository.findMaximumOfNotesPerReport();
	}

	public Double avg() {
		return this.noteRepository.findAverageOfNotesPerReport();
	}

	public Double stdev() {
		return this.noteRepository.findStandartDeviationOfNotesPerReport();
	}
}
