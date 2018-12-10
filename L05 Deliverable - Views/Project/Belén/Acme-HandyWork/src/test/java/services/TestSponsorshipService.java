
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
import domain.Sponsorship;
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestSponsorshipService extends AbstractTest {

	@Autowired
	private SponsorshipService	serviceSponsorship;

	@Autowired
	private SponsorService		serviceSponsor;

	@Autowired
	private TutorialService		serviceTutorial;


	@Test
	public void testGetAll() {
		Assert.isTrue(this.serviceSponsorship.findAll().size() >= 1);
	}
	@Test
	public void testGetOne() {
		Assert.notNull(this.serviceSponsorship.findOne(3098));
	}
	@Test
	public void testCreateSponsorship() {
		super.authenticate("sponsor1");
		Sponsorship sponsorShip, saved;
		Tutorial t;
		t = this.serviceTutorial.findOne(3007);
		sponsorShip = Utiles.createSponsorship(this.serviceSponsor.findById(3012), t);
		saved = this.serviceSponsorship.add(sponsorShip);
		Assert.notNull(saved);
		super.unauthenticate();
	}
	@Test
	public void testUpdateSponsorship() {
		super.authenticate("sponsor1");
		Sponsorship sponsorship;
		sponsorship = this.serviceSponsorship.findOne(3098);
		sponsorship.setUrlBanner("http://www.tuenti.com");
		Assert.notNull(this.serviceSponsorship.update(sponsorship));
		super.unauthenticate();
	}
	@Test
	public void testDeleteSponsorship() {
		Assert.notNull(this.serviceSponsorship.findOne(3098));
		this.serviceSponsorship.delete(3098);
		Assert.isNull(this.serviceSponsorship.findOne(3098));
	}
}
