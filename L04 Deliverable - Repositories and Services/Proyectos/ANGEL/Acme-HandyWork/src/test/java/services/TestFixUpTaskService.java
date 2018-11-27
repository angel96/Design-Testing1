
package services;

import java.util.ArrayList;
import java.util.Date;

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
import domain.Application;
import domain.Category;
import domain.Complaint;
import domain.FixUpTask;
import domain.Phase;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestFixUpTaskService extends AbstractTest {

	@Autowired
	private FixUpTaskService		fixUpTaskService;

	@Rule
	public final ExpectedException	exception	= ExpectedException.none();


	@Test
	public void testGetAll() {
		Assert.isTrue(this.fixUpTaskService.findAll().size() >= 1);
	}

	@Test
	public void testCreateFixUp() {
		FixUpTask fut, saved;
		super.authenticate("customer2");
		fut = new FixUpTask();
		fut.setAddress("Avda Andalucia");
		fut.setApplication(new ArrayList<Application>());
		Category c;
		c = new Category();
		c.setName("Carpentry");
		c.setCategories(new ArrayList<String>());
		fut.setCategory(c);
		fut.setComplaint(new ArrayList<Complaint>());
		fut.setDescription("Test job 1");
		final Date d = new Date(2018 - 1900, 11, 25);
		fut.setEnd(d);
		fut.setMaximumPrice(100.0);
		fut.setMoment(new Date());
		fut.setPhases(new ArrayList<Phase>());
		Warranty w;
		w = new Warranty();
		w.setDraftMode(false);
		w.setTitle("Law 1");
		w.setTerms("Law Terms 1");
		w.setLaws("Laws prepared for warranty");
		fut.setWarranty(w);
		fut.setTicker(Utiles.generateTicker());
		System.out.println(fut);
		saved = this.fixUpTaskService.save(fut);
		super.unauthenticate();
		Assert.notNull(saved);
	}
	// Test de creacion sin loggeo
	@Test
	public void testCreateFixUp2() {

		FixUpTask fut, saved;
		fut = new FixUpTask();
		fut.setAddress("Avda Andalucia");
		fut.setApplication(new ArrayList<Application>());
		Category c;
		c = new Category();
		c.setName("Carpentry");
		fut.setCategory(c);
		fut.setComplaint(new ArrayList<Complaint>());
		fut.setDescription("Test job 1");
		final Date d = new Date(2018 - 1900, 11, 25);
		fut.setEnd(d);
		fut.setMaximumPrice(100.0);
		fut.setMoment(new Date());
		fut.setPhases(new ArrayList<Phase>());
		Warranty w;
		w = new Warranty();
		w.setDraftMode(false);
		w.setTitle("Law 1");
		w.setTerms("Law Terms 1");
		w.setLaws("Laws prepared for warranty");
		fut.setWarranty(w);
		fut.setTicker(Utiles.generateTicker());
		this.exception.expect(IllegalArgumentException.class);
		saved = this.fixUpTaskService.save(fut);
	}

	//Testing FixUp update
	@Test
	public void testUpdate1() {
		super.authenticate("customer2");
		FixUpTask f, saved;
		f = this.fixUpTaskService.findOne(1249);
		f.setAddress("addressfixuptask6");
		saved = this.fixUpTaskService.update(f);
		super.authenticate(null);
		Assert.notNull(saved);
	}
	//Testing FixUp delete
	@Test
	public void testDelete1() {
		super.authenticate("customer2");
		this.fixUpTaskService.delete(1249);
		Assert.isNull(this.fixUpTaskService.findOne(1249));
		super.authenticate(null);
	}
	//Testing FixUp update - Doesn´t belong to the customer given - Expected Exception
	@Test
	public void testUpdate2() {
		super.authenticate("customer2");
		FixUpTask f, saved;
		//Task from Customer 1
		f = this.fixUpTaskService.findOne(1248);
		f.setAddress("addressfixuptask6");
		this.exception.expect(IllegalAccessError.class);
		saved = this.fixUpTaskService.update(f);
		super.authenticate(null);
		Assert.isNull(saved);
	}
	//Testing FixUp delete - Doesn´t belong to the customer given - Expected Exception
	@Test
	public void testDelete2() {
		super.authenticate("customer2");
		this.exception.expect(IllegalAccessError.class);
		this.fixUpTaskService.delete(1248);
		Assert.isNull(this.fixUpTaskService.findOne(1248));
		super.authenticate(null);
	}
}
