
package domain;

import java.util.Collection;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Category extends DomainEntity {

	private String					name;
	private Collection<Category>	categories;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public Collection<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(final Collection<Category> categories) {
		this.categories = categories;
	}

}
