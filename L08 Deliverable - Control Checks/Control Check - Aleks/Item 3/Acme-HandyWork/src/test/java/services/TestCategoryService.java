
package services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestCategoryService extends AbstractTest {

	@Autowired
	CategoryService	categoryService;


	@Test
	public void testFindAll() {
		super.authenticate("customer1");
		Assert.isTrue(!(this.categoryService.findAll().isEmpty()));
		super.unauthenticate();
	}

	@Test
	public void testFindOne() {
		super.authenticate("customer1");
		Assert.isTrue(this.categoryService.findOne(4111) != null);
		super.unauthenticate();
	}

	@Test
	public void testCreateCategory() {
		super.authenticate("admin1");
		Assert.isTrue(this.categoryService.createCategory() != null);
		super.unauthenticate();
	}

	@Test
	public void testSavePatern() {
		super.authenticate("admin1");
		Category c;
		c = new Category();
		c.setName("");
		c.setOtherlanguages(new ArrayList<String>());
		Assert.isTrue(this.categoryService.saveParent(c) != null);
	}

	@Test
	public void testSaveSubCategory() {
		super.authenticate("admin1");
		Category c;
		c = new Category();
		c.setName("");
		c.setOtherlanguages(new ArrayList<String>());
		Assert.isTrue(this.categoryService.saveSubCategory(this.categoryService.findOne(4111).getId(), c) != null);
		super.unauthenticate();
	}

	@Test
	public void testDeleteCategory() {
		super.authenticate("admin1");
		Assert.isTrue(this.categoryService.findOne(4111) != null);
		this.categoryService.deleteCategory(4111);
		Assert.isNull(this.categoryService.findOne(4111));
		super.unauthenticate();
	}

}
