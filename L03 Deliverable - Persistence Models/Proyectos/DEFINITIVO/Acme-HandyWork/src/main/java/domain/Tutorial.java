
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
public class Tutorial extends DomainEntity {

	private String				title;
	private Date				lastUpdate;
	private String				summary;
	private Collection<String>	pictures;
	private Collection<Section>	sections;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@NotBlank
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}
	@URL
	public Collection<String> getPictures() {
		return this.pictures;
	}

	public void setPictures(final Collection<String> pictures) {
		this.pictures = pictures;
	}

	public Collection<Section> getSections() {
		return this.sections;
	}

	public void setSections(final Collection<Section> sections) {
		this.sections = sections;
	}

}
