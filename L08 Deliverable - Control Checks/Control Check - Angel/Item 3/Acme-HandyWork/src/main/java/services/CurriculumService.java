
package services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.HandyWorker;
import domain.MiscellaneousRecord;
import domain.ProfessionalRecord;
import domain.Profile;

@Service
@Transactional
public class CurriculumService {

	@Autowired
	private CurriculumRepository	curriculumRepository;


	public HandyWorker getHandyByUserAccount(final int id) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
		return this.curriculumRepository.findHandyByUserAccountId(id);
	}

	public Profile getLinkedInProfile(final int id) {
		final Profile p = this.curriculumRepository.findLinkedInByHandyWorkerId(id);
		final HandyWorker w = this.getHandyByUserAccount(LoginService.getPrincipal().getId());
		Assert.isTrue(Utiles.findAuthority(w.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		return p;
	}

	public Curriculum findOne(final int idCurriculum) {
		final Curriculum c = this.curriculumRepository.findOne(idCurriculum);
		final HandyWorker w = this.getHandyByUserAccount(LoginService.getPrincipal().getId());
		Assert.isTrue(Utiles.findAuthority(w.getAccount().getAuthorities(), Authority.HANDY_WORKER) && w.getCurriculum().equals(c));
		return c;
	}

	public Curriculum createCurriculum() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));

		Curriculum curriculum;
		curriculum = new Curriculum();

		curriculum.setTicker(Utiles.generateTicker());

		curriculum.setEducationRecord(new ArrayList<EducationRecord>());

		curriculum.setEndorserRecord(new ArrayList<EndorserRecord>());

		curriculum.setProfessionalRecord(new ArrayList<ProfessionalRecord>());

		curriculum.setMiscellaneousRecord(new ArrayList<MiscellaneousRecord>());

		return curriculum;
	}
	public Curriculum save(final Curriculum c) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		Curriculum saved;
		saved = this.curriculumRepository.save(c);

		HandyWorker w;
		w = this.curriculumRepository.findHandyByUserAccountId(user.getId());

		w.setCurriculum(saved);

		return saved;
	}

}
