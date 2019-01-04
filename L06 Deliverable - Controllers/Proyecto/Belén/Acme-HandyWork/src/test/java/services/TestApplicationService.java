
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

	@Autowired
	private FixUpTaskService	fService;


	//	@Test
	//	public void testCreate() {
	//		super.authenticate("handyworker1");
	//		Application created, one;
	//		one = Utiles.createApplication();
	//		one.setMoment(Utiles.convertDate(2018, 10, 01));
	//		one.setMomentElapsed(Utiles.convertDate(2018, 12, 02));
	//		final List<String> comentarios = new ArrayList<>();
	//		comentarios.add("Comentarios creados");
	//		one.setComments(comentarios);
	//		one.setOfferedPrice(20.0);
	//		one.setFixUpTask(this.fService.findOne(3046));
	//		created = this.applicationService.save(this.fService.findOne(5069), one);
	//		super.unauthenticate();
	//	}
	@Test
	public void testUpdate() {
		super.authenticate("handyworker1");
		Application a;
		final Application saved;
		a = this.applicationService.findOne(5078);
		a.setOfferedPrice(40.0);

		super.authenticate(null);
	}
	@Test
	public void testUpdateStatus() {
		super.authenticate("customer1");
		Application a, saved;
		a = this.applicationService.findOne(5070);
		a.setStatus("accepted");
		saved = this.applicationService.updateStatus(Utiles.createCreditCard(), a);
		Assert.notNull(saved);
		super.authenticate(null);
	}
	@Test
	public void testAddComment() {
		super.authenticate("customer1");
		Application a;
		a = this.applicationService.findOne(5070);
		a.setStatus("accepted");
		this.applicationService.addComment("Comentario", a);
		super.authenticate(null);
	}

}
