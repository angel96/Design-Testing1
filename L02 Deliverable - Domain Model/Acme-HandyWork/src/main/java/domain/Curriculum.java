
package domain;

import javax.persistence.Entity;

@Entity
public class Curriculum extends DomainEntity {

	private EducationRecord		educationRecord;
	private EndorserRecord		endorserRecord;
	private ProfessionalRecord	professionalRecord;
	private MiscellaneousRecord	miscellaneousRecord;
	private PersonalRecord		personalRecord;


	public EducationRecord getEducationRecord() {
		return this.educationRecord;
	}

	public void setEducationRecord(final EducationRecord educationRecord) {
		this.educationRecord = educationRecord;
	}

	public EndorserRecord getEndorserRecord() {
		return this.endorserRecord;
	}

	public void setEndorserRecord(final EndorserRecord endorserRecord) {
		this.endorserRecord = endorserRecord;
	}

	public ProfessionalRecord getProfessionalRecord() {
		return this.professionalRecord;
	}

	public void setProfessionalRecord(final ProfessionalRecord professionalRecord) {
		this.professionalRecord = professionalRecord;
	}

	public MiscellaneousRecord getMiscellaneousRecord() {
		return this.miscellaneousRecord;
	}

	public void setMiscellaneousRecord(final MiscellaneousRecord miscellaneousRecord) {
		this.miscellaneousRecord = miscellaneousRecord;
	}

	public PersonalRecord getPersonalRecord() {
		return this.personalRecord;
	}

	public void setPersonalRecord(final PersonalRecord personalRecord) {
		this.personalRecord = personalRecord;
	}

}
