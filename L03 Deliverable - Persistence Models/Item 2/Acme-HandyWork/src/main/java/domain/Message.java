
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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Message extends DomainEntity {

	private Actor				sender;
	private Actor				receiver;
	private Date				moment;
	private String				subject;
	private String				body;
	private Collection<String>	tags;
	private Priority			priority;


	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}
	@ManyToOne(optional = false)
	public Actor getReceiver() {
		return this.receiver;
	}

	public void setReceiver(final Actor receiver) {
		this.receiver = receiver;
	}

	@Temporal(TemporalType.DATE)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}
	@NotBlank
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}
	@ElementCollection
	public Collection<String> getTags() {
		return this.tags;
	}

	public void setTags(final Collection<String> tags) {
		this.tags = tags;
	}
	@NotNull
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}
}
