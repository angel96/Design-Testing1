package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Section extends DomainEntity{
	
	private String title;
	private String text;
	private Integer code;
	private Collection<String> picture;
	private Integer number;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    @NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
//	@NotBlank
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	@ElementCollection
	public Collection<String> getPicture() {
		return this.picture;
	}
	public void setPicture(final Collection<String> picture) {
		this.picture = picture;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	

}
