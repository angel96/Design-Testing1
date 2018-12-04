
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import security.LoginService;
import security.UserAccount;
import domain.HandyWorker;
import domain.Section;

@Service
@Transactional
public class SectionService {

	// 1. Let´s instance the corresponding repository
	@Autowired
	private SectionRepository	sectionRepository;

	@Autowired
	private HandyWorkerService	serviceHandyWorker;


	// 2. CRUD

	public Collection<Section> findAll() {
		return this.sectionRepository.findAll();
	}
	public Section findById(final int id) {
		return this.sectionRepository.findOne(id);
	}

	public Section addSection(final Section s) {

		UserAccount user;
		user = LoginService.getPrincipal();

		HandyWorker w;
		w = this.serviceHandyWorker.findByUserAccount(user.getId());

		Assert.notNull(w);

		Section result;
		Assert.notNull(s);
		result = this.sectionRepository.save(s);
		Assert.notNull(result);

		return result;
	}
	public Section updateSection(final Section n) {
		UserAccount user;
		user = LoginService.getPrincipal();

		HandyWorker w;
		w = this.serviceHandyWorker.findByUserAccount(user.getId());

		Assert.notNull(w);
		Section saved;

		saved = this.sectionRepository.save(n);
		Assert.notNull(saved);
		return saved;
	}

	public void deleteSection(final Section s) {
		UserAccount user;
		user = LoginService.getPrincipal();

		HandyWorker w;
		w = this.serviceHandyWorker.findByUserAccount(user.getId());

		Assert.notNull(w);
		Assert.notNull(s);
		this.sectionRepository.delete(s.getId());
	}
}
