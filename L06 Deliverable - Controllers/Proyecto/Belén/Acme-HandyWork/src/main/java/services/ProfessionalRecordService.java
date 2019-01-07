
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Curriculum;
import domain.HandyWorker;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository	professionalRecRepository;

	@Autowired
	private CurriculumService				curriculumService;


	public Collection<ProfessionalRecord> findAll() {
		return this.professionalRecRepository.findAll();
	}

	public ProfessionalRecord findOne(final int idProfessional) {
		return this.professionalRecRepository.findOne(idProfessional);
	}

	public Collection<ProfessionalRecord> findProfessionalRecordByUserId(final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		return this.professionalRecRepository.findProfessionalRecordByUserId(id);
	}

	public ProfessionalRecord createProfessionalRecord() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		ProfessionalRecord professional;
		professional = new ProfessionalRecord();
		professional.setCompanyName("");
		professional.setStartWorking(new Date());
		professional.setEndWorking(new Date());
		professional.setRole("");
		professional.setAttachment("");
		Collection<String> comments;
		comments = new ArrayList<>();
		professional.setComments(comments);
		return professional;
	}

	public ProfessionalRecord save(final ProfessionalRecord p) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		ProfessionalRecord saved;
		saved = this.professionalRecRepository.save(p);
		HandyWorker h;
		h = this.curriculumService.getHandyByUserAccount(user.getId());
		Curriculum c;
		c = h.getCurriculum();
		Collection<ProfessionalRecord> professional;
		professional = c.getProfessionalRecord();
		professional.add(saved);
		return saved;
	}
}
