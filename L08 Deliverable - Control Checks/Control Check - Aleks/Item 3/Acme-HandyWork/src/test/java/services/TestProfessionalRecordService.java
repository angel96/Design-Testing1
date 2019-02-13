
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.ProfessionalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestProfessionalRecordService extends AbstractTest {

	@Autowired
	ProfessionalRecordService	professionalRecService;


	@Test
	public void testFindAll() {
		super.authenticate("handyworker1");
		Assert.isTrue(!(this.professionalRecService.findAll().isEmpty()));
		super.unauthenticate();
	}

	@Test
	public void testFindOne() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.professionalRecService.findOne(4277) != null);
		super.unauthenticate();
	}
	@Test
	public void testFindprofessionalRecordByUserId() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.professionalRecService.findProfessionalRecordByUserId(4322).size() > 0);
		super.unauthenticate();
	}
	@Test
	public void testCreateprofessionalRecord() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.professionalRecService.createProfessionalRecord() != null);
		super.unauthenticate();
	}
	@Test
	public void testSaveprofessionalRecord() {
		super.authenticate("handyworker1");
		ProfessionalRecord p;
		p = new ProfessionalRecord();
		p.setCompanyName("");
		p.setRole("");
		Assert.isTrue(this.professionalRecService.save(p) != null);
		super.unauthenticate();
	}
}
