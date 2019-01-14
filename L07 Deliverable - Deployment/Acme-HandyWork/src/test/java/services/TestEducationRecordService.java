
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.EducationRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestEducationRecordService extends AbstractTest {

	@Autowired
	EducationRecordService	educationRecService;


	@Test
	public void testFindAll() {
		super.authenticate("handyworker1");
		Assert.isTrue(!(this.educationRecService.findAll().isEmpty()));
		super.unauthenticate();
	}

	@Test
	public void testFindOne() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.educationRecService.findOne(4278) != null);
		super.unauthenticate();
	}
	@Test
	public void testFindeducationRecordByUserId() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.educationRecService.findEducationRecordByUserId(4322).size() > 0);
		super.unauthenticate();
	}
	@Test
	public void testCreateeducationRecord() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.educationRecService.createEducationRecord() != null);
		super.unauthenticate();
	}
	@Test
	public void testSaveeducationRecord() {
		super.authenticate("handyworker1");
		EducationRecord e;
		e = new EducationRecord();
		e.setAttachment("");
		e.setDiplomaTitle("");
		e.setInstitutionDiploma("");
		Assert.isTrue(this.educationRecService.save(e) != null);
		super.unauthenticate();
	}
}
