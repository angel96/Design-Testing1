
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MiscellaneousRecordRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Curriculum;
import domain.HandyWorker;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	@Autowired
	private MiscellaneousRecordRepository	miscellaneousRepository;

	@Autowired
	private CurriculumService				curriculumService;


	public Collection<MiscellaneousRecord> findAll() {
		return this.miscellaneousRepository.findAll();
	}

	public MiscellaneousRecord findOne(final int idMisce) {
		return this.miscellaneousRepository.findOne(idMisce);
	}

	public Collection<MiscellaneousRecord> findMiscellaneousRecordByUserId(final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		System.out.println(this.miscellaneousRepository.findMiscellaneousRecordByUserId(id));
		return this.miscellaneousRepository.findMiscellaneousRecordByUserId(id);
	}

	public MiscellaneousRecord createMiscellaneousRecord() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		MiscellaneousRecord miscellaneous;
		miscellaneous = new MiscellaneousRecord();
		miscellaneous.setTitle("");
		miscellaneous.setAttachment("");
		Collection<String> comments;
		comments = new ArrayList<>();
		miscellaneous.setComments(comments);
		return miscellaneous;
	}

	public MiscellaneousRecord save(final MiscellaneousRecord m) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		MiscellaneousRecord saved;
		saved = this.miscellaneousRepository.save(m);
		HandyWorker h;
		h = this.curriculumService.getHandyByUserAccount(user.getId());
		Curriculum c;
		c = h.getCurriculum();
		Collection<MiscellaneousRecord> miscellaneous;
		miscellaneous = c.getMiscellaneousRecord();
		miscellaneous.add(saved);
		return saved;
	}
}
