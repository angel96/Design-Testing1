
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Application extends DomainEntity {

	private Date		moment;
	private double		offeredPrice;
	private String		comments;
	private String		status;
	private FixUpTask	fixUpTask;
	private Date		momentElapsed;
	private boolean		elapsed;


	@Temporal(TemporalType.DATE)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@Digits(integer = 3, fraction = 2)
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

	@ManyToOne(optional = false)
	public FixUpTask getFixUpTask() {
		return this.fixUpTask;
	}

	public void setFixUpTask(final FixUpTask fixUpTask) {
		this.fixUpTask = fixUpTask;
	}

	@Temporal(TemporalType.DATE)
	public Date getMomentElapsed() {
		return this.momentElapsed;
	}

	public void setMomentElapsed(final Date momentElapsed) {
		this.momentElapsed = momentElapsed;
	}

	public boolean isElapsed() {
		return this.elapsed;
	}

	public void setElapsed(final boolean elapsed) {
		this.elapsed = elapsed;
	}

}
