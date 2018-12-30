
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


	public Collection<Tutorial> findAll() {
		return this.repositoryTutorial.findAll();
	}

	public Tutorial findOne(final int id) {
		return this.repositoryTutorial.findOne(id);
	}

	public Tutorial save(final Tutorial t) {

		return null;
	}
	public Section addSectionToTutorial(final Tutorial t, final Section s) {
		Collection<Section> sectionsFromTutorial;
		sectionsFromTutorial = t.getSection();
		final Section savedSection;
		//savedSection = this.serviceSection.addSection(s);
		//sectionsFromTutorial.add(savedSection);
		Tutorial saved;
		t.setSection(sectionsFromTutorial);
		saved = this.repositoryTutorial.save(t);

		return null;
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
