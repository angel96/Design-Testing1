
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import utilities.Utiles;
import domain.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestApplicationService extends AbstractTest {

	@Autowired
	private ApplicationService	applicationService;


	@Test
	public void testCreate() {
		super.authenticate("customer");
		Application saved, one;
		one = this.applicationService.findOne(1275);

		saved = this.applicationService.save(one);
		super.unauthenticate();
	}

	@Test
	public void testUpdateStatus() {
		super.authenticate("customer");
		Application a, saved;
		a = this.applicationService.findOne(1275);
		a.setStatus("accepted");
		saved = this.applicationService.updateStatus(Utiles.createCreditCard(), a);
		Assert.notNull(saved);
		super.authenticate(null);
	}

}
