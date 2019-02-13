
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import domain.Actor;
import domain.Complaint;
import domain.Customer;
import domain.HandyWorker;
import domain.Note;
import domain.Report;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private ComplaintRepository	complaintRepository;


	public Collection<Report> findReportsByReferee(final Actor r) {
		return this.reportRepository.findReportsByRefereeId(r.getId());
	}

	public Report findOne(final int idReport) {
		return this.reportRepository.findOne(idReport);

	}
	public Collection<Report> findAllFinalModeByActor(final int idReport) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) || Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));
		return this.reportRepository.findReportinFinalModeByActorId(idReport);
	}

	public Actor getActorByUser(final int id) {
		return this.reportRepository.findActorByUserAccountId(id);
	}

	public Report createReport(final int idComp) {
		Report res;
		res = new Report();
		res.setMoment(new Date());
		res.setDescription("");
		res.setAttachments(new ArrayList<String>());
		res.setNotes(new ArrayList<Note>());
		res.setFinalMode(false);
		res.setComplaint(this.complaintRepository.findOne(idComp));

		return res;

	}

	public Report save(final Report rep, final int idComp) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		Report saved;
		saved = this.reportRepository.save(rep);

		Complaint c;
		c = this.complaintRepository.findOne(idComp);
		Collection<Report> coll;
		coll = c.getReport();
		coll.add(saved);
		c.setReport(coll);

		return saved;
	}

	public void delete(final int idRep) {
		Assert.notNull(idRep);
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		Report rep;
		rep = this.reportRepository.findOne(idRep);
		this.reportRepository.delete(rep);
	}

	public Collection<Report> findReportsByCustomerId(final int id) {
		return this.reportRepository.findReportsByCustomerId(id);
	}

	public Collection<Report> findReportsByhandyWorkerId(final int id) {
		return this.reportRepository.findReportsByhandyWorkerId(id);
	}

	public Customer findCustomerByUserAccountId(final int id) {
		return this.reportRepository.findCustomerByUserAccountId(id);
	}

	public HandyWorker findHandyByUserAccountId(final int id) {
		return this.reportRepository.findHandyByUserAccountId(id);
	}
}
