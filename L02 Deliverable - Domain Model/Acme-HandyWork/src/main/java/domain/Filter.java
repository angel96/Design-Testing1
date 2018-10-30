
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Filter extends DomainEntity {

	private String	name;
	private Integer	value;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	@NotBlank
	public Integer getValue() {
		return this.value;
	}

	public void setValue(final Integer value) {
		this.value = value;
	}
}
