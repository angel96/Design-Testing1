
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestTutorialService extends AbstractTest {

	@Autowired
	private TutorialService	tutorialService;


	@Test
	public void getAll() {
		Assert.isTrue(this.tutorialService.findAll().size() >= 1);
	}
	@Test
	public void getOne() {
		Assert.notNull(this.tutorialService.findOne(1270));
	}
	@Test
	public void getTutorialsByHandyWorker() {
		super.authenticate("handyworker");
		Assert.isTrue(this.tutorialService.getTutorialsByHandyWorker(LoginService.getPrincipal().getId()).size() >= 1);
		super.unauthenticate();
	}

	@Test
	public void createTutorial() {

	}
	@Test
	public void updateTutorial() {

	}
	@Test
	public void addSectionToTutorial() {

	}
	@Test
	public void removeSectionFromTutorial() {

	}
	@Test
	public void addSponsoshipToTutorial() {

	}
	@Test
	public void removeSponsorshipFromTutorial() {

	}
	@Test
	public void removeTutorial() {

	}
}
