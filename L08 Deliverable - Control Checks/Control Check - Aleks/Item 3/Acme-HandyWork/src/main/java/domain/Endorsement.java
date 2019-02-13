
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Endorsement extends DomainEntity {

	private Date				moment;
	private Collection<String>	comments;
	private Endorsable			userReceived;
	private Endorsable			userSended;


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@ElementCollection
	public Collection<String> getComments() {
		return this.comments;
	}
	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}
	@ManyToOne(optional = false)
	public Endorsable getUserReceived() {
		return this.userReceived;
	}
	public void setUserReceived(final Endorsable userReceived) {
		this.userReceived = userReceived;
	}
	@ManyToOne(optional = false)
	public Endorsable getUserSended() {
		return this.userSended;
	}
	public void setUserSended(final Endorsable userSended) {
		this.userSended = userSended;
	}

}
