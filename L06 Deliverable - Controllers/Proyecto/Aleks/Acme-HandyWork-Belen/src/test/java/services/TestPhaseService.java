
package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Phase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestPhaseService extends AbstractTest {

	@Autowired
	private PhasesService	phaseService;


	@Test
	public void testCreatePhase() {
		Phase ph, saved;
		super.authenticate("handyworker1");
		ph = new Phase();
		ph.setDescription("Parte 1");
		final Date d = new Date(2018 - 1900, 11, 25);
		ph.setEndMoment(d);
		ph.setNumber(1);
		final Date g = new Date(2018 - 1900, 10, 25);
		ph.setStartMoment(g);
		ph.setTitle("Phase 1");
		System.out.println(ph);
		saved = this.phaseService.save(ph);
		super.authenticate(null);
		Assert.notNull(saved);
	}
	@Test
	public void testUpdatePhase() {
		super.authenticate("handyworker3");
		Phase p;
		final Phase saved;
		p = this.phaseService.findOne(4860);
		p.setDescription("Parte 1 plus");
		p.setTitle("Phase 1 plus");
		//saved = this.phaseService.update(p);
		//System.out.println(saved.getDescription());
		super.authenticate(null);
		//Assert.notNull(saved);

	}

	@Test
	public void testDeletePhase() {
		super.authenticate("handyworker3");
		this.phaseService.delete(4860);
		Assert.isNull(this.phaseService.findOne(4860));
		super.authenticate(null);
	}

}
