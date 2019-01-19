
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Section;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestSectionService extends AbstractTest {

	@Autowired
	SectionService	sectionService;

	@Autowired
	TutorialService	tutorialService;


	@Test
	public void testFindAll() {
		super.authenticate("handyworker1");
		Assert.isTrue(!(this.sectionService.findAll().isEmpty()));
		super.unauthenticate();
		Assert.isTrue(!(this.sectionService.findAll().isEmpty()));
	}

	@Test
	public void testFindOne() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.sectionService.findOne(4240) != null);
		super.unauthenticate();
	}

	@Test
	public void testCreateSection() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.sectionService.createSection() != null);
		super.unauthenticate();
	}

	@Test
	public void testFindTutorialBySection() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.sectionService.findBySection(4240).getId() == 4251);
		super.unauthenticate();
	}

	@Test
	public void testSaveSection() {
		super.authenticate("handyworker1");
		Section s;
		s = new Section();
		s.setText("");
		s.setTitle("");
		Assert.isTrue(this.sectionService.save(this.tutorialService.findOne(4251), s) != null);
		super.unauthenticate();
	}

	@Test
	public void testDeleteSection() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.sectionService.findOne(4240) != null);
		this.sectionService.delete(this.tutorialService.findOne(4251), this.sectionService.findOne(4240));
		Assert.isNull(this.sectionService.findOne(4240));
		super.unauthenticate();
	}

}
