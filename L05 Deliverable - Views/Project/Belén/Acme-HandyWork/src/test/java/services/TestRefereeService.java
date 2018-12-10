package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", 
	"classpath:spring/config/packages.xml"
})
@Transactional
public class TestRefereeService extends AbstractTest {
	
	@Autowired
	private RefereeService refereeService;
	
	@Test //TEST CREATING A REFEREE
	public void testCreateReferee() {
		super.authenticate("admin1");
		Assert.notNull(this.refereeService.create());
		super.unauthenticate();		
	}

}
