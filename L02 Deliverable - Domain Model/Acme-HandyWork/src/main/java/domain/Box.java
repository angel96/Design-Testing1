
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Box extends DomainEntity {

	private String	name;
	private Boolean	fromSystem;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Boolean getFromSystem() {
		return this.fromSystem;
	}

	public void setFromSystem(final Boolean fromSystem) {
		this.fromSystem = fromSystem;
	}

}
