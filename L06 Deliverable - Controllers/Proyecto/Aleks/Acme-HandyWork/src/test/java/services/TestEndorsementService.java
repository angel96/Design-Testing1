
package services;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import utilities.Utiles;
import domain.Endorsement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestEndorsementService extends AbstractTest {

	@Autowired
	private EndorsementService	serviceEndorsement;

	@Autowired
	private CustomerService		serviceCustomer;

	@Autowired
	private HandyWorkerService	serviceHandyWorker;


	@Test
	public void getAll() {
		Assert.isTrue(this.serviceEndorsement.findAll().size() >= 1);
	}
	@Test
	public void getOne() {
		Assert.notNull(this.serviceEndorsement.findOne(5103));
	}

	@Test
	public void testCreate() {
		super.authenticate("customer1");
		Endorsement endorsement;
		endorsement = Utiles.createEndorsement(this.serviceCustomer.findOne(3073), this.serviceHandyWorker.findOne(3078));
		endorsement.setComments(Arrays.asList("comment1", "comment2", "comment3"));
		endorsement.setMoment(Utiles.convertDate(2018, 05, 04));
		System.out.println(this.serviceEndorsement.add(endorsement).getId());
		Assert.notNull(this.serviceEndorsement.add(endorsement));
		super.unauthenticate();
	}
	@Test
	public void testUpdate() {
		super.authenticate("customer1");
		Endorsement modified;
		modified = this.serviceEndorsement.findOne(5102);
		modified.setMoment(Utiles.convertDate(2018, 06, 01));
		Assert.notNull(this.serviceEndorsement.add(modified));
		super.unauthenticate();
	}
	@Test
	public void testDelete() {
		Assert.notNull(this.serviceEndorsement.findOne(5099));
		this.serviceEndorsement.delete(5099);
		Assert.isNull(this.serviceEndorsement.findOne(5099));
	}
}
