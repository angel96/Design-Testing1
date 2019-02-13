
package services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Section;
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestTutorialService extends AbstractTest {

	@Autowired
	TutorialService	tutorialService;


	@Test
	public void testFindActorByUserAccountId() {
		super.authenticate("handyworker4");
		Assert.isTrue(this.tutorialService.findActorByUserAccount(4063) != null);
		super.unauthenticate();
	}

	@Test
	public void testFindAll() {
		super.authenticate("handyworker1");
		Assert.isTrue(!(this.tutorialService.findAll().isEmpty()));
		super.unauthenticate();
		Assert.isTrue(!(this.tutorialService.findAll().isEmpty()));
	}

	@Test
	public void testFindAllTutorialsOfHandyLogged() {
		super.authenticate("handyworker1");
		Assert.isTrue(!(this.tutorialService.findAllHandyWorkerlog().isEmpty()));
		super.unauthenticate();
	}

	@Test
	public void testFindOne() {
		super.authenticate("handyworker4");
		Assert.isTrue(this.tutorialService.findOne(4251) != null);
		super.unauthenticate();
	}

	@Test
	public void testCreateTutorial() {
		super.authenticate("handyworker4");
		Assert.isTrue(this.tutorialService.createTutorial() != null);
		super.unauthenticate();
	}

	@Test
	public void testSaveTutorial() {
		super.authenticate("handyworker1");
		Tutorial t;
		t = new Tutorial();
		t.setSection(new ArrayList<Section>());
		t.setTitle("");
		Assert.isTrue(this.tutorialService.save(t) != null);
		super.unauthenticate();
	}

	public void testDeleteEndorsement() {
		super.authenticate("handyworker1");
		Assert.notNull(this.tutorialService.findOne(4251));
		this.tutorialService.delete(4251);
		Assert.isNull(this.tutorialService.findOne(4251));
		super.unauthenticate();
	}
}
