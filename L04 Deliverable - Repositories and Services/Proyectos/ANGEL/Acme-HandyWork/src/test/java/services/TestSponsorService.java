
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestSponsorService extends AbstractTest {

	@Autowired
	private SponsorService	serviceSponsor;


	@Test
	public void testGetAllSponsors() {
		Assert.isTrue(this.serviceSponsor.findAll().size() >= 1);
	}

	public void testCreateSponsor() {

		Sponsor s, saved;
		s = this.serviceSponsor.create();
		saved = this.serviceSponsor.addSponsor(s);

	}
	@Test
	public void testUpdateSponsor() {

	}
	@Test
	public void testDeleteSponsor() {

	}
}
