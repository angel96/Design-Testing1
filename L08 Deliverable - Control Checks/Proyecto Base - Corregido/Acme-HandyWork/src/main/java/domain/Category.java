
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Category extends DomainEntity {

	private String					name;
	private Collection<String>		otherlanguages;
	private Collection<Category>	categories;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@OneToMany(cascade = {
		CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH
	})
	public Collection<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(final Collection<Category> categories) {
		this.categories = categories;
	}

	//Position 0 is for spanish
	@ElementCollection
	public Collection<String> getOtherlanguages() {
		return this.otherlanguages;
	}

	public void setOtherlanguages(final Collection<String> otherlanguages) {
		this.otherlanguages = otherlanguages;
	}

}
