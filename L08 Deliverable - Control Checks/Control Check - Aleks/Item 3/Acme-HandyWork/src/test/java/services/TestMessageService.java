
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Box;
import domain.Mesage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestMessageService extends AbstractTest {

	@Autowired
	MesageService	messageService;

	@Autowired
	TutorialService	tutorialService;

	@Autowired
	BoxService		boxService;


	@Test
	public void testGetMessagesByBox() {
		super.authenticate("customer1");
		Assert.isTrue(!(this.messageService.getMessagesByBox(4133).isEmpty()));
		super.unauthenticate();
	}

	@Test
	public void testFindOne() {
		super.authenticate("handyworker4");
		Assert.isTrue(this.messageService.findOne(4337) != null);
		super.unauthenticate();
	}

	@Test
	public void testCreateMessage() {
		super.authenticate("customer1");
		Assert.isTrue(this.messageService.createMessage(this.tutorialService.findActorByUserAccount(4055)) != null);
		super.unauthenticate();
	}

	@Test
	public void testSendMessage() {
		super.authenticate("customer1");
		Mesage m;
		m = this.messageService.findOne(4337);
		Assert.isTrue(this.messageService.sendMessage(m) != null);
		super.unauthenticate();
	}

	@Test
	public void testMoveToMessage() {
		super.authenticate("sponsor1");
		Assert.isTrue(this.messageService.moveTo("Trash Box", this.messageService.findOne(4337)) == 4136);
		super.unauthenticate();
	}
	@Test
	public void testDeleteMessage() {
		super.authenticate("handyworker4");
		Assert.isTrue(this.messageService.findOne(4346) != null);
		this.messageService.deleteFromSystem(this.messageService.findOne(4346));
		Actor a;
		a = this.tutorialService.findActorByUserAccount(4063);
		Box b;
		b = this.boxService.getActorTrashBox(a.getId());
		System.out.println(b.getMessage().contains(this.messageService.findOne(4346)));
		Assert.isTrue(!(b.getMessage().contains(this.messageService.findOne(4346))));
		super.unauthenticate();

	}
}
