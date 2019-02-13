
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.LoginService;
import utilities.AbstractTest;
import domain.Application;
import domain.Customer;
import domain.FixUpTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestApplicationService extends AbstractTest {

	@Autowired
	private ApplicationService	appService;
	@Autowired
	private FixUpTaskService	futService;


	@Test
	public void testGetAppsByHW() {
		super.authenticate("handyworker1");
		Collection<Application> apps;
		apps = this.appService.getApplicationsByHandyWorker(4060);
		Assert.notEmpty(apps, "The collections must contain any application");
		super.unauthenticate();
	}

	@Test
	public void testGetCustByApp() {
		super.authenticate("handyworker1");
		Application a;
		a = this.appService.findOne(4310);
		Assert.notNull(a);
		Customer c;
		c = this.appService.getCustomerByApplication(a.getId());
		Assert.notNull(c);
		super.unauthenticate();
	}

	@Test
	public void testGetAppsByFixUp() {
		super.authenticate("customer1");
		Collection<Application> apps;
		apps = this.appService.getApplicationsByFixUp(LoginService.getPrincipal().getId(), this.futService.findOne(4282).getId());
		Assert.notNull(apps);
		super.unauthenticate();
	}

	@Test
	public void testSave() {
		super.authenticate("handyworker1");
		Application a;
		a = this.appService.findOne(4298);
		Assert.notNull(a);
		FixUpTask f;
		f = this.futService.findOne(4282);
		Assert.notNull(f);
		Application newer;
		newer = this.appService.save(f, a);
		Assert.notNull(newer);
		Assert.isTrue(a.getFixUpTask().getId() == f.getId());
		super.unauthenticate();

	}
}
