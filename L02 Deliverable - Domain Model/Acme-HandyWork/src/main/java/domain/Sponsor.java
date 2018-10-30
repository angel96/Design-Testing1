
package domain;

import java.util.Collection;

import javax.persistence.Entity;

@Entity
public class Sponsor extends Actor {

	private Collection<Sponsorship>	sponsorships;	//??


	public Collection<Sponsorship> getSponsorships() {
		return this.sponsorships;
	}

	public void setSponsorships(final Collection<Sponsorship> sponsorships) {
		this.sponsorships = sponsorships;
	}

}
