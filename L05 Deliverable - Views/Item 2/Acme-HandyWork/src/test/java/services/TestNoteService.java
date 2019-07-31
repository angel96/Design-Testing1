
package services;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestNoteService extends AbstractTest {

	@Autowired
	private NoteService	noteService;


	@Test
	//TEST FOR CREATE
	public void testCreateNote() {
		super.authenticate("handyworker1");
		Note n;
		n = new Note();
		n.setId(5010); // if the note doesn't is regarding any of the reports, failed
		n.setMoment(new GregorianCalendar(2018, 11, 28, 17, 0, 0).getTime());
		n.setComment("Comentario 1");
		n.setOtherComments(new ArrayList<String>());
		Note saved;
		saved = this.noteService.save(n);
		Assert.notNull(saved);
		super.unauthenticate();
	}

	@Test
	//TEST FOR WRITE COMMENTS
	public void testAddComments() {
		super.authenticate("handyworker1");
		Note n;
		n = this.noteService.findOne(5010);
		final String c = "Comentario extra";
		this.noteService.addCommentToNote(c, n);
		super.unauthenticate();
	}
	@Test
	//TEST FOR CREATE
	public void testCreateNoteCustomer() {
		super.authenticate("customer1");
		Note n;
		n = new Note();
		n.setId(5010); // if the note doesn't is regarding any of the reports, failed
		n.setMoment(new GregorianCalendar(2018, 11, 28, 17, 0, 0).getTime());
		n.setComment("Comentario 1");
		n.setOtherComments(new ArrayList<String>());
		Note saved;
		saved = this.noteService.save(n);
		Assert.notNull(saved);
		super.unauthenticate();
	}

	@Test
	//TEST FOR WRITE COMMENTS
	public void testAddCommentsCustomer() {
		super.authenticate("customer1");
		Note n;
		n = this.noteService.findOne(5010);
		final String c = "Comentario extra";
		this.noteService.addCommentToNote(c, n);
		super.unauthenticate();
	}
	@Test
	//TEST FOR CREATE
	public void testCreateNoteReferee() {
		super.authenticate("referee1");
		Note n;
		n = new Note();
		n.setId(5005); // if the note doesn't is regarding any of the reports, failed
		n.setMoment(new GregorianCalendar(2018, 11, 28, 17, 0, 0).getTime());
		n.setComment("Comentario 1");
		n.setOtherComments(new ArrayList<String>());
		Note saved;
		saved = this.noteService.save(n);
		Assert.notNull(saved);
		super.unauthenticate();
	}

	@Test
	//TEST FOR WRITE COMMENTS
	public void testAddCommentsReferee() {
		super.authenticate("referee1");
		Note n;
		n = this.noteService.findOne(5005);
		final String c = "Comentario extra";
		this.noteService.addCommentToNote(c, n);
		super.unauthenticate();
	}
}
