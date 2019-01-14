
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.LoginService;
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


	//TEST LISTING NOTES
	@Test
	public void testNoteReferenceCustomer() {
		super.authenticate("customer1");
		Assert.notNull(this.noteService.selectNoteReferenceCustomer(this.noteService.finCustomerByUsarAccountId(LoginService.getPrincipal().getId()).getId()));
		super.unauthenticate();
	}

	@Test
	public void testNoteReferenceHandy() {
		super.authenticate("handyWorker2");
		Assert.notNull(this.noteService.selectNoteReferenceHandy(this.noteService.findHandyWorkerByUserAccountId(LoginService.getPrincipal().getId()).getId()));
		super.unauthenticate();
	}

	@Test
	public void testNoteReferenceReferee() {
		super.authenticate("referee3");
		Assert.notNull(this.noteService.selectNoteReferenceReferee(this.noteService.findRefereeByUserAccountId(LoginService.getPrincipal().getId()).getId()));
		super.unauthenticate();
	}
	//TEST SAVE A NOTE
	@Test
	public void testSaveNote() {
		super.authenticate("referee1");
		Note n;
		n = new Note();
		n.setRefereeComment("comment");
		Note saved;
		saved = this.noteService.save(n, 4352);
		Assert.notNull(saved, "La note no se pudo guardar");
		super.unauthenticate();
	}
	//TEST FIND ONE NOTE
	@Test
	public void testFindOne() {
		super.authenticate("referee3");
		Assert.notNull(this.noteService.findOne(4233), "No se puede encontrar el report");
		Assert.isNull(this.noteService.findOne(4298));
		super.unauthenticate();
	}
}
