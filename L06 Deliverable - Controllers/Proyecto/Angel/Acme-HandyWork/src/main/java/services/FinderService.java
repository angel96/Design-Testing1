
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FinderRepository;
import domain.Finder;
import domain.FixUpTask;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;

	@Autowired
	private FixUpTaskService	serviceFixUptask;


	public Collection<Finder> findAll() {
		return this.finderRepository.findAll();
	}

	public Finder findOne(final int id) {
		return this.finderRepository.findOne(id);
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
		}
		return modify;
	}
}
