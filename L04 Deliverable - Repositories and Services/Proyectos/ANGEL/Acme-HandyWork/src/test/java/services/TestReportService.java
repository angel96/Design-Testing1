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
import utilities.Utiles;
import domain.Complaint;
import domain.Note;
import domain.Referee;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", 
	"classpath:spring/config/packages.xml"
})
@Transactional
public class TestReportService extends AbstractTest {
	
	@Autowired
	private ReportService reportService;
	
	@Test // TESTING FIND ALL REPORTS THAT ARE SAVED IN FINAL MODE
	public void testReportsFinalMode() {
		super.authenticate("customer1");
		Assert.isTrue(this.reportService.findAllFinalMode().size() >= 1);
		super.unauthenticate();
	}
	
	@Test //TEST FOR CREATING REPORTS
	public void testCreateReports() {
		super.authenticate("referee1");
		Report r;
		r = new Report();
		r.setAttachments(new ArrayList<String>());
		
		Complaint c;
		c = new Complaint();
		c.setAttachment(12);
		c.setDescription("a ver");
		c.setMoment(new GregorianCalendar(2018, 11, 29, 16, 0, 10).getTime());
		c.setReport(new ArrayList<Report>());
		c.setTicker(Utiles.generateTicker());
		c.setId(3132);
		
		System.out.println(c);
		r.setComplaints(c);
		r.setDescription("eeee");
		r.setFinalMode(true);
		r.setMoment(new GregorianCalendar(2018, 11, 29, 16, 0, 0).getTime());
		r.setNotes(new ArrayList<Note>());
		r.setReferee(new Referee());
		this.reportService.create();
		super.unauthenticate();
	}
	
	@Test //TESTING UPDATE REPORT
	public void testUpdateReport() {
		super.authenticate("referee1");
		Report r;
		r = this.reportService.findOne(3105);
		r.setDescription("abcd");
		Report saved;
		saved = this.reportService.update(r);
		Assert.notNull(saved);
		super.unauthenticate();
	}
	
	@Test //TESTING DELETE REPORT
	public void testDeleteReport() {
		super.authenticate("referee1");
		Report r;
		r = this.reportService.findOne(3106);
		this.reportService.delete(r);
		Assert.isNull(this.reportService.findOne(3106));
		super.unauthenticate();
	}
}
