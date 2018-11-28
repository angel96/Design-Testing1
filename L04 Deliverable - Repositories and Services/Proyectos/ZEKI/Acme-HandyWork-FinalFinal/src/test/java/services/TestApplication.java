package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;

import domain.Application;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"})
@Transactional
public class TestApplication extends AbstractTest{
	
	@Autowired
	private ApplicationService applicationService;
	
	@Test
	public void testUpdateStatus() {
		super.authenticate("customer2");
		Application a, saved;
		a = this.applicationService.findOne(1252);
		System.out.println(a.getId());
		a.setStatus("rejected");
		saved = this.applicationService.updateStatus(a.getId(), a);
		super.authenticate(null);
		Assert.notNull(saved);
	}

}
