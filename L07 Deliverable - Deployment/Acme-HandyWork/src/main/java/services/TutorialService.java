
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Actor;
import domain.HandyWorker;
import domain.Section;
import domain.Sponsorship;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository	repositoryTutorial;

	@Autowired
	private HandyWorkerService	serviceHandyWorker;

	@Autowired
	private SponsorshipService	serviceSponsorship;


	public Actor findActorByUserAccount(final int id) {
		return this.repositoryTutorial.findActorByUserAccount(id);
	}

	public Collection<Tutorial> findAll() {
		Collection<Tutorial> result;
		result = this.repositoryTutorial.findAll();
		return result;
	}
	public Collection<Tutorial> findAllHandyWorkerlog() {
		Collection<Tutorial> result;
		result = ((HandyWorker) this.repositoryTutorial.findActorByUserAccount(LoginService.getPrincipal().getId())).getTutorials();
		return result;
	}
	public Tutorial findOne(final int id) {
		return this.repositoryTutorial.findOne(id);
	}
	public Tutorial createTutorial() {
		Tutorial t;
		t = new Tutorial();
		t.setTitle("");
		t.setSummary("");
		t.setSection(new ArrayList<Section>());
		t.setPicture(new ArrayList<String>());
		t.setLastUpdate(new Date());
		t.setSponsorship(new ArrayList<Sponsorship>());
		return t;
	}
	public Tutorial save(final Tutorial t) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
		Tutorial saved;
		saved = this.repositoryTutorial.save(t);

		final HandyWorker w = (HandyWorker) this.repositoryTutorial.findActorByUserAccount(LoginService.getPrincipal().getId());

		Collection<Tutorial> tutorials;

		tutorials = w.getTutorials();

		if (!tutorials.contains(saved)) {
			tutorials.add(saved);
			w.setTutorials(tutorials);
		}

		return saved;
	}

	public void delete(final int id) {

		UserAccount user;
		user = LoginService.getPrincipal();

		HandyWorker w;
		w = this.serviceHandyWorker.findByUserAccount(user.getId());
		final Tutorial t = this.repositoryTutorial.findOne(id);

		Collection<Tutorial> tutorialsFromHW;
		tutorialsFromHW = w.getTutorials();
		tutorialsFromHW.remove(this.repositoryTutorial.findOne(id));
		w.setTutorials(tutorialsFromHW);

		final Collection<Sponsorship> sponsorships = t.getSponsorship();
		this.serviceSponsorship.delete(sponsorships);

		this.repositoryTutorial.delete(id);
	}
}
