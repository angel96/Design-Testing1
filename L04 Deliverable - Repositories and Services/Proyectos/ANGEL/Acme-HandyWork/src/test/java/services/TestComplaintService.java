
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
import domain.Complaint;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", 
	"classpath:spring/config/packages.xml"
})
@Transactional
public class TestComplaintService extends AbstractTest {

	@Autowired
	private ComplaintService	complaintService;

	@Test //TEST FOR LISTING COMPLAINTS
	public void testGetAllComplaints() {
		super.authenticate("customer2");
		Assert.isTrue(this.complaintService.findAll().size() >= 1);
		super.unauthenticate();
	}
	
	@Test //TEST FOR SHOWING COMPLAINTS
	public void testGetComplaint() {
		super.authenticate("customer2");
		Assert.notNull(this.complaintService.findOne(3022));
		super.unauthenticate();
	}
	@Test //TEST FOR CREATING COMPLAINTS
	public void testCreateComplaints() {
		super.authenticate("customer2");
		Complaint c;
		c = new Complaint();
		c.setAttachment(12);
		c.setDescription("a ver");
		c.setMoment(new GregorianCalendar(2018, 11, 29, 16, 0, 0).getTime());
		c.setReport(new ArrayList<Report>());
		Complaint saved;
		saved = this.complaintService.save(c);
		Assert.notNull(saved);
		super.unauthenticate();
	}
	
	@Test //TEST FOR LISTING COMPLAINTS THAT NO REFEREE ASSIGNED
	public void testListNoReferee() {
		super.authenticate("referee1");
		Assert.notNull(this.complaintService.findComplaintNoRefereeAssigned());
		super.unauthenticate();
	}
	
	@Test //TEST FOR LISTING COMPLAINTS THAT REFEREE ASSIGNED
	public void testListReferee() {
		super.authenticate("referee1");
		Assert.notNull(this.complaintService.findComplaintRefereeAssigned());
		super.unauthenticate();
	}
	
	@Test //TEST FOR LISTING COMPLAINTS THAT OWN REFEREE ASSIGNED
	public void testListComplaintByReferee() {
		super.authenticate("referee2");
		Assert.notNull(this.complaintService.findComplaintByReferee(1236));
		super.unauthenticate();
	}
	
	@Test //TEST FOR LISTING AND SHOWING COMPLAINTS BY HANDY WORKER ID
	public void testListComplaintByHandy() {
		super.authenticate("handyworker1");
		Assert.notNull(this.complaintService.findComplaintByHandyWorkerId(3078));
		super.unauthenticate();
	}
}
