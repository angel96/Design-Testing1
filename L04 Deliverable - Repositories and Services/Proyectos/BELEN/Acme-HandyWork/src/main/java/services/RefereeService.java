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
import domain.Box;
import domain.Message;
import domain.Note;
import domain.Profile;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class RefereeService {
	
	@Autowired
	private RefereeRepository refereeRepository;
	
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
	
	public Referee create() {
		Referee r;
		r = new Referee();
		UserAccount user;
		user = new UserAccount();
		Authority a;
		a = new Authority();
		Collection<Authority> authorities;
		authorities = new ArrayList<>();
		a.setAuthority(Authority.REFEREE);
		authorities.add(a);
		user.setAuthorities(authorities);
		r.setAccount(user);
		r.setAdress("");
		r.setBan(false);
		r.setBoxes(new ArrayList<Box>());
		r.setEmail("");
		r.setId(0);
		r.setMessage(new ArrayList<Message>());
		r.setMiddleName("");
		r.setName("");
		r.setNotes(new ArrayList<Note>());
		r.setPhone("");
		r.setPhoto("");
		r.setProfiles(new ArrayList<Profile>());
		r.setReports(new ArrayList<Report>());
		r.setSurname("");
		return r;
	}
	
	public Referee save(final Referee ref) {
		Assert.notNull(ref);
		return this.refereeRepository.save(ref);
	}
	
	public Referee update(final Referee ref) {
		Referee old, saved;
		old = this.refereeRepository.findByUserAccount(ref.getId());
		old.setAdress(ref.getAdress());
		old.setBan(ref.isBan());
		old.setBoxes(ref.getBoxes());
		old.setEmail(ref.getEmail());
		old.setMessage(ref.getMessage());
		old.setMiddleName(ref.getMiddleName());
		old.setName(ref.getName());
		old.setNotes(ref.getNotes());
		old.setPhone(ref.getPhone());
		old.setPhoto(ref.getPhoto());
		old.setSurname(ref.getSurname());
		
		UserAccount user;
		user = LoginService.getPrincipal();

		if (user.equals(this.refereeRepository.findByUserAccount(ref.getId()).getAccount())) {
			saved = this.refereeRepository.save(old);
		} else {
			throw new IllegalAccessError("A referee which doesn´t belong to the referee logged can not be modified");
		}
		Assert.notNull(saved);
		return saved;
		
		
	}
}
