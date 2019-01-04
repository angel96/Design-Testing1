
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Actor;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.ProfessionalRecord;

@Service
@Transactional
public class CurriculumService {

	@Autowired
	private CurriculumRepository	curriculumRepository;


	public Actor getActorByUserAccount(final int id) {
		return this.curriculumRepository.findActorByUserAccountId(id);
	}

	public Collection<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}

	public Curriculum findOne(final int idCurriculum) {
		return this.curriculumRepository.findOne(idCurriculum);
	}

	public Curriculum createCurriculum() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		Curriculum curriculum;
		curriculum = new Curriculum();
		if (this.curriculumRepository.findLinkedInByActorId(user.getId()).size() > 0) {
			curriculum.setTicker(Utiles.generateTicker());
			Collection<EducationRecord> educ;
			educ = new ArrayList<>();
			curriculum.setEducationRecord(educ);
			Collection<EndorserRecord> endor;
			endor = new ArrayList<>();
			curriculum.setEndorserRecord(endor);
			Collection<ProfessionalRecord> profe;
			profe = new ArrayList<>();
			curriculum.setProfessionalRecord(profe);
			Collection<MiscellaneousRecord> misc;
			misc = new ArrayList<>();
			curriculum.setMiscellaneousRecord(misc);
		}

		return curriculum;
	}
	public Curriculum save(final Curriculum c) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		Curriculum saved;
		saved = this.curriculumRepository.save(c);
		return saved;
	}

}
