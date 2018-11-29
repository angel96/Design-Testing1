
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Administrator;
import domain.Box;
import domain.Customer;
import domain.HandyWorker;
import domain.Message;
import domain.Referee;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository		sponsorRepository;
	@Autowired
	private RefereeService			refereeService;
	@Autowired
	private AdministratorService	adminService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private HandyWorkerService		hwService;
	@Autowired
	private BoxService				boxService;
	@Autowired
	private MessageService			messageService;


	public Collection<Sponsor> findAll() {
		return this.sponsorRepository.findAll();
	}

	public Collection<Sponsorship> getSponsorshipsBySponsor(final Sponsor s) {
		Collection<Sponsorship> result;
		Assert.notNull(s);
		result = this.sponsorRepository.getSponsorshipsBySponsor(s.getId());
		Assert.isTrue(result.size() >= 0);
		return result;
	}

	public Sponsor findById(final int id) {
		return this.sponsorRepository.findOne(id);
	}
	public Sponsor findByUserAccount(final UserAccount userAccount) {
		Sponsor s;
		Assert.notNull(userAccount);
		s = this.sponsorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(s);
		return s;
	}
	public Sponsor create() {
		Sponsor w;

		w = new Sponsor();

		w.setName("");
		w.setMiddleName("");
		w.setSurname("");
		w.setEmail("");
		w.setAdress("");
		w.setBan(false);
		w.setPhoto("");
		//		final Collection<Sponsorship> sponsorship;
		//		w.setSponsorship(sponsorship);
		//		final Collection<Profile> profile;
		//		w.setProfiles(profile);

		UserAccount user;
		user = new UserAccount();
		user.setUsername("");
		user.setPassword("");

		Authority authority;
		authority = new Authority();

		authority.setAuthority(Authority.SPONSOR);

		user.addAuthority(authority);

		w.setAccount(user);

		return w;
	}

	public Sponsor addSponsor(final Sponsor s) {
		Sponsor result;
		Assert.notNull(s);
		result = this.sponsorRepository.save(s);
		Assert.notNull(result);
		return result;
	}

	public Sponsor updateSponsor(final int id, final Sponsor s) {

		Sponsor update;
		update = this.findById(id);
		Assert.notNull(update);
		Assert.notNull(s);
		update.setId(id);
		update.setName(s.getName());
		update.setMiddleName(s.getMiddleName());
		update.setSurname(s.getSurname());
		update.setEmail(s.getEmail());
		update.setPhone(s.getPhone());
		update.setPhoto(s.getPhoto());
		update.setProfiles(s.getProfiles());
		update.setAdress(s.getAdress());
		update.setAccount(s.getAccount());
		update.setSponsorship(s.getSponsorship());

		return this.sponsorRepository.save(update);
	}
	public Collection<Box> manageNotSystemBoxes(final Sponsor sponsor) {
		Assert.notNull(this.sponsorRepository.findByUserAccountId(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNonBoxes(sponsor.getId());
		return boxes;
	}
	public void sendMessage(final Sponsor sender, final Administrator recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.ADMIN));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.adminService.save(recipient);
	}

	public void sendMessage(final Sponsor sender, final Sponsor recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.SPONSOR));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.sponsorRepository.save(recipient);
	}

	public void sendMessage(final Sponsor sender, final Referee recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.REFEREE));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.refereeService.save(recipient);
	}

	public void sendMessage(final Sponsor sender, final Customer recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.CUSTOMER));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.customerService.save(recipient);
	}

	public void sendMessage(final Sponsor sender, final HandyWorker recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.SPONSOR));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.hwService.save(recipient);
	}
}
