
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Section extends DomainEntity {

	private String				title;
	private String				text;
	private Collection<String>	picture;
	private Integer				number;


	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getText() {
		return this.text;
	}
	public void setText(final String text) {
		this.text = text;
	}

	@ElementCollection
	public Collection<String> getPicture() {
		return this.picture;
	}
	public void setPicture(final Collection<String> picture) {
		this.picture = picture;
	}
	@Min(1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getNumber() {
		return this.number;
	}
	public void setNumber(final Integer number) {
		this.number = number;
	}

}
