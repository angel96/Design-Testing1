
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
import domain.Box;
import domain.HandyWorker;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestHandyWorkerService extends AbstractTest {

	@Autowired
	private HandyWorkerService	handyService;


	@Test
	public void testFindAll() {
		Collection<HandyWorker> handys;
		handys = this.handyService.findAll();
		Assert.notNull(handys);
	}

	@Test
	public void testUpdate() {
		HandyWorker old;
		HandyWorker newer;
		old = this.handyService.findByUserAccount(2876);
		old.setName("test");
		newer = this.handyService.update(old);
		Assert.isTrue(old.equals(newer));
	}

	@Test
	public void testManageNoSystemBoxes() {
		super.authenticate("handyworker1");
		Collection<Box> boxes;
		HandyWorker h;
		h = this.handyService.findByUserAccount(2876);
		boxes = this.handyService.manageNotSystemBox(h);
		Assert.notNull(boxes);
	}

	@Test
	public void testSendMessageHWtoHW() {
		super.unauthenticate();
		super.authenticate("handyworker1");
		HandyWorker sender;
		sender = this.handyService.findByUserAccount(2876);
		sender.setName("sender");
		HandyWorker recipient;
		recipient = this.handyService.findByUserAccount(2877);
		recipient.setName("recipient");

		Message stock;
		stock = new Message();
		stock.setBody("started body");
		Message newer;
		newer = new Message();
		newer.setBody("new body");

		this.handyService.sendMessage(sender, recipient, newer);
		for (final Box b : recipient.getBoxes())
			for (final Message m : b.getMessage())
				System.out.println(m.getBody());
	}

}
