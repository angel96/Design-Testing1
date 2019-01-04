
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
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecordService {

	@Autowired
	private EndorserRecordRepository	endorserRecRepository;


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
		return this.endorserRecRepository.findEndorserRecordByUserId(user.getId());
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
		return saved;
	}
}
