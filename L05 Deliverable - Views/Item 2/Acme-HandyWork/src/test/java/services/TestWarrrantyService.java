
package services;

import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import utilities.Utiles;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestWarrrantyService extends AbstractTest {

	@Autowired
	private WarrantyService			serviceWarranty;

	@Rule
	public final ExpectedException	exception	= ExpectedException.none();


	@Test
	public void testGetAll() {
		Assert.isTrue(this.serviceWarranty.findAll().size() >= 0);
	}
	@Test
	public void testGetById() {
		Assert.notNull(this.serviceWarranty.findOne(4887));
	}
	@Test
	public void testCreate() {
		super.authenticate("admin1");
		Warranty w, saved;
		Collection<Warranty> warranties;

		w = Utiles.createWarranty();
		w.setTitle("Ley 1933/2018");
		w.setLaws("Real decreto ley 1998/2001");
		w.setDraftMode(true);
		w.setTerms("Terminos de la garantia que aplicar");

		saved = this.serviceWarranty.addWarranty(w);

		warranties = this.serviceWarranty.findAll();
		Assert.isTrue(warranties.contains(saved));
		super.unauthenticate();
	}

	// It can be modified due to its draftMode value

	@Test
	public void testUpdate1() {
		super.authenticate("admin1");
		Warranty w;
		w = this.serviceWarranty.findOne(4888);
		w.setTitle("Testeando garantia");
		Assert.notNull(this.serviceWarranty.updateWarranty(w));
		super.authenticate(null);
	}

	@Test
	public void testDelete1() {
		Assert.notNull(this.serviceWarranty.findOne(4888));
		this.serviceWarranty.deleteWarranty(4888);
		Assert.isNull(this.serviceWarranty.findOne(4888));
	}
	// It can not be modified due to its draftMode value
	@Test
	public void testUpdate2() {
		super.authenticate("admin1");
		Warranty w;
		w = this.serviceWarranty.findOne(4887);
		w.setTitle("Testeando garantia");
		this.exception.expect(IllegalAccessError.class);
		Assert.notNull(this.serviceWarranty.updateWarranty(w));
		super.authenticate(null);
	}
	@Test
	public void testDelete2() {
		Assert.notNull(this.serviceWarranty.findOne(4887));
		this.exception.expect(IllegalAccessError.class);
		this.serviceWarranty.deleteWarranty(4887);
		Assert.isNull(this.serviceWarranty.findOne(4887));
	}
}
