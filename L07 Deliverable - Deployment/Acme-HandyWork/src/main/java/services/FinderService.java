
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FinderRepository;
import utilities.Utiles;
import domain.Actor;
import domain.Finder;
import domain.FixUpTask;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;

	@Autowired
	private FixUpTaskService	serviceFixUptask;


	public Finder findOne(final int id) {
		Finder finder;
		finder = this.finderRepository.findOne(id);
		if (finder.getFixUpTask().size() > 0) {
			System.out.println("Fecha del finder: " + finder.getCreationDate());
			System.out.println("Fecha: " + new Date());
			final Date creationFinder = finder.getCreationDate();
			final Date now = new Date();

			final long diff = now.getTime() - creationFinder.getTime();

			final long diffMinutes = diff / (60 * 1000);

			if (diffMinutes >= Utiles.hoursFinder * 60) {
				Collection<FixUpTask> fcol;
				fcol = finder.getFixUpTask();
				finder.setFixUpTask(new ArrayList<FixUpTask>());
				System.out.println("Seteo la collection vacia");
			}
		}
		return finder;
	}

	public Actor findByUserAccount(final int id) {
		return this.finderRepository.findByUserAccount(id);
	}

	public Finder createFinder() {
		Finder f;
		f = new Finder();
		f.setCategory(null);
		f.setCreationDate(new Date());
		f.setEndDate(new Date());
		f.setFixUpTask(new ArrayList<FixUpTask>());
		f.setPrice1(0.0);
		f.setPrice2(10000.0);
		f.setSingleKey("");
		f.setStartDate(new Date());
		f.setWarranty(null);
		return f;
	}

	public Finder save(final Finder finder) {

		if (finder.getId() != 0) {
			if (finder.getCategory().getId() == 0 || finder.getCategory() == null)
				finder.setCategory(null);
			if (finder.getWarranty().getId() == 0 || finder.getWarranty() == null)
				finder.setWarranty(null);
		}

		Finder modify;
		modify = this.finderRepository.save(finder);

		if (finder.getId() == 0) {
			modify.setCategory(null);
			modify.setWarranty(null);
		}

		if (finder.getId() != 0) {
			final Collection<FixUpTask> fixuptasks = this.serviceFixUptask.findAllByFinder(finder);
			modify.setFixUpTask(fixuptasks);
			modify.setCreationDate(new Date());
		}

		return modify;
	}

}
