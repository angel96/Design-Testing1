
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Aolet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestAoletService extends AbstractTest {

	@Autowired
	private AoletService	appService;


	@Test
	public void testUpdate() {
		super.authenticate("customer1");
		final Aolet a = this.appService.findOne(4342);
		a.setBody("Body cambiado");

		Assert.notNull(this.appService.save(a, 0));
		super.unauthenticate();
	}
}
