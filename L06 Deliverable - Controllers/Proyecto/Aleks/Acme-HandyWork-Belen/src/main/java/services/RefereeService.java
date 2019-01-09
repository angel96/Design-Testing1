
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.UserAccount;
import utilities.Utiles;
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
		if (ref.getId() != 0) {
			final UserAccount account = this.findOne(ref.getId()).getAccount();
			account.setUsername(ref.getAccount().getUsername());
			account.setPassword(Utiles.hashPassword(ref.getAccount().getPassword()));
			account.setAuthorities(ref.getAccount().getAuthorities());
			ref.setAccount(account);
		} else
			ref.getAccount().setPassword(Utiles.hashPassword(ref.getAccount().getPassword()));

		Referee modify;

		modify = this.refereeRepository.saveAndFlush(ref);

		return modify;
	}
}
