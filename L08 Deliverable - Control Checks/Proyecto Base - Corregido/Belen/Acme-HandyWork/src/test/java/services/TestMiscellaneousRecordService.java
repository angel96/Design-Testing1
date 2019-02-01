
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.MiscellaneousRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestMiscellaneousRecordService extends AbstractTest {

	@Autowired
	MiscellaneousRecordService	miscellaneousRecService;


	@Test
	public void testFindAll() {
		super.authenticate("handyworker1");
		Assert.isTrue(!(this.miscellaneousRecService.findAll().isEmpty()));
		super.unauthenticate();
	}

	@Test
	public void testFindOne() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.miscellaneousRecService.findOne(4280) != null);
		super.unauthenticate();
	}
	@Test
	public void testFindMiscellaneousRecordByUserId() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.miscellaneousRecService.findMiscellaneousRecordByUserId(4322).size() > 0);
		super.unauthenticate();
	}
	@Test
	public void testCreateMiscellaneousRecord() {
		super.authenticate("handyworker1");
		Assert.isTrue(this.miscellaneousRecService.createMiscellaneousRecord() != null);
		super.unauthenticate();
	}
	@Test
	public void testSaveMiscellaneousRecord() {
		super.authenticate("handyworker1");
		MiscellaneousRecord m;
		m = new MiscellaneousRecord();
		m.setAttachment("");
		m.setTitle("");
		Assert.isTrue(this.miscellaneousRecService.save(m) != null);
		super.unauthenticate();
	}
}
