
package domain;

import java.util.Collection;

import javax.persistence.Entity;

@Entity
public class Curriculum extends DomainEntity {

	private Collection<EducationRecord>		educationRecord;
	private Collection<EndorserRecord>		endorserRecord;
	private Collection<ProfessionalRecord>	professionalRecord;
	private Collection<MiscellaneousRecord>	miscellaneousRecord;
	private Collection<PersonalRecord>		personalRecord;


	public Collection<EducationRecord> getEducationRecord() {
		return this.educationRecord;
	}

	public void setEducationRecord(final Collection<EducationRecord> educationRecord) {
		this.educationRecord = educationRecord;
	}

	public Collection<EndorserRecord> getEndorserRecord() {
		return this.endorserRecord;
	}

	public void setEndorserRecord(final Collection<EndorserRecord> endorserRecord) {
		this.endorserRecord = endorserRecord;
	}

	public Collection<ProfessionalRecord> getProfessionalRecord() {
		return this.professionalRecord;
	}

	public void setProfessionalRecord(final Collection<ProfessionalRecord> professionalRecord) {
		this.professionalRecord = professionalRecord;
	}

	public Collection<MiscellaneousRecord> getMiscellaneousRecord() {
		return this.miscellaneousRecord;
	}

	public void setMiscellaneousRecord(final Collection<MiscellaneousRecord> miscellaneousRecord) {
		this.miscellaneousRecord = miscellaneousRecord;
	}

	public Collection<PersonalRecord> getPersonalRecord() {
		return this.personalRecord;
	}

	public void setPersonalRecord(final Collection<PersonalRecord> personalRecord) {
		this.personalRecord = personalRecord;
	}

}
