
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import utilities.Utiles;
import domain.Actor;
import domain.Administrator;
import domain.Box;
import domain.Customer;
import domain.HandyWorker;
import domain.Message;
import domain.Referee;
import domain.Sponsor;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	adminRepository;
	@Autowired
	private SponsorService			sponsorService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private HandyWorkerService		hwService;
	@Autowired
	private RefereeService			refereeService;
	@Autowired
	private BoxService				boxService;
	@Autowired
	private ActorService			actorService;


	public Administrator createAnotherAdministrator() {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Administrator administrator;
		administrator = Utiles.createAdministrator();
		Assert.notNull(administrator);
		return administrator;
	}

	public Administrator findOne(final int userAccount) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Administrator saved;
		saved = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(saved);
		return saved;
	}

	public Administrator save(final Administrator admin) {
		Assert.isTrue(admin.getAccount().getId() == this.adminRepository.findAdministratorById(admin.getAccount().getId()).getAccount().getId());
		Assert.notNull(admin);
		return this.adminRepository.save(admin);
	}

	public Administrator update(final Administrator admin) {
		Assert.notNull(admin);
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Assert.isTrue(admin.getAccount().getId() == this.adminRepository.findAdministratorById(admin.getAccount().getId()).getAccount().getId());
		Administrator saved;
		saved = this.save(admin);
		Assert.notNull(saved);
		return saved;
	}

	public void broadcastMessage(final Administrator admin, final Message m) { //TODO Hay que mirarlo
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Assert.notNull(m);

		Collection<Administrator> allAdmins;
		allAdmins = this.adminRepository.findAll();
		Assert.notNull(allAdmins);
		Utiles.broadcastMessage(allAdmins, m);

		Collection<Sponsor> allSponsors;
		allSponsors = this.sponsorService.findAll();
		Assert.notNull(allSponsors);
		Utiles.broadcastMessage(allSponsors, m);

		Collection<Referee> allReferees;
		allReferees = this.refereeService.findAll();
		Assert.notNull(allReferees);
		Utiles.broadcastMessage(allReferees, m);

		Collection<Customer> allCustomers;
		allCustomers = this.customerService.findAll();
		Assert.notNull(allCustomers);
		Utiles.broadcastMessage(allCustomers, m);

		Collection<HandyWorker> allHandyWorkers;
		allHandyWorkers = this.hwService.findAll();
		Assert.notNull(allHandyWorkers);
		Utiles.broadcastMessage(allHandyWorkers, m);
	}
	//Admin dashboard
	public Collection<Customer> findCustomersWith10PerCentMoreFixUpPublishedThanAvgOrderApps(final Administrator admin) {
		Administrator administrator;
		administrator = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(administrator);
		return this.adminRepository.findCustomers10PerCentMoreFixUpThanAvgOrderApplication();
	}

	public Collection<HandyWorker> findHandyWorkersWith10PerCentMoreAppsPublishedThanAvgOrderApps(final Administrator admin) {
		Administrator administrator;
		administrator = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(administrator);
		return this.adminRepository.findHandyWorkers10PerCentMoreAppsThanAvgAcepptedOrderApplication();
	}

	public Collection<Customer> topThreeCustomerOrderByComplaints(final Administrator admin) {
		Administrator administrator;
		administrator = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(administrator);
		List<Customer> topThree;
		topThree = this.adminRepository.topTreeCustomerOrderByComplaints();
		List<Customer> result;
		result = topThree.subList(0, 3);
		return result;
	}

	public Collection<HandyWorker> topThreeHandyWorkerOrderByComplaints(final Administrator admin) {
		Administrator administrator;
		administrator = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(administrator);
		List<HandyWorker> topThree;
		topThree = this.adminRepository.topTreeHandyWorkerOrderByComplaints();
		List<HandyWorker> result;
		result = topThree.subList(0, 3);
		return result;
	}

	public Collection<Box> manageNotSystemBoxes() {
		Assert.notNull(this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNotSystemBoxes(LoginService.getPrincipal().getId());
		return boxes;
	}
	public void sendMessage(final Actor sender, final Collection<Actor> recipient, final Message m) {
		Assert.notNull(LoginService.getPrincipal().getAuthorities());
		Assert.notEmpty(recipient);
		Assert.notNull(m);
		this.actorService.sendIndividualMessage(sender, recipient, m);
	}

	public void banActor(final Actor toBan) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Assert.notNull(toBan);
		this.actorService.banActor(toBan);
	}

	public void unbanActor(final Actor toUnban) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Assert.notNull(toUnban);
		this.actorService.unbanActor(toUnban);
	}

	public Collection<Actor> getSuspiciousActors() {
		Collection<Actor> actors;
		actors = new ArrayList<>();
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		actors = this.adminRepository.getSuspiciousActors();
		Assert.notNull(actors);
		return actors;
	}

}
