
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Note extends DomainEntity {

	private Date	moment;
	private String	customerComment;
	private String	refereeComment;
	private String	handyWorkerComment;


	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public String getCustomerComment() {
		return this.customerComment;
	}

	public void setCustomerComment(final String customerComment) {
		this.customerComment = customerComment;
	}

	public String getRefereeComment() {
		return this.refereeComment;
	}

	public void setRefereeComment(final String refereeComment) {
		this.refereeComment = refereeComment;
	}

	public String getHandyWorkerComment() {
		return this.handyWorkerComment;
	}

	public void setHandyWorkerComment(final String handyWorkerComment) {
		this.handyWorkerComment = handyWorkerComment;
	}

}
