
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestProfileService extends AbstractTest {

	@Autowired
	private ProfileService	profileService;


	//TEST SAVE PROFILE
	@Test
	public void testSaveProfile() {
		super.authenticate("referee1");
		Profile p;
		p = new Profile();
		p.setLink("http://www.twitter.com");
		p.setNick("paco");
		p.setSocialNetworkName("twitter");
		Profile saved;
		saved = this.profileService.save(p);
		Assert.notNull(saved, "El profile no se guardó correctamente");
		super.unauthenticate();
	}
	//TEST DELETE PROFILE
	@Test
	public void testDeleteProfile() {
		super.authenticate("handyWorker1");
		this.profileService.deleteProfile(4075);
		Assert.isNull(this.profileService.findOne(4075));
		super.unauthenticate();
	}
	//TEST GET ACTOR BY USER
	@Test
	public void testGetActorByUser() {
		Actor a;
		a = this.profileService.getActorByUser(4050);
		Assert.isTrue(a.getId() == 4261, "El actor y el user no concuerdan");
	}
	//TEST FIN ONE PROFILE
	@Test
	public void testFindOneProfile() {
		super.authenticate("referee3");
		Assert.notNull(this.profileService.findOne(4084), "No se puede encontrar el profile");
		super.unauthenticate();
	}
	//TEST FIND ALL PROFILES
	@Test
	public void testFindAllProfile() {
		super.authenticate("referee4");
		Assert.isTrue(this.profileService.findAll().size() > 0);
		super.unauthenticate();
	}

}
