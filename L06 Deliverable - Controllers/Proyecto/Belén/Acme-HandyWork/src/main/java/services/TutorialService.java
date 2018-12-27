
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import security.LoginService;
import security.UserAccount;
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
	private SectionService		serviceSection;

	@Autowired
	private SponsorshipService	serviceSponsorship;

	@Autowired
	private HandyWorkerService	serviceHandyWorker;


	public Collection<Tutorial> getTutorialsByHandyWorker(final int userAccount) {
		return this.repositoryTutorial.getTutorialsByHandyWorker(userAccount);
	}

	public Collection<Tutorial> findAll() {
		return this.repositoryTutorial.findAll();
	}

	public Collection<Section> getSectionsByTutorial(final int id) {
		return this.repositoryTutorial.getSectionsByTutorial(id);
	}
	public Collection<Sponsorship> getSponsorshipsByTutorial(final int id) {
		return this.repositoryTutorial.getSponsorshipsByTutorial(id);
	}
	public Tutorial findOne(final int id) {
		return this.repositoryTutorial.findOne(id);
	}

	public Tutorial save(final Tutorial t) {

		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);
		Collection<Tutorial> tutorialesDelUsuario;
		tutorialesDelUsuario = this.repositoryTutorial.getTutorialsByHandyWorker(user.getId());

		Tutorial saved;

		if (t != null) {
			saved = this.repositoryTutorial.save(t);
			tutorialesDelUsuario.add(t);
			//Necesito el update HandyWorker
		} else
			throw new IllegalAccessError();

		return saved;
	}
	public Section addSectionToTutorial(final Tutorial t, final Section s) {
		Collection<Section> sectionsFromTutorial;
		sectionsFromTutorial = t.getSection();
		Section savedSection;
		savedSection = this.serviceSection.addSection(s);
		sectionsFromTutorial.add(savedSection);
		Tutorial saved;
		t.setSection(sectionsFromTutorial);
		saved = this.repositoryTutorial.save(t);

		return savedSection;
	}

	public Sponsorship addSponsorshipToTutorial(final Sponsorship s) {
		Collection<Sponsorship> sponsorshipsFromTutorial;
		sponsorshipsFromTutorial = s.getTutorial().getSponsorship();

		Sponsorship savedSponsorship;
		savedSponsorship = this.serviceSponsorship.add(s);

		sponsorshipsFromTutorial.add(savedSponsorship);

		Tutorial saved;

		s.getTutorial().setSponsorship(sponsorshipsFromTutorial);

		saved = this.repositoryTutorial.save(s.getTutorial());

		return savedSponsorship;
	}

	public Tutorial update(final Tutorial modify) {

		UserAccount user;
		user = LoginService.getPrincipal();

		HandyWorker w;
		w = this.serviceHandyWorker.findByUserAccount(user.getId());

		Assert.notNull(w);

		Tutorial saved;

		saved = this.repositoryTutorial.save(modify);

		Assert.notNull(saved);

		return saved;
	}

	public void delete(final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();

		HandyWorker w;
		w = this.serviceHandyWorker.findByUserAccount(user.getId());
		Assert.notNull(w);
		Assert.notNull(this.findOne(id));
		this.repositoryTutorial.delete(id);
	}
}
