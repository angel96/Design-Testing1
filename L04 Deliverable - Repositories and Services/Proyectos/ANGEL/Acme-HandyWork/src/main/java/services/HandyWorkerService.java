
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
import domain.Administrator;
import domain.Application;
import domain.Box;
import domain.Category;
import domain.Curriculum;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Message;
import domain.Note;
import domain.Phase;
import domain.Profile;
import domain.Referee;
import domain.Sponsor;
import domain.Tutorial;
import domain.Warranty;

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
	private MessageService			messageService;


	public Collection<HandyWorker> findAll() {
		return this.repositoryHandyWorker.findAll();
	}

	public HandyWorker findOne(final int id) {
		return this.repositoryHandyWorker.findOne(id);
	}

	public HandyWorker findByUserAccount(final int userAccount) {
		HandyWorker result;
		result = this.repositoryHandyWorker.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
	public HandyWorker create() {
		HandyWorker handyWorker;
		handyWorker = new HandyWorker();
		UserAccount account;
		account = new UserAccount();
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
		handyWorker.setPhase(new ArrayList<Phase>());
		handyWorker.setProfiles(new ArrayList<Profile>());
		handyWorker.setTutoriales(new ArrayList<Tutorial>());
		handyWorker.setAdress("");
		handyWorker.setBan(false);
		handyWorker.setCurriculum(new Curriculum());
		handyWorker.setEmail("");
		handyWorker.setId(0);
		handyWorker.setMiddleName("");
		handyWorker.setName("");
		handyWorker.setPhone("");
		handyWorker.setPhoto("");
		handyWorker.setScore(0.0);
		handyWorker.setSurname("");
		handyWorker.setVersion(0);
		return handyWorker;
	}

	public HandyWorker save(final HandyWorker hw) {
		Assert.notNull(hw);
		return this.repositoryHandyWorker.save(hw);
	}

	public HandyWorker update(final HandyWorker newHw) {
		Assert.notNull(newHw);
		HandyWorker saved;
		saved = this.save(newHw);
		Assert.notNull(saved);
		return saved;
	}

	public Collection<Box> manageNotSystemBox(final HandyWorker hw) {
		Assert.notNull(this.repositoryHandyWorker.findByUserAccount(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNonBoxes(hw.getId());
		return boxes;
	}
	public void sendMessage(final HandyWorker sender, final Administrator recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.ADMIN));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.adminService.save(recipient);
	}

	public void sendMessage(final HandyWorker sender, final Sponsor recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.SPONSOR));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.sponsorService.addSponsor(recipient);
	}

	public void sendMessage(final HandyWorker sender, final Referee recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.REFEREE));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.refereeService.save(recipient);
	}

	public void sendMessage(final HandyWorker sender, final Customer recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.CUSTOMER));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.customerService.save(recipient);
	}

	public void sendMessage(final HandyWorker sender, final HandyWorker recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.repositoryHandyWorker.save(recipient);
	}

	public Collection<FixUpTask> findByKeyWord(final String s) {
		return this.repositoryHandyWorker.findAllByKeyWorkd("%" + s + "%");
	}
	public Collection<FixUpTask> findByPrices(final double d1, final double d2) {
		return this.repositoryHandyWorker.findAllByPrices(d1, d2);
	}
	public Collection<FixUpTask> findByCategory(final Category category) {
		return this.repositoryHandyWorker.findAllByCategory(category);
	}
	public Collection<FixUpTask> findByWarranty(final Warranty warranty) {
		return this.repositoryHandyWorker.findAllByWarranty(warranty);
	}
}
