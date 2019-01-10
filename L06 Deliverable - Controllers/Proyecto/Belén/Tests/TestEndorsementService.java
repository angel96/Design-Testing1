
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Endorsement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestEndorsementService extends AbstractTest {

	@Autowired
	EndorsementService	endorsementService;


	@Test
	public void testFindAll() {
		super.authenticate("handyworker1");
		Assert.isTrue(!(this.endorsementService.findAll().isEmpty()));
		super.unauthenticate();
		super.authenticate("customer1");
		Assert.isTrue(!(this.endorsementService.findAll().isEmpty()));
		super.unauthenticate();
	}
	@Test
	public void testFindOne() {
		super.authenticate("handyworker4");
		Assert.isTrue(this.endorsementService.findOne(4335) != null);
		super.unauthenticate();
		super.authenticate("customer4");
		Assert.isTrue(this.endorsementService.findOne(4335) != null);
		super.unauthenticate();
	}

	@Test
	public void testFindActorByUserId() {
		super.authenticate("handyworker4");
		Assert.isTrue(this.endorsementService.findActorByUserId(4063) != null);
		super.unauthenticate();
		super.authenticate("customer4");
		Assert.isTrue(this.endorsementService.findActorByUserId(4058) != null);
		super.unauthenticate();
	}

	@Test
	//findEndorsableByUserId is tested too
	public void testFindEndorsementsByActorSended() {
		super.authenticate("handyworker4");
		Assert.isTrue(!(this.endorsementService.findEndorsementsByActorSended(this.endorsementService.findEndorsableByUserId(4063)).isEmpty()));
		super.unauthenticate();
		super.authenticate("customer4");
		Assert.isTrue(!(this.endorsementService.findEndorsementsByActorSended(this.endorsementService.findEndorsableByUserId(4058)).isEmpty()));
		super.unauthenticate();
	}

	@Test
	public void testCreateEndorsement() {
		super.authenticate("handyworker4");
		Assert.isTrue(this.endorsementService.createEndorsement(4320) != null);
		super.unauthenticate();
		super.authenticate("customer4");
		Assert.isTrue(this.endorsementService.createEndorsement(4328) != null);
		super.unauthenticate();
	}

	@Test
	public void testSaveEndorsement() {
		super.authenticate("handyworker4");
		Endorsement x;
		x = new Endorsement();
		x.setUserReceived(this.endorsementService.findEndorsableByUserId(4058));
		x.setUserSended(this.endorsementService.findEndorsableByUserId(4063));
		Assert.isTrue(this.endorsementService.save(x, this.endorsementService.findEndorsableByUserId(4058).getId()) != null);
		super.unauthenticate();
		super.authenticate("customer4");
		Endorsement e;
		e = new Endorsement();
		e.setUserReceived(this.endorsementService.findEndorsableByUserId(4063));
		e.setUserSended(this.endorsementService.findEndorsableByUserId(4058));
		Assert.isTrue(this.endorsementService.save(e, this.endorsementService.findEndorsableByUserId(4063).getId()) != null);
		super.unauthenticate();
	}

	@Test
	public void testDeleteEndorsement() {
		super.authenticate("handyworker4");
		Assert.notNull(this.endorsementService.findOne(163840));
		this.endorsementService.delete(163840);
		Assert.isNull(this.endorsementService.findOne(163840));
		super.unauthenticate();
		super.authenticate("customer4");
		Assert.notNull(this.endorsementService.findOne(753664));
		this.endorsementService.delete(753664);
		Assert.isNull(this.endorsementService.findOne(753664));
		super.unauthenticate();
	}

}
