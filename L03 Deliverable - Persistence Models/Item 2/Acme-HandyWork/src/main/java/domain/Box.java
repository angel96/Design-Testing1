
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Box extends DomainEntity {

	private String				name;
	private boolean				fromSystem;
	private Collection<Message>	message;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean getFromSystem() {
		return this.fromSystem;
	}

	public void setFromSystem(final boolean fromSystem) {
		this.fromSystem = fromSystem;
	}
	@ManyToMany
	public Collection<Message> getMessage() {
		return this.message;
	}

	public void setMessage(final Collection<Message> message) {
		this.message = message;
	}

}
