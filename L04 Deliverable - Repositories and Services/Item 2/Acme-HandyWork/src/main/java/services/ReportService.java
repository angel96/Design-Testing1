package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Complaint;
import domain.Note;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private RefereeService refereeService;
	
	public Collection<Report> findReportsByReferee(final Referee r) {
		return this.reportRepository.findReportsByRefereeId(r.getId());
	}
	
	public Report findOne(final int idReport) {
		return this.reportRepository.findOne(idReport);
	}
	public Collection<Report> findAllFinalMode() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) ||
				Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));
		return this.reportRepository.findReportinFinalMode();
	}
	
	public Report create() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		Report res;
		res = new Report();
		res.setAttachments(new ArrayList<String>());
		res.setComplaints(new Complaint());
		res.setDescription("");
		res.setFinalMode(false);
		res.setMoment(new Date());
		res.setNotes(new ArrayList<Note>());
		res.setReferee(new Referee());
		return res;
	}
	
	public Report save(final Report rep) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);
		Referee r;
		r = this.refereeService.findByUserAccount(user.getId());
		Collection<Report> reportPerReferee;
		reportPerReferee = this.reportRepository.findReportsByRefereeId(r.getId());
		Report saved;
		saved = this.reportRepository.save(rep);
		reportPerReferee.add(saved);
		r.setReports(reportPerReferee);
		this.refereeService.update(r);
		return saved;
	}
	
	public Report update(final Report rep) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE));
		Assert.notNull(rep);
		Report saved;
		
		saved = this.reportRepository.save(rep);
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
