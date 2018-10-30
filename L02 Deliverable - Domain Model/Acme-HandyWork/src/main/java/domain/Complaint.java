
package domain;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Complaint extends DomainEntity {

	private String	ticker;
	private Date	moment;
	private String	description;
	private Integer	attachment;


	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	@NotBlank
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@NotBlank
	public Integer getAttachment() {
		return this.attachment;
	}

	public void setAttachment(final Integer attachment) {
		this.attachment = attachment;
	}
}
