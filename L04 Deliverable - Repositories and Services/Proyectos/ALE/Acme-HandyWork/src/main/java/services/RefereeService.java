
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.LoginService;
import utilities.Utiles;
import domain.Administrator;
import domain.Box;
import domain.Customer;
import domain.HandyWorker;
import domain.Message;
import domain.Referee;
import domain.Sponsor;

@Service
@Transactional
public class RefereeService {

	@Autowired
	private RefereeRepository		refereeRepository;
	@Autowired
	private AdministratorService	adminService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private HandyWorkerService		hwService;
	@Autowired
	private SponsorService			sponsorService;
	@Autowired
	private BoxService				boxService;


	public Collection<Referee> findAll() {
		return this.refereeRepository.findAll();
	}
	public Referee save(final Referee r) {
		Assert.notNull(r);
		return this.refereeRepository.save(r);
	}

	public Collection<Box> manageNotSystemBox(final Referee referee) {
		Assert.notNull(this.refereeRepository.findOneByUserAccount(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNonBoxes(referee.getId());
		return boxes;
	}
	public void sendMessage(final Referee sender, final Administrator recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.ADMIN));
		Assert.notNull(m);
		Utiles.sendIndividualMessage(sender, recipient, m);
		this.adminService.save(recipient);
	}

	public void sendMessage(final Referee sender, final Sponsor recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.SPONSOR));
		Assert.notNull(m);
		Utiles.sendIndividualMessage(sender, recipient, m);
		this.sponsorService.addSponsor(recipient);
	}

	public void sendMessage(final Referee sender, final Referee recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.REFEREE));
		Assert.notNull(m);
		Utiles.sendIndividualMessage(sender, recipient, m);
		this.refereeRepository.save(recipient);
	}

	public void sendMessage(final Referee sender, final Customer recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.CUSTOMER));
		Assert.notNull(m);
		Utiles.sendIndividualMessage(sender, recipient, m);
		this.customerService.save(recipient);
	}

	public void sendMessage(final Referee sender, final HandyWorker recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.REFEREE));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		Assert.notNull(m);
		Utiles.sendIndividualMessage(sender, recipient, m);
		this.hwService.save(recipient);
	}

	public Referee findOneByUserAccount(final int userAccount) {
		Referee r;
		r = this.refereeRepository.findOneByUserAccount(userAccount);
		Assert.notNull(r);
		return r;
	}
}
