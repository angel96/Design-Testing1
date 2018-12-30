
package services;

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
import domain.Finder;
import domain.HandyWorker;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	repositoryHandyWorker;
	@Autowired
	private AdministratorService	adminService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private RefereeService			refereeService;
	@Autowired
	private SponsorService			sponsorService;
	@Autowired
	private BoxService				boxService;

	@Autowired
	private FinderService			finderService;


	public Collection<HandyWorker> findAll() {
		return this.repositoryHandyWorker.findAll();
	}

	public HandyWorker findOne(final int id) {
		return this.repositoryHandyWorker.findOne(id);
	}

	public HandyWorker findHandyWorker(final int handy) {
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

	public HandyWorker save(final HandyWorker hw) {

		System.out.println(hw);
		if (hw.getId() != 0) {
			final UserAccount account = this.findHandyWorker(hw.getId()).getAccount();
			account.setUsername(hw.getAccount().getUsername());
			account.setPassword(Utiles.hashPassword(hw.getAccount().getPassword()));
			account.setAuthorities(hw.getAccount().getAuthorities());
			hw.setAccount(account);
		} else
			hw.getAccount().setPassword(Utiles.hashPassword(hw.getAccount().getPassword()));

		Finder fin;
		fin = this.finderService.save(Utiles.createFinder());
		System.out.println(fin);
		hw.setFinder(fin);

		HandyWorker modify;
		modify = this.repositoryHandyWorker.saveAndFlush(hw);

		return modify;
	}

}
