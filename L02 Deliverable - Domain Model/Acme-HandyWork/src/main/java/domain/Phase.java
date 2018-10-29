
package domain;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Phase extends DomainEntity {

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@NotBlank
	public Date getStartMoment() {
		return this.startMoment;
	}

	public void setStartMoment(final Date startMoment) {
		this.startMoment = startMoment;
	}
	@NotBlank
	public Date getEndMoment() {
		return this.endMoment;
	}

	public void setEndMoment(final Date endMoment) {
		this.endMoment = endMoment;
	}


	private String	title;
	private String	description;
	private Date	startMoment;
	private Date	endMoment;

}
