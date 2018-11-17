
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.TutorialRepository;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository	repositoryTutorial;


	public Collection<Tutorial> getTutorialsByHandyWorker(final int id) {
		return this.repositoryTutorial.getTutorialsByHandyWorker(id);
	}

}
