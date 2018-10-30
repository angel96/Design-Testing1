
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Note extends DomainEntity {

	private Date				moment;
	private String				comment;
	private Collection<String>	otherComments;


	@NotBlank
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public Collection<String> getOtherComments() {
		return this.otherComments;
	}

	public void setOtherComments(final Collection<String> otherComments) {
		this.otherComments = otherComments;
	}
}
