package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Tutorial extends DomainEntity{
	
	private String title;
	private Date lastUpdate;
	private String summary;
	private Collection<String> picture;
	private Collection<Sponsorship> sponsorship;
	private Collection<Section> section;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
//	@NotBlank
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@NotBlank
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
//	@URL
	@ElementCollection
	public Collection<String> getPicture() {
		return picture;
	}
	public void setPicture(Collection<String> picture) {
		this.picture = picture;
	}
	@OneToMany(mappedBy="tutorial") //si lo quito la tabla 
	//sale en my sql y si no pos te jodes
	public Collection<Sponsorship> getSponsorship() {
		return this.sponsorship;
	}
	public void setSponsorship(final Collection<Sponsorship> sponsorship) {
		this.sponsorship = sponsorship;
	}
	@OneToMany(cascade=CascadeType.ALL)
	public Collection<Section> getSection() {
		return section;
	}
	public void setSection(Collection<Section> section) {
		this.section = section;
	}
	
}
