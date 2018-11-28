
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
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
		return this.complaintRepository.findAll();
	}

	public Complaint findOne(final int id) {
		return this.complaintRepository.findOne(id);
	}

	public Complaint create() {
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
		Assert.notNull(user);
		Customer c;
		c = this.customerService.findByUserAccount(user.getId());
		Collection<Complaint> complaintPerCustomer;
		complaintPerCustomer = this.complaintRepository.findComplaintByCustomerId(c.getId());
		Complaint saved;
		saved = this.complaintRepository.save(comp);
		complaintPerCustomer.add(saved);
		c.setComplaint(complaintPerCustomer);
		this.customerService.update(c);
		return saved;
	}

	public Complaint update(final int idComplaint, final Complaint comp) {
		Complaint c;
		c = this.findOne(idComplaint);
		c.setAttachment(comp.getAttachment());
		c.setDescription(comp.getDescription());
		c.setMoment(comp.getMoment());
		c.setTicker(comp.getTicker());
		c.setReport(comp.getReport());

		UserAccount user;
		user = LoginService.getPrincipal();

		Complaint saved = null;
		if (user.equals(this.complaintRepository.findCustomerByComplaintId(idComplaint)))
			saved = this.complaintRepository.save(c);
		else
			throw new IllegalAccessError("A complaint which doesn't belong to the customer logged can't be modified");
		Assert.notNull(saved);
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
