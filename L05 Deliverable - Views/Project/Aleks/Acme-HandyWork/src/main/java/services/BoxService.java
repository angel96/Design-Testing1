
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import domain.Box;

@Service
@Transactional
public class BoxService {

	@Autowired
	private BoxRepository	boxRepository;


	public Collection<Box> findAllNotSystemBoxes(final int actorId) {
		Assert.notNull(actorId);
		return this.boxRepository.manageNotSystemBoxes(actorId);
	}

}
