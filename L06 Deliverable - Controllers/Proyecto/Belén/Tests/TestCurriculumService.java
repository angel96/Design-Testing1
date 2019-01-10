
package services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.ProfessionalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestCurriculumService extends AbstractTest {

	@Autowired
	CurriculumService				curriculumService;

	@Rule
	public final ExpectedException	exception	= ExpectedException.none();


	@Test
	public void testHandyByUserAccount() {
		super.authenticate("handyworker4");
		Assert.isTrue(this.curriculumService.getHandyByUserAccount(4063).getId() == 4328);
		super.unauthenticate();
	}

	@Test
	public void testLinkedInProfile() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.curriculumService.getLinkedInProfile(4322) != null);
		super.unauthenticate();
	}

	@Test
	public void testFindOne() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.curriculumService.findOne(4281) != null);
		super.unauthenticate();
	}

	@Test
	public void testCreateCurriculum() {
		super.authenticate("handyworker2");
		this.exception.expect(IllegalArgumentException.class);
		Assert.isNull(this.curriculumService.createCurriculum().getId());
		super.unauthenticate();
	}
	@Test
	public void testSaveCurriculum() {
		super.authenticate("handyworker1");
		Curriculum c;
		c = new Curriculum();
		c.setEducationRecord(new ArrayList<EducationRecord>());
		c.setProfessionalRecord(new ArrayList<ProfessionalRecord>());
		c.setEndorserRecord(new ArrayList<EndorserRecord>());
		c.setMiscellaneousRecord(new ArrayList<MiscellaneousRecord>());
		Assert.isTrue(this.curriculumService.save(c) != null);
		super.unauthenticate();
	}

}
