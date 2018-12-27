
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
import domain.Box;
import domain.Category;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
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
	/*
	 * public void sendMessage(final HandyWorker sender, final Administrator recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.ADMIN));
	 * Assert.notNull(m);
	 * m.setSender(sender);
	 * Collection<Actor> receivers;
	 * receivers = new ArrayList<>();
	 * receivers.add(recipient);
	 * 
	 * m.setReceiver(receivers);
	 * Collection<Message> received;
	 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	 * Utiles.sendIndividualMessage(recipient, received, m);
	 * this.adminService.save(recipient);
	 * }
	 * 
	 * public void sendMessage(final HandyWorker sender, final Sponsor recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.SPONSOR));
	 * Assert.notNull(m);
	 * m.setSender(sender);
	 * Collection<Actor> receivers;
	 * receivers = new ArrayList<>();
	 * receivers.add(recipient);
	 * 
	 * m.setReceiver(receivers);
	 * Collection<Message> received;
	 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	 * Utiles.sendIndividualMessage(recipient, received, m);
	 * this.sponsorService.addSponsor(recipient);
	 * }
	 * 
	 * public void sendMessage(final HandyWorker sender, final Referee recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.REFEREE));
	 * Assert.notNull(m);
	 * m.setSender(sender);
	 * Collection<Actor> receivers;
	 * receivers = new ArrayList<>();
	 * receivers.add(recipient);
	 * m.setReceiver(receivers);
	 * Collection<Message> received;
	 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	 * Utiles.sendIndividualMessage(recipient, received, m);
	 * this.refereeService.save(recipient);
	 * }
	 * 
	 * public void sendMessage(final HandyWorker sender, final Customer recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.CUSTOMER));
	 * Assert.notNull(m);
	 * m.setSender(sender);
	 * Collection<Actor> receivers;
	 * receivers = new ArrayList<>();
	 * receivers.add(recipient);
	 * m.setReceiver(receivers);
	 * Collection<Message> received;
	 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	 * Utiles.sendIndividualMessage(recipient, received, m);
	 * this.customerService.save(recipient);
	 * }
	 * 
	 * public void sendMessage(final HandyWorker sender, final HandyWorker recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.notNull(m);
	 * m.setSender(sender);
	 * Collection<Actor> receivers;
	 * receivers = new ArrayList<>();
	 * receivers.add(recipient);
	 * m.setReceiver(receivers);
	 * Collection<Message> received;
	 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	 * Utiles.sendIndividualMessage(recipient, received, m);
	 * this.repositoryHandyWorker.save(recipient);
	 * }
	 */public Collection<FixUpTask> findByKeyWord(final String s) {
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
