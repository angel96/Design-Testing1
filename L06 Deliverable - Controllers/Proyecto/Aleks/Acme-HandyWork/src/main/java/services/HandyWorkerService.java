
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Application;
import domain.Box;
import domain.Curriculum;
import domain.Customer;
import domain.Finder;
import domain.HandyWorker;
import domain.Note;
import domain.Profile;
import domain.Tutorial;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	repositoryHandyWorker;

	@Autowired
	private BoxService				boxService;

	@Autowired
	private FinderService			finderService;


	public Collection<HandyWorker> findAll() {
		return this.repositoryHandyWorker.findAll();
	}

	public Collection<Customer> findCustomerByHandyWorkerId(final int id) {
		return this.repositoryHandyWorker.findCustomerByHandyWorkerId(id);
	}

	public Collection<HandyWorker> findHandyWorkersByCustomerId(final int id) {
		return this.repositoryHandyWorker.findHandyWorkersByCustomerId(id);
	}

	public HandyWorker findOne(final int handy) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
		HandyWorker saved;
		saved = this.repositoryHandyWorker.findOne(handy);
		Assert.notNull(saved);
		return saved;
	}

	public HandyWorker findByUserAccount(final int userAccount) {
		HandyWorker result;
		result = this.repositoryHandyWorker.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
	public HandyWorker createHandyWorker() {
		HandyWorker handyWorker;
		handyWorker = new HandyWorker();

		UserAccount account;
		account = new UserAccount();
		account.setEnabled(true);
		Authority auth;
		auth = new Authority();
		Collection<Authority> authorities;
		authorities = new ArrayList<>();
		auth.setAuthority(Authority.HANDY_WORKER);
		authorities.add(auth);
		account.setAuthorities(authorities);
		account.setUsername("");
		account.setPassword("");

		handyWorker.setAccount(account);
		handyWorker.setApplication(new ArrayList<Application>());
		handyWorker.setNotes(new ArrayList<Note>());
		handyWorker.setProfiles(new ArrayList<Profile>());
		handyWorker.setTutorials(new ArrayList<Tutorial>());
		handyWorker.setAdress("");
		handyWorker.setCurriculum(new Curriculum());
		handyWorker.setEmail("");
		handyWorker.setMake("");
		handyWorker.setMiddleName("");
		handyWorker.setName("");
		handyWorker.setPhone("");
		handyWorker.setPhoto("");
		handyWorker.setScore(0.0);
		handyWorker.setSurname("");

		return handyWorker;
	}

	public HandyWorker save(final HandyWorker hw) {

		System.out.println("ENTRO AQUI 1:" + hw.getMake());
		System.out.println("ENTRO AQUI 1:" + hw.getScore());
		if (hw.getId() != 0) {
			final UserAccount account = this.findOne(hw.getId()).getAccount();
			account.setUsername(hw.getAccount().getUsername());
			account.setPassword(Utiles.hashPassword(hw.getAccount().getPassword()));
			account.setAuthorities(hw.getAccount().getAuthorities());
			hw.setAccount(account);
		} else {
			hw.getAccount().setPassword(Utiles.hashPassword(hw.getAccount().getPassword()));
			final Collection<Box> boxes = this.boxService.save(Utiles.initBoxes());
			hw.setBoxes(boxes);

			Finder fin;
			fin = this.finderService.save(this.finderService.createFinder());
			System.out.println(fin);
			hw.setFinder(fin);

			System.out.println("ENTRO AQUI 2:" + hw.getMake());
			System.out.println("ENTRO AQUI 2:" + hw.getScore());
		}

		HandyWorker modify;
		modify = this.repositoryHandyWorker.saveAndFlush(hw);
		System.out.println("ENTRO AQUI 3:" + hw.getMake());
		System.out.println("ENTRO AQUI 3:" + hw.getScore());
		return modify;
	}
}
