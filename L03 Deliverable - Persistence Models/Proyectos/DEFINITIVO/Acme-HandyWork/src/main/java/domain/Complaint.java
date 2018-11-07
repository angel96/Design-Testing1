
package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Complaint extends DomainEntity {

	private String		ticker;
	private Date		moment;
	private String		description;
	private Integer		attachment;
	private Report		report;
	private FixUpTask	fixUpTask;


	@Pattern(regexp = "^\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])-[\\w]{6}$")
	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	@NotBlank
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

	public Integer getAttachment() {
		return this.attachment;
	}

	public void setAttachment(final Integer attachment) {
		this.attachment = attachment;
	}

	public Report getReport() {
		return this.report;
	}

	public void setReport(final Report report) {
		this.report = report;
	}

	public FixUpTask getFixUpTask() {
		return this.fixUpTask;
	}

	public void setFixUpTask(final FixUpTask fixUpTask) {
		this.fixUpTask = fixUpTask;
	}
}
