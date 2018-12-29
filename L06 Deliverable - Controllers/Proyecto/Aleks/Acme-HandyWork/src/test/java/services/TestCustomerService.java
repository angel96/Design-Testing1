
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
import utilities.Utiles;
import domain.Box;
import domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestCustomerService extends AbstractTest {

	@Autowired
	private CustomerService	customerService;


	@Test
	public void testCreate() {
		Customer c;
		c = Utiles.createCustomer();
		Assert.notNull(c);
	}
	@Test
	public void update() {
		Customer old;
		Customer newer;
		old = this.customerService.findByUserAccount(4795);
		old.setName("test");
		newer = this.customerService.save(old);
		Assert.isTrue(old.equals(newer));
	}

	@Test
	public void testManageNoSystemBoxes() {
		super.authenticate("customer1");
		final Collection<Box> boxes;
		Customer c;
		c = this.customerService.findByUserAccount(4795);
		//boxes = this.customerService.manageNotSystemBoxes(c);

		//Assert.notNull(boxes);
	}

	/*
	 * @Test
	 * public void testSendMessageCustToCust() {
	 * super.unauthenticate();
	 * super.authenticate("customer1");
	 * Customer sender;
	 * sender = this.customerService.findByUserAccount(2871);
	 * sender.setName("sender");
	 * Customer recipient;
	 * recipient = this.customerService.findByUserAccount(2872);
	 * recipient.setName("recipient");
	 * 
	 * Message stock;
	 * stock = new Message();
	 * stock.setBody("starter body");
	 * Message newer;
	 * newer = new Message();
	 * newer.setBody("new body");
	 * 
	 * this.customerService.sendMessage(sender, recipient, newer);
	 * for (final Box b : recipient.getBoxes())
	 * for (final Message m : b.getMessage())
	 * System.out.println(m.getBody());
	 * }
	 */
	@Test
	public void testFindAll() {
		Collection<Customer> c;
		c = this.customerService.findAll();
		Assert.notNull(c);
	}

}
