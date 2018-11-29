
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import utilities.Utiles;
import domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestCategoryService extends AbstractTest {

	@Autowired
	private CategoryService	serviceCategory;


	@Test
	public void testCreate() {
		super.authenticate("admin1");
		Category c, saved;
		Collection<Category> colCat;
		c = Utiles.createCategory();
		c.setName("Reparación movil");
		c.setCategories(new ArrayList<String>());

		saved = this.serviceCategory.addCategory(c);

		colCat = this.serviceCategory.findAll();

		Assert.isTrue(colCat.contains(saved));
		System.out.println(saved.getName());
		super.authenticate(null);
	}

	@Test
	public void testUpdate() {
		super.authenticate("admin1");
		Category c;
		c = this.serviceCategory.findOne(2946);
		System.out.println(c.getName());
		c.setName("testeo carpinteria");
		System.out.println(c.getName());
		Assert.isTrue(c.getName().equals("testeo carpinteria"));
		super.authenticate(null);
	}

	@Test
	public void testDelete() {
		super.authenticate("admin1");
		Assert.notNull(this.serviceCategory.findOne(2946));
		System.out.println(this.serviceCategory.findOne(2946));
		this.serviceCategory.deleteCategory(2946);
		System.out.println(this.serviceCategory.findOne(2946));
		Assert.isNull(this.serviceCategory.findOne(2946));
		super.authenticate(null);
	}

}
