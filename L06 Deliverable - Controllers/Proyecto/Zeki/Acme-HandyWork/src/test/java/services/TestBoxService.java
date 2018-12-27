
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Box;
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestBoxService extends AbstractTest {

	@Autowired
	private BoxService			boxService;
	@Autowired
	private HandyWorkerService	handyService;


	@Test
	public void testFindNoSystemBoxes() {
		Collection<Box> boxes;
		HandyWorker h;
		h = this.handyService.findByUserAccount(4796);
		int id;
		id = h.getId();
		boxes = this.boxService.findAllNonBoxes(id);
		Assert.notNull(boxes);
	}

}
