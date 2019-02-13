
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Section;
import domain.Tutorial;

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
	public Section findOne(final int id) {
		return this.sectionRepository.findOne(id);
	}
	public Section createSection() {

		Section s;

		s = new Section();

		s.setTitle("");
		s.setText("");
		s.setNumber(1);
		s.setPicture(new ArrayList<String>());

		return s;
	}

	public Tutorial findBySection(final int section) {
		return this.sectionRepository.findTutorialBySection(section);
	}

	public Section save(final Tutorial t, final Section s) {

		UserAccount user;
		user = LoginService.getPrincipal();

		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));

		Section result;
		result = this.sectionRepository.save(s);

		if (s.getId() == 0) {
			Collection<Section> sections;

			sections = t.getSection();

			sections.add(result);

			t.setSection(sections);

		}

		return result;
	}

	public void delete(final Tutorial t, final Section s) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));

		Collection<Section> sections;

		sections = t.getSection();

		sections.remove(s);

		t.setSection(sections);

		this.sectionRepository.delete(s.getId());
	}
}
