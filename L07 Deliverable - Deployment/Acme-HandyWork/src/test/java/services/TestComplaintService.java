
package services;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.LoginService;
import utilities.AbstractTest;
import domain.Complaint;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestComplaintService extends AbstractTest {

	@Autowired
	private ComplaintService	complaintService;


	//TEST FOR LISTING COMPLAINTS
	@Test
	public void testGetAllComplaints() {
		super.authenticate("customer2");
		Assert.isTrue(this.complaintService.findAll().size() >= 1);
		super.unauthenticate();
	}
	//TEST FOR FIND ONE COMPLAINT
	@Test
	public void testFindOne() {
		super.authenticate("customer2");
		Assert.notNull(this.complaintService.findOne(4269));
		super.unauthenticate();
		super.authenticate("handyWorker1");
		Assert.notNull(this.complaintService.findOne(4269));
		super.unauthenticate();
		super.authenticate("referee3");
		Assert.notNull(this.complaintService.findOne(4269));
		super.unauthenticate();
	}
	//TEST FOR UPDATE COMPLAINT
	@Test
	public void testUpdateComplaint() {
		super.authenticate("referee3");
		Assert.notNull(this.complaintService.findOne(4271));
		Complaint c;
		c = this.complaintService.findOne(4271);
		this.complaintService.update(4271);
		Assert.isTrue(c.getReferee().getId() == 4274, "No se ha guardado el referee logeado");
		super.unauthenticate();
	}
	//TEST FOR SAVE COMPLAINT
	@Test
	public void testSaveComplaint() {
		super.authenticate("customer2");
		Complaint c;
		c = new Complaint();
		c.setAttachment(12);
		c.setDescription("description");
		c.setMoment(new Date());
		c.setReport(new ArrayList<Report>());
		Complaint saved;
		saved = this.complaintService.save(c, 4286);
		Assert.notNull(saved, "La compalint no se guardó correctamente");
		super.unauthenticate();
	}
	//TEST FOR LIST COMPLAINT
	@Test
	public void testListComplaintByCustomer() {
		super.authenticate("customer1");
		Assert.notNull(this.complaintService.findComplaintByCustomer(this.complaintService.getActorByUser(LoginService.getPrincipal().getId())), "No se listan las complaints del user logeado");
		super.unauthenticate();
	}

	@Test
	public void testListComplaintByHandy() {
		super.authenticate("handyWorker1");
		Assert.notNull(this.complaintService.findComplaintByHandyWorkerId(this.complaintService.getActorByUser(LoginService.getPrincipal().getId()).getId()), "No se listan las complaints del user logeado");
		super.unauthenticate();
	}

	@Test
	public void testListComplaintByReferee() {
		super.authenticate("referee1");
		Assert.notNull(this.complaintService.findComplaintByReferee(this.complaintService.getActorByUser(LoginService.getPrincipal().getId()).getId()), "No se listan las complaints del user logeado");
		super.unauthenticate();
	}

	@Test
	public void testListNoReferee() {
		super.authenticate("referee1");
		Assert.notNull(this.complaintService.findComplaintNoRefereeAssigned());
		super.unauthenticate();
	}

}
