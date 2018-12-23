
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;
import domain.Box;
import domain.Customer;
import domain.HandyWorker;
import domain.Message;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestAdministratorService extends AbstractTest {

	@Autowired
	private AdministratorService	adminService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private SponsorService			sponsorService;


	@Test
	//R9.2
	public void testUpdate() {
		super.authenticate("admin1");
		Administrator actual;
		Administrator newer;
		actual = this.adminService.findOne(2866);
		System.out.println(actual);
		actual.setName("test");
		newer = this.adminService.update(actual);
		Assert.notNull(newer);
		System.out.println(newer);
	}

	@Test
	//9.2
	public void testSendMessageAdminToAdmin() {
		super.unauthenticate();
		super.authenticate("admin1");
		Administrator sender;
		sender = this.adminService.findOne(3017);
		sender.setName("sender");
		Administrator recipient;
		recipient = this.adminService.findOne(3018);
		recipient.setName("recipient");

		Message stock;
		stock = new Message();
		stock.setBody("starter body");
		Message newer;
		newer = new Message();
		newer.setBody("new body");

		this.adminService.sendMessage(sender, recipient, newer);
		for (final Box b : recipient.getBoxes())
			for (final Message m : b.getMessage())
				System.out.println(m.getBody());
	}

	@Test
	//R9.2
	public void testSendMessageAdminToHandyWorker() {
		super.unauthenticate();
		super.authenticate("admin1");
		Administrator sender;
		sender = this.adminService.createAnotherAdministrator();
		sender.setName("sender");
		Administrator recipient;
		recipient = this.adminService.createAnotherAdministrator();
		recipient.setName("recipient");

		Message stock;
		stock = new Message();
		stock.setBody("starter body");
		Message newer;
		newer = new Message();
		newer.setBody("new body");
		for (final Box b : sender.getBoxes())
			if (b.getName().equals("entry")) {
				Collection<Message> mes;
				mes = b.getMessage();
				mes.add(stock);
			}

		for (final Box b : recipient.getBoxes())
			if (b.getName().equals("entry")) {
				Collection<Message> mes;
				mes = b.getMessage();
				mes.add(stock);
			}

		this.adminService.sendMessage(sender, recipient, newer);
		for (final Box b : recipient.getBoxes())
			for (final Message m : b.getMessage())
				System.out.println(m.getBody());
	}

	@Test
	//R9.4
	public void testManageNoSystemBoxes() {
		super.unauthenticate();
		super.authenticate("admin1");
		Administrator admin;
		admin = this.adminService.findOne(3017);
		Collection<Box> boxes;
		boxes = this.adminService.manageNotSystemBoxes(admin);
		for (final Box b : boxes)
			System.out.println(b.getName());

	}

	@Test
	//R12.1
	public void testAddAnotherAdmin() {
		super.authenticate("admin1");
		Administrator admin;
		admin = this.adminService.createAnotherAdministrator();
		Assert.notNull(admin);
	}

	@Test
	//R12.4
	public void testBroadcastMessage() {
		super.authenticate("admin1");
		Administrator admin;
		admin = this.adminService.findOne(3017);
		Message m;
		m = new Message();
		m.setBody("new message body");
		this.adminService.broadcastMessage(admin, m);
		final Administrator prueba = this.adminService.findOne(3018);
		for (final Box b : prueba.getBoxes())
			if (b.getName().equals("entry"))
				for (final Message me : b.getMessage())
					System.out.println(me.getBody());
	}

	@Test
	//R12.5 - 9
	public void test10PerCentCustomers() {
		super.authenticate("admin1");
		Administrator admin;
		admin = this.adminService.findOne(3017);
		final Collection<Customer> customers;
		customers = this.adminService.findCustomersWith10PerCentMoreFixUpPublishedThanAvgOrderApps(admin);
		Assert.notNull(customers);
	}

	@Test
	//R12.5 - 10
	public void test10PerCentHandyWorker() {
		super.authenticate("admin1");
		Administrator admin;
		admin = this.adminService.findOne(3017);
		final Collection<HandyWorker> handyWorker;
		handyWorker = this.adminService.findHandyWorkersWith10PerCentMoreAppsPublishedThanAvgOrderApps(admin);
		Assert.notNull(handyWorker);
	}

	//	@Test
	public void testBanActor() {
		//R38.3
		super.authenticate("admin1");
		Administrator admin;
		admin = this.adminService.findOne(3017);
		Customer c;
		c = this.customerService.findByUserAccount(2871);
		Sponsor r;
		r = this.sponsorService.findById(3013);

		this.adminService.banCustomer(admin, c);
		c = this.customerService.findByUserAccount(2871);
		System.out.println(c.isBan());

		this.adminService.banSponsor(admin, r);
		r = this.sponsorService.findById(3013);
		System.out.println(r.isBan());
	}

	//	@Test
	public void unBanActor() {
		//R38.4
		super.authenticate("admin1");
		final Sponsor sponsor;
		sponsor = this.sponsorService.findById(4791);
		System.out.println(sponsor.isBan());
	}
	@Test
	//R38.5 - 4
	public void testTopThreeCustomers() {
		super.authenticate("admin1");
		Administrator admin;
		admin = this.adminService.findOne(3017);
		Collection<Customer> top3;
		top3 = this.adminService.topThreeCustomerOrderByComplaints(admin);
		Assert.notNull(top3);
		for (final Customer c : top3)
			System.out.println(c.getName());
	}
	@Test
	//R38.5 - 5
	public void testTopThreeHW() {
		super.authenticate("admin1");
		Administrator admin;
		admin = this.adminService.findOne(3017);
		Collection<HandyWorker> top3;
		top3 = this.adminService.topThreeHandyWorkerOrderByComplaints(admin);
		Assert.notNull(top3);
		for (final HandyWorker hw : top3)
			System.out.println(hw.getName());
	}

}
