
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import domain.Section;

@Service
@Transactional
public class SectionService {

	// 1. Let´s instance the corresponding repository
	@Autowired
	private SectionRepository	sectionRepository;


	// 2. CRUD

	public Collection<Section> findAll() {
		return this.sectionRepository.findAll();
	}
	public Section findById(final int id) {
		return this.sectionRepository.findOne(id);
	}

	public Section addSection(final Section s) {

		Section result;
		Assert.notNull(s);
		result = this.sectionRepository.save(s);
		Assert.notNull(result);

		return result;
	}
	public Section updateSection(final Section n) {
		Section saved;

		saved = this.sectionRepository.save(n);
		Assert.notNull(saved);
		return saved;
	}

	public void deleteSection(final Section s) {
		Assert.notNull(s);
		this.sectionRepository.delete(s.getId());
	}
}
