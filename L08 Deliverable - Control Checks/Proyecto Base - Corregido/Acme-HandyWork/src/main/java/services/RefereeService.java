
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

	@Autowired
	private BoxService			boxService;


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
	public Referee createReferee() {

		UserAccount creator;
		creator = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(creator.getAuthorities(), Authority.ADMIN));

		Referee r;
		r = new Referee();

		UserAccount user;
		user = new UserAccount();
		user.setEnabled(true);
		Authority a;
		a = new Authority();
		Collection<Authority> authorities;
		authorities = new ArrayList<>();
		a.setAuthority(Authority.REFEREE);
		authorities.add(a);
		user.setAuthorities(authorities);

		r.setAccount(user);
		r.setAdress("");
		r.setBoxes(new ArrayList<Box>());
		r.setEmail("");

		r.setMiddleName("");
		r.setName("");
		r.setNotes(new ArrayList<Note>());
		r.setPhone("");
		r.setPhoto("");
		r.setProfiles(new ArrayList<Profile>());
		r.setSurname("");
		return r;
	}
	public Referee save(final Referee ref) {
		if (ref.getId() != 0) {
			final UserAccount account = this.findOne(ref.getId()).getAccount();
			account.setUsername(ref.getAccount().getUsername());
			account.setPassword(Utiles.hashPassword(ref.getAccount().getPassword()));
			account.setAuthorities(ref.getAccount().getAuthorities());
			ref.setAccount(account);
		} else {
			ref.getAccount().setPassword(Utiles.hashPassword(ref.getAccount().getPassword()));
			final Collection<Box> boxes = this.boxService.save(Utiles.initBoxes());
			ref.setBoxes(boxes);
		}
		Referee modify;

		modify = this.refereeRepository.saveAndFlush(ref);

		return modify;
	}
}
