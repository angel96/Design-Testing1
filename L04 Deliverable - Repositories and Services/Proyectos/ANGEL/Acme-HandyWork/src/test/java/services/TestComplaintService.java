
package services;

import java.util.ArrayList;
import java.util.Date;

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
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestComplaintService extends AbstractTest {

	@Autowired
	private ComplaintService	complaintService;


	@Test
	public void testCreateComplaints() {
		super.authenticate("customer2");
		Complaint c, saved;
		c = new Complaint();
		c.setAttachment(12);
		c.setDescription("a ver");
		c.setMoment(new Date(2018, 11, 29, 16, 0));
		c.setReport(new ArrayList<Report>());
		saved = this.complaintService.save(c);
		Assert.notNull(saved);
		super.unauthenticate();
	}

}
