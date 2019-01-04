
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import repositories.ReportRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Complaint;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private ComplaintRepository	complaintRepository;

	@Autowired
	private RefereeService		refereeService;


	public Collection<Report> findReportsByReferee(final Referee r) {
		return this.reportRepository.findReportsByRefereeId(r.getId());
	}

	public Report findOne(final int idReport) {
		return this.reportRepository.findOne(idReport);
	}
	public Collection<Report> findAllFinalMode() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) || Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));
		return this.reportRepository.findReportinFinalMode();
	}

	public Report save(final Report rep) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		Assert.notNull(user);
		Referee r;
		r = this.refereeService.findByUserAccount(user.getId());
		Collection<Complaint> complaintPerReferee;
		complaintPerReferee = this.complaintRepository.findComplaintByRefereeId(user.getId());
		Assert.isTrue(complaintPerReferee.contains(this.reportRepository.findComplaintByReportId(rep.getId())));
		Complaint c;
		c = this.reportRepository.findComplaintByReportId(rep.getId());
		Collection<Report> reportPerComplaint;
		reportPerComplaint = this.reportRepository.findReportsByComplaintId(c.getId());
		Report saved;
		saved = this.reportRepository.save(rep);
		reportPerComplaint.add(saved);
		c.setReport(reportPerComplaint);
		//this.refereeService.update(r);
		return saved;
	}

	public void delete(final Report rep) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		Assert.notNull(rep);
		this.reportRepository.delete(rep);
	}
}
