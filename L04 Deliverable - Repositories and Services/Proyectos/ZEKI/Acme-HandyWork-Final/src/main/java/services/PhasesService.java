package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.HandyWorker;
import domain.Phase;

import repositories.PhasesRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class PhasesService {
	
	@Autowired
	private PhasesRepository phaseRepository;
	
	@Autowired
	private HandyWorkerService handyWorkerService;
	
	@Autowired
	private ApplicationService applicationService;
	
	public Phase findOne(final int id) {
		return this.phaseRepository.findOne(id);
	}
	
	public Collection<Phase> getPhasesByhandyWorker(final HandyWorker h) {
		return this.phaseRepository.getPhasesByHandyWorker(h.getId());
	}
	
	public Phase create() {
		Phase p;
		p = new Phase();
		p.setDescription("");
		p.setEndMoment(new Date());
		p.setNumber(0);
		p.setStartMoment(new Date());
		p.setTitle("");
		return p;
	}
	
	public Phase save(final Phase p) {
//		Assert.notNull(this.applicationService.getApplicationAcceptedByPhase(p.getId()));
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);
		
		HandyWorker h;
		System.out.println(this.handyWorkerService.findByUserAccount(user.getId()));
		h = this.handyWorkerService.findByUserAccount(user.getId());
		
		Collection<Phase> phaseHandy;
		phaseHandy = this.getPhasesByhandyWorker(h);
		
		Phase saved;
		Assert.notNull(p);
		saved = this.phaseRepository.save(p);
		
		phaseHandy.add(saved);
		h.setPhase(phaseHandy);
		this.handyWorkerService.update(h);
		return saved;
	}
	
	public Phase update(final int id, final Phase newer) {
		Phase old, saved;
		
		old = this.findOne(id);
		old.setDescription(newer.getDescription());
		old.setEndMoment(newer.getEndMoment());
		old.setNumber(newer.getNumber());
		old.setStartMoment(newer.getStartMoment());
		old.setTitle(newer.getTitle());
		
		UserAccount userLogged;
		userLogged = LoginService.getPrincipal();
		
		if(userLogged.equals(this.phaseRepository.getHandyWorkerByPhases(id).getAccount()))
			saved = this.phaseRepository.save(old);
		else
			throw new IllegalAccessError("A phase which doesn´t belong to the HandyWorker logged can not be modified");
		
		Assert.notNull(saved);
		return saved;
	}
	
	public void delete(final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();
		
		HandyWorker h;
		h = this.handyWorkerService.findByUserAccount(user.getId());
		
		if(h.equals(this.phaseRepository.getHandyWorkerByPhases(id)))
			this.phaseRepository.delete(id);
		else
			throw new IllegalAccessError("A phase which doesn´t belong to the HandyWorker logged can not be modified");
		
	}
		
		
}
