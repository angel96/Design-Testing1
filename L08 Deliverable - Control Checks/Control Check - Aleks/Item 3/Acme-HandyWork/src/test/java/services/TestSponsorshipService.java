
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Sponsor;
import domain.Sponsorship;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestSponsorshipService extends AbstractTest {

	@Autowired
	SponsorshipService	sponsorshipService;

	@Autowired
	TutorialService		tutorialService;


	@Test
	public void testFindAll() {
		super.authenticate("sponsor1");
		Assert.isTrue(!(this.sponsorshipService.findAll().isEmpty()));
		super.unauthenticate();
	}

	@Test
	public void testFindOne() {
		super.authenticate("sponsor1");
		Assert.isTrue(this.sponsorshipService.findOne(4347) != null);
		super.unauthenticate();
	}

	@Test
	public void testCreateSponsorship() {
		super.authenticate("sponsor1");
		Assert.isTrue(this.sponsorshipService.createSponsorship((Sponsor) this.tutorialService.findActorByUserAccount(4256), this.tutorialService.findOne(4251)) != null);
		super.unauthenticate();
	}

	@Test
	public void testSaveSponsorship() {
		super.authenticate("sponsor1");
		Sponsorship s;
		s = new Sponsorship();
		s.setLinkTPage("");
		s.setUrlBanner("");
		Assert.isTrue(this.sponsorshipService.save(s) != null);
		super.unauthenticate();
	}

	@Test
	public void testDeleteSponsorship() {
		super.authenticate("sponsor1");
		Assert.notNull(this.sponsorshipService.findOne(4347));
		this.sponsorshipService.delete(4347);
		Assert.isNull(this.sponsorshipService.findOne(4347));
		super.unauthenticate();
	}

	@Test
	public void testDeleteAllSponsorships() {
		super.authenticate("sponsor1");
		Collection<Sponsorship> s;
		s = this.sponsorshipService.findAll();
		this.sponsorshipService.delete(s);
		Assert.isNull(this.sponsorshipService.findOne(4347));
	}
}
