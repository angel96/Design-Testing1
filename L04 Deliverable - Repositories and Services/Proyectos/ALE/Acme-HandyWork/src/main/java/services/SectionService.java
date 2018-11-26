
package services;

import java.util.ArrayList;
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

	public Collection<String> findPicturesBySection(final int id) {
		return this.findById(id).getPicture();
	}
	public Section create() {

		Section s;

		s = new Section();

		s.setTitle("");
		s.setText("");
		s.setNumber(1);
		s.setPicture(new ArrayList<String>());

		return s;
	}
	public Section addSection(final Section s) {

		Section result;
		Assert.notNull(s);
		result = this.sectionRepository.save(s);
		Assert.notNull(result);

		return result;
	}
	public Section updateSection(final int id, final Section n) {
		Section update, saved;

		update = this.sectionRepository.findOne(id);
		Assert.notNull(update);

		update.setId(id);
		update.setTitle(n.getText());
		update.setText(n.getText());
		update.setNumber(n.getNumber());
		update.setPicture(n.getPicture());
		saved = this.sectionRepository.save(update);
		Assert.notNull(saved);
		return saved;
	}

	public void deleteSection(final Section s) {
		Assert.notNull(s);
		this.sectionRepository.delete(s.getId());
	}
}
