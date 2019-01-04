
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Box;
import domain.Note;
import domain.Profile;
import domain.Referee;

@Service
@Transactional
public class RefereeService {

	@Autowired
	private RefereeRepository	refereeRepository;


	public Collection<Referee> findAll() {
		return this.refereeRepository.findAll();
	}

	public Referee findOne(final int idReferee) {
		return this.refereeRepository.findOne(idReferee);
	}

	public Referee findByUserAccount(final int userAccount) {
		Assert.notNull(userAccount);
		return this.refereeRepository.findByUserAccount(userAccount);
	}

	public Referee save(final Referee ref) {
		Assert.notNull(ref);
		return this.refereeRepository.save(ref);
	}
}
