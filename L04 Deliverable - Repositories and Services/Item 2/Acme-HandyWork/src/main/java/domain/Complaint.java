
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Complaint extends DomainEntity {

	private String				ticker;
	private Date				moment;
	private String				description;
	private Integer				attachment;
	private Collection<Report>	report;


	@Pattern(regexp = "^\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])-[\\w]{6}$")
	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	@Temporal(TemporalType.DATE)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@Min(value = 0)
	public Integer getAttachment() {
		return this.attachment;
	}

	public void setAttachment(final Integer attachment) {
		this.attachment = attachment;
	}
	@OneToMany(mappedBy = "complaints")
	public Collection<Report> getReport() {
		return this.report;
	}

	public void setReport(final Collection<Report> report) {
		this.report = report;
	}

}
