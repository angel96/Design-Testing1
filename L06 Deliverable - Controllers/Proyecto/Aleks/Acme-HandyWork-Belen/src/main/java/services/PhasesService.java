
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PhasesRepository;
import security.LoginService;
import security.UserAccount;
import domain.HandyWorker;
import domain.Phase;

@Service
@Transactional
public class PhasesService {

	@Autowired
	private PhasesRepository	phaseRepository;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public Phase findOne(final int id) {
		return this.phaseRepository.findOne(id);
	}

	public Phase save(final Phase p) {
		//		Assert.notNull(this.applicationService.getApplicationAcceptedByPhase(p.getId()));
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);

		HandyWorker h;
		System.out.println(this.handyWorkerService.findByUserAccount(user.getId()));
		h = this.handyWorkerService.findByUserAccount(user.getId());

		final Collection<Phase> phaseHandy;
		//phaseHandy = this.getPhasesByhandyWorker(h);

		Phase saved;
		Assert.notNull(p);
		saved = this.phaseRepository.save(p);

		//phaseHandy.add(saved);
		//h.setPhase(phaseHandy);
		this.handyWorkerService.save(h);
		return saved;
	}

	public void delete(final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();

		HandyWorker h;
		h = this.handyWorkerService.findByUserAccount(user.getId());

		//		if (h.equals(this.phaseRepository.getHandyWorkerByPhases(id)))
		//			this.phaseRepository.delete(id);
		//		else
		//			throw new IllegalAccessError("A phase which doesn´t belong to the HandyWorker logged can not be modified");

	}

}
