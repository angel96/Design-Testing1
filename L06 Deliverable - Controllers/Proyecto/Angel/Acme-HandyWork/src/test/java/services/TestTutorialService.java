
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import utilities.Utiles;
import domain.Section;
import domain.Sponsorship;
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestTutorialService extends AbstractTest {

	@Autowired
	private TutorialService	tutorialService;

	@Autowired
	private SponsorService	sponsorService;


	@Test
	public void getAll() {
		Assert.isTrue(this.tutorialService.findAll().size() >= 1);
	}
	@Test
	public void getOne() {
		Assert.notNull(this.tutorialService.findOne(5023));
	}

	public void getTutorialsByHandyWorker() {
		super.authenticate("handyworker1");
		//Assert.isTrue(this.tutorialService.getTutorialsByHandyWorker(LoginService.getPrincipal().getId()).size() >= 1);
		super.unauthenticate();
	}
	@Test
	public void createTutorial() {
		super.authenticate("handyworker1");
		Assert.notNull(this.tutorialService.save(Utiles.createTutorial()));
		super.unauthenticate();
	}
	@Test
	public void updateTutorial() {
		super.authenticate("handyworker1");
		Tutorial t, updated;
		t = this.tutorialService.findOne(5023);
		t.setTitle("Tutorial with title updated");
		updated = this.tutorialService.save(t);
		Assert.notNull(updated);
		System.out.println(updated.getTitle());
		super.unauthenticate();
	}
	@Test
	public void addSectionToTutorial() {
		super.authenticate("handyworker1");

		Section s1;
		s1 = Utiles.createSection();
		s1.setTitle("Titulo s1");
		s1.setText("Test 1");
		Section s2;
		s2 = Utiles.createSection();
		s2.setTitle("Titulo s2");
		s1.setText("Test 2");

		Tutorial t;
		t = this.tutorialService.findOne(5023);
		System.out.println(t.getSection().size());
		final Section update1 = this.tutorialService.addSectionToTutorial(t, s1);
		final Section update2 = this.tutorialService.addSectionToTutorial(t, s2);

		Assert.isTrue(t.getSection().contains(update1));
		Assert.isTrue(t.getSection().contains(update2));

		super.authenticate(null);
	}

	@Test
	public void addSponsoshipToTutorial() {
		super.authenticate("sponsor1");
		Tutorial t1;
		t1 = this.tutorialService.findOne(5023);

		Sponsorship s1, savedS;
		s1 = Utiles.createSponsorship(this.sponsorService.findById(5118), t1);

		s1.setCreditCard(Utiles.createCreditCard());
		s1.setUrlBanner("https://www.google.com");
		s1.setLinkTPage("https://www.google.com");

		savedS = this.tutorialService.addSponsorshipToTutorial(s1);

		Assert.isTrue(t1.getSponsorship().contains(savedS));
		super.unauthenticate();
	}
	@Test
	public void removeTutorial() {
		super.authenticate("handyworker1");
		this.tutorialService.delete(5023);
		Assert.isNull(this.tutorialService.findOne(5023));
		super.unauthenticate();
	}
}
