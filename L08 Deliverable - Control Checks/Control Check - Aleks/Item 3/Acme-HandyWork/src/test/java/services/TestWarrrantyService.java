
package services;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestWarrrantyService extends AbstractTest {

	@Autowired
	private WarrantyService			serviceWarranty;

	@Rule
	public final ExpectedException	exception	= ExpectedException.none();


	@Test
	public void testGetAll() {
		Assert.isTrue(this.serviceWarranty.findAll().size() >= 0);
	}
	@Test
	public void testGetById() {
		Assert.notNull(this.serviceWarranty.findOne(4127));
	}
	@Test
	public void testCreate() {
		super.authenticate("admin1");
		Assert.isTrue(this.serviceWarranty.createWarranty() != null);
		super.unauthenticate();
	}

	// It can be modified due to its draftMode value

	@Test
	public void testUpdate1() {
		super.authenticate("admin1");
		Warranty w;
		w = this.serviceWarranty.findOne(4127);
		w.setTitle("Testeando garantia");
		//Assert.notNull(this.serviceWarranty.updateWarranty(w));
		super.authenticate(null);
	}
}
