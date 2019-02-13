
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WageRepository;
import security.Authority;
import security.LoginService;
import utilities.Utiles;
import domain.FixUpTask;
import domain.Wage;

@Service
@Transactional
public class WageService {

	@Autowired
	private WageRepository		wageRepo;
	@Autowired
	private FixUpTaskService	fixUpService;


	public Collection<Wage> findAll() {
		Collection<Wage> q;
		q = this.wageRepo.findAll();
		Assert.notNull(q);
		return q;
	}

	public Wage findOne(final int id) {
		Wage q;
		q = this.wageRepo.findOne(id);
		Assert.notNull(q);
		return q;
	}

	public Collection<Wage> getAllByUser(final int accountId) {
		Collection<Wage> qs;
		qs = this.wageRepo.getWagesByUser(accountId);
		Assert.notNull(qs);
		return qs;
	}

	public Collection<Wage> getAllByFixUp(final int fixId) {
		Collection<Wage> qs;
		qs = this.wageRepo.getWagesForFixUp(fixId);
		Assert.notNull(qs);
		return qs;
	}

	public Wage save(final Wage w, final FixUpTask f) {
		Assert.notNull(w);
		Assert.notNull(f);
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));

		if (w.getFinalMode())
			w.setPublicationMoment(new Date());
		Wage saved;
		saved = this.wageRepo.save(w);
		Collection<Wage> ws;
		ws = f.getWages();
		if (!ws.contains(saved)) {
			w.setPublicationMoment(new Date());
			ws.add(saved);
			f.setWages(ws);
		}

		return saved;
	}

	public void delete(final int id) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
		FixUpTask f;
		f = this.fixUpService.getFixUpTaskByWage(id);

		Collection<Wage> collect;
		collect = f.getWages();
		collect.remove(this.wageRepo.findOne(id));
		f.setWages(collect);
		this.wageRepo.delete(id);
	}

	public Wage create() {
		Wage w;
		w = new Wage();
		w.setTicker(Utiles.generateWageTicker());
		w.setBody("");
		w.setFinalMode(false);
		w.setPublicationMoment(new Date());
		w.setUrl("");
		return w;
	}
}
