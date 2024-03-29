
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Curriculum;
import domain.EndorserRecord;
import domain.HandyWorker;

@Service
@Transactional
public class EndorserRecordService {

	@Autowired
	private EndorserRecordRepository	endorserRecRepository;

	@Autowired
	private CurriculumService			curriculumService;


	public Collection<EndorserRecord> findAll() {
		return this.endorserRecRepository.findAll();
	}

	public EndorserRecord findOne(final int idEndorser) {
		return this.endorserRecRepository.findOne(idEndorser);
	}

	public Collection<EndorserRecord> findEndorserRecordByUserId(final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		return this.endorserRecRepository.findEndorserRecordByUserId(id);
	}

	public EndorserRecord createEndorserRecord() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		EndorserRecord endorser;
		endorser = new EndorserRecord();
		endorser.setFullNameEndorser("");
		endorser.setEmail("");
		endorser.setPhone("");
		endorser.setLinkedin("");
		Collection<String> comments;
		comments = new ArrayList<>();
		endorser.setComments(comments);
		return endorser;
	}

	public EndorserRecord save(final EndorserRecord e) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		EndorserRecord saved;
		saved = this.endorserRecRepository.save(e);

		HandyWorker h;
		h = this.curriculumService.getHandyByUserAccount(user.getId());

		Curriculum c;

		if (h.getCurriculum() == null)
			c = this.curriculumService.save(this.curriculumService.createCurriculum());
		else
			c = h.getCurriculum();

		Collection<EndorserRecord> endorser;

		if (c.getEndorserRecord().isEmpty() || c.getEndorserRecord() == null)
			endorser = new ArrayList<EndorserRecord>();
		else
			endorser = c.getEndorserRecord();

		endorser.add(saved);
		c.setEndorserRecord(endorser);

		return saved;
	}
}
