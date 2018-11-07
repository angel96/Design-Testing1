
package domain;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Endorsement extends DomainEntity {

	private Date	moment;
	private String	comments;


	@NotBlank
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

}
