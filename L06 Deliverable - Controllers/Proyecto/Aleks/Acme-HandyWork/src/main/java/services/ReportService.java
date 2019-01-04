
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
		Assert.notNull(user);
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		//		Referee r;
		//		r = this.refereeService.findByUserAccount(user.getId());
		//		Collection<Complaint> complaintPerReferee;
		//		complaintPerReferee = new ArrayList<>();
		//		complaintPerReferee = r.getComplaints();//complaintPerReferee = this.complaintRepository.findComplaintByRefereeId(user.getId());

		Complaint c;
		c = rep.getComplaint();//c = this.reportRepository.findComplaintByReportId(rep.getId());
		Collection<Report> reportPerComplaint;
		reportPerComplaint = c.getReport();//reportPerComplaint = this.reportRepository.findReportsByComplaintId(c.getId());
		Report saved;
		saved = this.reportRepository.save(rep);

		reportPerComplaint.add(saved);
		c.setReport(reportPerComplaint);
		return saved;
	}

	public void delete(final Report rep) {
		Assert.notNull(rep);
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		this.reportRepository.delete(rep);
	}
}
