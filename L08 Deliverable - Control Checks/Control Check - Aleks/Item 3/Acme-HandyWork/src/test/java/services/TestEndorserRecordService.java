
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.EndorserRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestEndorserRecordService extends AbstractTest {

	@Autowired
	EndorserRecordService	endorserRecService;


	@Test
	public void testFindAll() {
		super.authenticate("handyworker1");
		Assert.isTrue(!(this.endorserRecService.findAll().isEmpty()));
		super.unauthenticate();
	}

	@Test
	public void testFindOne() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.endorserRecService.findOne(4279) != null);
		super.unauthenticate();
	}
	@Test
	public void testFindEndorserRecordByUserId() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.endorserRecService.findEndorserRecordByUserId(4322).size() > 0);
		super.unauthenticate();
	}
	@Test
	public void testCreateEndorserRecord() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.endorserRecService.createEndorserRecord() != null);
		super.unauthenticate();
	}
	@Test
	public void testSaveEndorserRecord() {
		super.authenticate("handyworker1");
		EndorserRecord e;
		e = new EndorserRecord();
		e.setFullNameEndorser("");
		e.setLinkedin("");
		e.setPhone("");
		Assert.isTrue(this.endorserRecService.save(e) != null);
		super.unauthenticate();
	}
}
