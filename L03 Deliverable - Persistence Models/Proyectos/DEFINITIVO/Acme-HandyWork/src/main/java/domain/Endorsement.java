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

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Endorsement extends DomainEntity {
	
	private Date moment;
	private Collection<String> comments;
	private Endorsable userReceived;
	private Endorsable userSended;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	@ElementCollection
	public Collection<String> getComments() {
		return comments;
	}
	public void setComments(Collection<String> comments) {
		this.comments = comments;
	}
	@ManyToOne(optional=false)
	public Endorsable getUserReceived() {
		return userReceived;
	}
	public void setUserReceived(Endorsable userReceived) {
		this.userReceived = userReceived;
	}
	@ManyToOne(optional=false)
	public Endorsable getUserSended() {
		return userSended;
	}
	public void setUserSended(Endorsable userSended) {
		this.userSended = userSended;
	}



	
	
	

}
