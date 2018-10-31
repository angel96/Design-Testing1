
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Endorsable extends Actor {

	private Double		score;
	private Endorsement	endorsement;


	public Endorsement getEndorsement() {
		return this.endorsement;
	}

	public void setEndorsement(final Endorsement endorsement) {
		this.endorsement = endorsement;
	}

	@NotBlank
	public Double getScore() {
		return this.score;
	}

	public void setScore(final Double score) {
		this.score = score;
	}

}
