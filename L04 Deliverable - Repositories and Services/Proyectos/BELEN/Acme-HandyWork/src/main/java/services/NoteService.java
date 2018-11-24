package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Note;

public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private CustomerService customerService;
	
	public  Collection<Note> findAll() {
		return this.noteRepository.findAll();
	}
	
	public Note findOne(final int noteId) {
		return this.noteRepository.findOne(noteId);
	}
	public Note create() {
		Note res;
		res = new Note();
		res.setMoment(new Date());
		res.setComment(new String());
		res.setOtherComments(new ArrayList<String>());
		return res;
	}
	
	public Note save(final Note note) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);
		Customer c;
		c = this.customerService.findByUserAccount(user.getId());
		Collection<Note> notesPerCustomer;
		notesPerCustomer = this.noteRepository.;
		
	}
	
	public Double min() {
		return this.noteRepository.findMinimumOfNotesPerReferee();
	}
	
	public Double max() {
		return this.noteRepository.findMaximumOfNotesPerReferee();
	}
	
	public Double avg() {
		return this.noteRepository.findAverageOfNotesPerReferee();
	}
	
	public Double stdev() {
		return this.noteRepository.findStandartDeviationOfNotesPerReferee();
	}
}
