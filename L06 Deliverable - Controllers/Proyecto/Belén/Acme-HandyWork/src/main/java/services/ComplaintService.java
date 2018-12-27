
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Complaint;
import domain.Customer;
import domain.Report;

@Service
@Transactional
public class ComplaintService {

	@Autowired
	private ComplaintRepository	complaintRepository;

	@Autowired
	private CustomerService		customerService;


	public Collection<Complaint> findAll() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));
		return this.complaintRepository.findAll();
	}

	public Complaint findOne(final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));
		return this.complaintRepository.findOne(id);
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

	public Complaint create() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));
		Complaint res;
		res = new Complaint();
		res.setTicker(Utiles.generateTicker());
		res.setMoment(new Date());
		res.setReport(new ArrayList<Report>());
		return res;
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
		this.customerService.save(c);
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

	public Collection<Complaint> findComplaintByCustomer(final Customer c) {
		return this.complaintRepository.findComplaintByCustomerId(c.getId());
	}
}
