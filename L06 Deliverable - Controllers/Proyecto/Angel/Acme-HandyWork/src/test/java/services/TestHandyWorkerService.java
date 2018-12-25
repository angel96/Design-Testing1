
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Category;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestHandyWorkerService extends AbstractTest {

	@Autowired
	private HandyWorkerService	service;

	@Autowired
	private CategoryService		serviceC;

	@Autowired
	private WarrantyService		serviceW;


	@Test
	public void testFindByKeyWord() {
		Assert.isTrue(this.service.findByKeyWord("address").size() >= 1);
	}

	@Test
	public void testFindByPrices() {
		System.out.println(this.service.findByPrices(10.0, 40.0).size());
		Assert.isTrue(this.service.findByPrices(10.0, 40.0).size() >= 1);
	}

	@Test
	public void testFindByCategory() {
		Category category;
		category = this.serviceC.findOne(4874);
		Assert.isTrue(this.service.findByCategory(category).size() >= 1);
	}

	@Test
	public void testFindByWarranty() {
		Warranty w;
		w = this.serviceW.findOne(4891);
		Assert.isTrue(this.service.findByWarranty(w).size() >= 1);
	}

}
