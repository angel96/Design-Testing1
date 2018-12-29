
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomisationSystemRepository;
import domain.Actor;
import domain.CustomisationSystem;

@Service
@Transactional
public class CustomisationSystemService {

	@Autowired
	private CustomisationSystemRepository	repositoryCustomisationSystem;


	public Collection<Actor> findAllSuspiciousActors() {
		return this.repositoryCustomisationSystem.findAllSuspiciousActors();
	}

	public CustomisationSystem findUnique() {
		return this.repositoryCustomisationSystem.findAll().get(0);
	}

	public CustomisationSystem save(final CustomisationSystem custom) {
		return this.repositoryCustomisationSystem.save(custom);
	}
}
