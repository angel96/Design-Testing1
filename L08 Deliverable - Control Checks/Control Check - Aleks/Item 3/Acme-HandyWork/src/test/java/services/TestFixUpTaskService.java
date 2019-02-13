
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
import domain.FixUpTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestFixUpTaskService extends AbstractTest {

	@Autowired
	private FixUpTaskService	futService;


	@Test
	public void testGetFixUpsByHw() {
		super.authenticate("handyworker1");
		Collection<FixUpTask> fut;
		fut = this.futService.getFixUpTasksByHandyWorker(LoginService.getPrincipal().getId());
		Assert.notNull(fut);
		super.unauthenticate();
	}

	@Test
	public void testFindAllByUser() {
		super.authenticate("customer1");
		Collection<FixUpTask> futs;
		futs = this.futService.findAllByUser(LoginService.getPrincipal().getId());
		Assert.notNull(futs);
		super.unauthenticate();
	}
	@Test
	public void testSave() {
		super.authenticate("customer1");
		FixUpTask f;
		FixUpTask newer;
		f = this.futService.findOne(4282);
		f.setAddress("Prueba");
		newer = this.futService.save(f);
		Assert.notNull(newer);
		super.unauthenticate();
	}

	@Test
	public void testDelete() {
		super.authenticate("customer1");
		FixUpTask f;
		f = this.futService.findOne(4282);
		this.futService.delete(f.getId());
	}
}
