
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	private Collection<FixUpTask>	fixUpTask;
	private Date					startDate;
	private Date					endDate;
	private String					singleKey;
	private Category				category;
	private Warranty				warranty;
	private Double					price1;
	private Double					price2;
	private Date					creationDate;


	@OneToMany
	public Collection<FixUpTask> getFixUpTask() {
		return this.fixUpTask;
	}
	public void setFixUpTask(final Collection<FixUpTask> fixUpTask) {
		this.fixUpTask = fixUpTask;
	}
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public String getSingleKey() {
		return this.singleKey;
	}

	public void setSingleKey(final String singleKey) {
		this.singleKey = singleKey;
	}

	@OneToOne(optional = true)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}
	@OneToOne(optional = true)
	public Warranty getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final Warranty warranty) {
		this.warranty = warranty;
	}

	public Double getPrice1() {
		return this.price1;
	}

	public void setPrice1(final Double price1) {
		this.price1 = price1;
	}

	public Double getPrice2() {
		return this.price2;
	}

	public void setPrice2(final Double price2) {
		this.price2 = price2;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}
}
