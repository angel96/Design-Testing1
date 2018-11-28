
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;


	public Collection<Sponsorship> findAll() {
		return this.sponsorshipRepository.findAll();
	}

	public Sponsorship findOne(final int id) {
		return this.sponsorshipRepository.findOne(id);
	}

	public Sponsorship add(final Sponsorship sponsorship) {

		return null;
	}
	public Sponsorship update(final int id, final Sponsorship newer) {
		return null;
	}
	public void delete(final int id) {
		Assert.notNull(this.findOne(id));
		this.sponsorshipRepository.delete(id);
	}

}
