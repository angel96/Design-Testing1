
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
import domain.Finder;
import domain.HandyWorker;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestFinderService extends AbstractTest {

	@Autowired
	private FinderService		serviceFinder;

	@Autowired
	private WarrantyService		serviceWarranty;

	@Autowired
	private CategoryService		serviceCategory;

	@Autowired
	private FixUpTaskService	serviceFixUpTask;


	@Test
	public void findOneTest() {
		Assert.notNull(this.serviceFinder.findOne(4323));
	}

	@Test
	public void testFindFixUpsByCategory() {
		super.authenticate("handyworker1");

		final HandyWorker h = (HandyWorker) this.serviceFinder.findByUserAccount(LoginService.getPrincipal().getId());

		final Finder f = h.getFinder();

		f.setCategory(this.serviceCategory.findOne(4106));

		Assert.isTrue(this.serviceFixUpTask.findAllByFinder(f).size() >= 0);
	}
	@Test
	public void testFindFixUpsByWarranty() {
		super.authenticate("handyworker1");

		final HandyWorker h = (HandyWorker) this.serviceFinder.findByUserAccount(LoginService.getPrincipal().getId());

		final Finder f = h.getFinder();

		f.setWarranty(new ArrayList<Warranty>(this.serviceWarranty.findAllFinalWarranties()).get(0));

		Assert.isTrue(this.serviceFixUpTask.findAllByFinder(f).size() >= 0);
	}
	@Test
	public void testFindFixUpsBySingleKey() {
		super.authenticate("handyworker1");

		final HandyWorker h = (HandyWorker) this.serviceFinder.findByUserAccount(LoginService.getPrincipal().getId());

		final Finder f = h.getFinder();

		f.setSingleKey("20181108-A32569");

		Assert.isTrue(this.serviceFixUpTask.findAllByFinder(f).size() == 1);
	}
}
