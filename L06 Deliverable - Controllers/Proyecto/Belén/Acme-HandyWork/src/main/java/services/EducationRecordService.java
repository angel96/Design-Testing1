
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EducationRecordRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	@Autowired
	private EducationRecordRepository	educationRecRepository;


	public Collection<EducationRecord> findAll() {
		return this.educationRecRepository.findAll();
	}

	public EducationRecord findOne(final int idEducation) {
		return this.educationRecRepository.findOne(idEducation);
	}

	public Collection<EducationRecord> findEducationRecordByUserId(final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		return this.educationRecRepository.findEducationRecordByUserId(user.getId());
	}

	public EducationRecord createEducationRecord() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		EducationRecord education;
		education = new EducationRecord();
		education.setDiplomaTitle("");
		education.setStartStudies(new Date());
		education.setEndStudies(new Date());
		education.setInstitutionDiploma("");
		education.setAttachment("");
		Collection<String> comments;
		comments = new ArrayList<>();
		education.setComments(comments);
		return education;
	}

	public EducationRecord save(final EducationRecord e) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		EducationRecord saved;
		saved = this.educationRecRepository.save(e);
		return saved;
	}

}
