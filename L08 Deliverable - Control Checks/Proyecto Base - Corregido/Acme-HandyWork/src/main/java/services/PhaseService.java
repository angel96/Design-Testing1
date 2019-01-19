
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PhaseRepository;
import security.Authority;
import security.LoginService;
import utilities.Utiles;
import domain.Actor;
import domain.FixUpTask;
import domain.Phase;

@Service
@Transactional
public class PhaseService {

	@Autowired
	private PhaseRepository	phaseRepository;


	public FixUpTask findFixUpTaskByPhase(final Phase p) {
		return this.phaseRepository.findFixUpTaskFromPhase(p.getId());
	}

	public Actor findByUserAccount(final int id) {
		return this.phaseRepository.findByUserAccount(id);
	}

	public Phase findOne(final int id) {
		return this.phaseRepository.findOne(id);
	}
	public Phase createPhase() {
		Phase p;
		p = new Phase();
		p.setDescription("");
		p.setEndMoment(new Date());
		p.setNumber(1);
		p.setStartMoment(new Date());
		p.setTitle("");
		return p;
	}
	public Phase save(final FixUpTask f, final Phase p) {

		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));

		final FixUpTask aux = this.phaseRepository.findFixUpTaskAccepted(f.getId());

		Phase saved = null;
		if (aux != null)
			saved = this.phaseRepository.save(p);

		if (p.getId() == 0) {
			Collection<Phase> phases;
			phases = f.getPhases();
			phases.add(saved);
			f.setPhases(phases);

		}

		return saved;
	}

	public void delete(final Phase phase) {
		Assert.isTrue(phase.getId() != 0);
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));

		FixUpTask f;

		f = this.phaseRepository.findFixUpTaskFromPhase(phase.getId());

		Collection<Phase> phases;
		phases = f.getPhases();
		phases.remove(phase);
		f.setPhases(phases);
	}

}
