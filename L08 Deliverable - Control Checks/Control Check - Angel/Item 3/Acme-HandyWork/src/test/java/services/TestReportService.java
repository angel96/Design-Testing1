
package services;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.LoginService;
import utilities.AbstractTest;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestReportService extends AbstractTest {

	@Autowired
	private ReportService	reportService;


	//LISTING THE REPORTS BY ACTORS
	@Test
	public void testListReportsByReferee() {
		super.authenticate("referee1");
		Assert.notNull(this.reportService.findReportsByReferee(this.reportService.getActorByUser(LoginService.getPrincipal().getId())));
		super.unauthenticate();
	}

	@Test
	public void testListReportsByHandy() {
		super.authenticate("handyWorker1");
		Assert.notNull(this.reportService.findReportsByhandyWorkerId(this.reportService.findHandyByUserAccountId(LoginService.getPrincipal().getId()).getId()));
		super.unauthenticate();
	}

	@Test
	public void testListReportsByCustomer() {
		super.authenticate("customer1");
		Assert.notNull(this.reportService.findReportsByCustomerId(this.reportService.findCustomerByUserAccountId(LoginService.getPrincipal().getId()).getId()));
		super.unauthenticate();
	}
	//TEST SAVE THE REPORT
	@Test
	public void saveReport() {
		super.authenticate("referee1");
		Report r;
		r = new Report();
		r.setAttachments(new ArrayList<String>());
		r.setDescription("eeee");
		r.setFinalMode(true);
		Report saved;
		saved = this.reportService.save(r, 4266);
		Assert.notNull(saved, "El report no se guardó correctamente");
		super.unauthenticate();
	}
	//TEST FIND ONE
	@Test
	public void testFindOneReport() {
		super.authenticate("referee3");
		Assert.notNull(this.reportService.findOne(4352), "No se puede encontrar el report");
		super.unauthenticate();
	}
	//TEST DELETE REPORT
	@Test
	public void testDeleteReport() {
		super.authenticate("referee3");
		this.reportService.delete(4352);
		Assert.isNull(this.reportService.findOne(4352));
		super.unauthenticate();
	}
}
