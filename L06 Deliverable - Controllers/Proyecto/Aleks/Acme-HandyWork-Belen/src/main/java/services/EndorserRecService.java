
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.EndorserRecRepository;
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecService {

	@Autowired
	private EndorserRecRepository	endorserRecRepository;


	public Collection<EndorserRecord> findAll() {
		return this.endorserRecRepository.findAll();
	}

	public EndorserRecord findOne(final int idEndorser) {
		return this.endorserRecRepository.findOne(idEndorser);
	}
}
