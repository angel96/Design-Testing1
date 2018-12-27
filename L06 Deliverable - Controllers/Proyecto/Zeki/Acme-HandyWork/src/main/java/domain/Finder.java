
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
	private String singleKey;
	private Category category;
	private Warranty warranty;
	private Double price1;
	private Double price2;
	private Date creationDate;

	@OneToMany
	public Collection<FixUpTask> getFixUpTask() {
		return fixUpTask;
	}
	public void setFixUpTask(Collection<FixUpTask> fixUpTask) {
		this.fixUpTask = fixUpTask;
	}
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSingleKey() {
		return singleKey;
	}
	public void setSingleKey(String singleKey) {
		this.singleKey = singleKey;
	}
	@OneToOne(optional = true)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@OneToOne(optional = true)
	public Warranty getWarranty() {
		return warranty;
	}
	public void setWarranty(Warranty warranty) {
		this.warranty = warranty;
	}
	public Double getPrice1() {
		return price1;
	}
	public void setPrice1(Double price1) {
		this.price1 = price1;
	}
	public Double getPrice2() {
		return price2;
	}
	public void setPrice2(Double price2) {
		this.price2 = price2;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	

	
	
}
