
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.HandyWorker;
import domain.Section;
import domain.Sponsorship;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository	repositoryTutorial;


	public Collection<Tutorial> getTutorialsByHandyWorker(final HandyWorker worker) {
		return this.repositoryTutorial.getTutorialsByHandyWorker(worker.getId());
	}

	public Collection<Tutorial> findAll() {
		return this.repositoryTutorial.findAll();
	}

	public Collection<Section> getSectionsByTutorial(final int id) {
		return this.repositoryTutorial.getSectionsByTutorial(id);
	}

	public Tutorial findOne(final int id) {
		return this.repositoryTutorial.findOne(id);
	}

	public Tutorial create() {
		Tutorial t;
		t = new Tutorial();
		t.setTitle("");
		t.setSummary("");
		t.setSection(new ArrayList<Section>());
		t.setPicture(new ArrayList<String>());
		t.setLastUpdate(new Date());
		t.setSponsorship(new ArrayList<Sponsorship>());
		return t;
	}
	public Tutorial save(final Tutorial t) {
		Assert.notNull(t);
		return this.repositoryTutorial.save(t);
	}

	public Tutorial update() {
		return null;
	}

	public void delete(final int id) {
		Assert.notNull(this.findOne(id));
		this.repositoryTutorial.delete(id);
	}
}
