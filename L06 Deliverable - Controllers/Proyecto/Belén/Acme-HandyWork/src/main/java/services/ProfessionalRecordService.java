
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
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository	professionalRecRepository;


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
		return this.professionalRecRepository.findProfessionalRecordByUserId(user.getId());
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
		return saved;
	}
}
