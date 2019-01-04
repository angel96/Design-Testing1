
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Actor;
import domain.Complaint;
import domain.Customer;

@Service
@Transactional
public class ComplaintService {

	@Autowired
	private ComplaintRepository	complaintRepository;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private FixUpTaskService	serviceFix;


	public Collection<Complaint> findAll() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));
		return this.complaintRepository.findAll();
	}

	public Complaint findOne(final int id) {
		return this.complaintRepository.findOne(id);
	}

	public Actor getActorByUser(final int id) {
		return this.complaintRepository.findActorByUserAccountId(id);
	}

	public Collection<Complaint> findComplaintByHandyWorkerId(final int handyWId) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		return this.complaintRepository.findComplaintByHandyWorkerId(handyWId);
	}

	public Collection<Complaint> findComplaintByReferee(final int refereeId) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		return this.complaintRepository.findComplaintByRefereeId(refereeId);
	}
	public Collection<Complaint> findComplaintNoRefereeAssigned() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		return this.complaintRepository.findComplaintNoRefereeAssigned();
	}

	public Collection<Complaint> findComplaintRefereeAssigned() {
		return this.complaintRepository.findComplaintRefereeAssigned();
	}

	public Complaint save(final Complaint comp) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE) || Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));
		Assert.notNull(user);
		Customer c;
		c = this.customerService.findByUserAccount(user.getId());
		Collection<Complaint> complaintPerCustomer;
		complaintPerCustomer = this.complaintRepository.findComplaintByCustomerId(c.getId());
		Complaint saved;
		saved = this.complaintRepository.save(comp);
		complaintPerCustomer.add(saved);
		c.setComplaint(complaintPerCustomer);
		return saved;
	}

	public Double min() {
		return this.complaintRepository.findMinimumOfComplaintsPerFixUpTask();
	}

	public Double max() {
		return this.complaintRepository.findMaximumOfComplaintsPerFixUpTask();
	}

	public Double avg() {
		return this.complaintRepository.findAverageOfComplaintsPerFixUpTask();
	}

	public Double stdev() {
		return this.complaintRepository.findStandartDeviationOfComplaintsPerFixUpTask();
	}

	public Customer findCustomerByComplaint(final Complaint c) {
		return this.complaintRepository.findCustomerByComplaintId(c.getId());
	}

	public Collection<Complaint> findComplaintByCustomer(final Actor c) {
		return this.complaintRepository.findComplaintByCustomerId(c.getId());
	}
}
