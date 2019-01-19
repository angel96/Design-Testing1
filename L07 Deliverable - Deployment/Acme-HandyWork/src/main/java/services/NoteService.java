
package services;

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
import domain.Report;

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

	@Autowired
	private ReportService		reportService;


	public Report findReportByNote(final int id) {
		return this.noteRepository.findReportByNote(id);
	}

	public Collection<Note> findNoteByCustomer(final int id) {
		return this.noteRepository.findNotesByCustomerId(id);
	}

	public Customer finCustomerByUsarAccountId(final int id) {
		return this.noteRepository.findCustomerByUserAccountId(id);
	}

	public Collection<Note> findNoteByHandy(final int id) {
		return this.noteRepository.findNotesByHandyWorkerId(id);
	}

	public HandyWorker findHandyWorkerByUserAccountId(final int id) {
		return this.noteRepository.findHandyWorkerByUserAccountId(id);
	}

	public Collection<Note> findNoteByReferee(final int id) {
		return this.noteRepository.findNotesByRefereeId(id);
	}

	public Referee findRefereeByUserAccountId(final int id) {
		return this.noteRepository.findRefereeByUserAccountId(id);
	}

	public Note findOne(final int noteId) {
		return this.noteRepository.findOne(noteId);
	}

	public Collection<Note> selectNoteReferenceHandy(final int id) {
		return this.noteRepository.selectNoteReferenceHandy(id);
	}

	public Collection<Note> selectNoteReferenceCustomer(final int id) {
		return this.noteRepository.selectNoteReferenceCustomer(id);
	}

	public Collection<Note> selectNoteReferenceReferee(final int id) {
		return this.noteRepository.selectNoteReferenceReferee(id);
	}

	public Collection<Note> findAll() {
		Collection<Note> notes;
		notes = this.noteRepository.findAll();
		Assert.notNull(notes);
		return notes;
	}

	public Note createNote() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue((Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) || Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) || Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE)));
		Note res;
		res = new Note();
		res.setMoment(new Date());
		res.setCustomerComment("");
		res.setRefereeComment("");
		res.setHandyWorkerComment("");
		return res;
	}

	public Note save(final Note note, final int idRep) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);
		Note saved;
		saved = this.noteRepository.save(note);
		if (Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER)) {

			Customer c;
			c = this.customerService.findByUserAccount(user.getId());
			Collection<Note> notesPerCustomer;
			notesPerCustomer = this.noteRepository.findNotesByCustomerId(c.getId());
			if (!notesPerCustomer.contains(saved)) {
				notesPerCustomer.add(saved);
				c.setNotes(notesPerCustomer);
			}

		}
		if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER)) {
			HandyWorker h;
			h = this.handyWorkerService.findByUserAccount(user.getId());
			Collection<Note> notesPerHandyWorker;
			notesPerHandyWorker = this.noteRepository.findNotesByHandyWorkerId(h.getId());
			if (!notesPerHandyWorker.contains(saved)) {
				notesPerHandyWorker.add(saved);
				h.setNotes(notesPerHandyWorker);
			}

		}
		if (Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE)) {
			Referee r;
			r = this.refereeService.findByUserAccount(user.getId());

			Collection<Note> notesPerReferee;
			notesPerReferee = this.noteRepository.findNotesByRefereeId(r.getId());
			if (!notesPerReferee.contains(saved)) {
				notesPerReferee.add(saved);
				r.setNotes(notesPerReferee);
			}

		}
		Report r;
		r = this.reportService.findOne(idRep);
		Collection<Note> coll;
		coll = r.getNotes();
		if (!coll.contains(saved)) {
			coll.add(saved);
			r.setNotes(coll);
		}
		return saved;
	}

}
