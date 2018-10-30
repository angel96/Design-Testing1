
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Message extends DomainEntity {

	private Date				moment;
	private String				subject;
	private String				body;
	private Collection<String>	tags;
	private Priority			priority;
	private Actor				recipient;	//??


	@NotBlank
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
	@NotBlank
	public Collection<String> getTags() {
		return this.tags;
	}

	public void setTags(final Collection<String> tags) {
		this.tags = tags;
	}

	@NotBlank
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}
}
