
package domain;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Application extends DomainEntity {

	private Date		moment;
	private double		offeredPrice;
	private String		comments;
	private String		status;
	private FixUpTask	fixUpTask;


	@NotBlank
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public double getOfferedPrice() {
		return this.offeredPrice;
	}

	public void setOfferedPrice(final double offeredPrice) {
		this.offeredPrice = offeredPrice;
	}
	@NotBlank
	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}
	@NotBlank
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public FixUpTask getFixUpTask() {
		return this.fixUpTask;
	}

	public void setFixUpTask(final FixUpTask fixUpTask) {
		this.fixUpTask = fixUpTask;
	}
}
