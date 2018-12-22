
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CustomisationSystem extends DomainEntity {

	private String	systemName;
	private String	banner;
	private String	message;
	private Collection<String> spamWords;
	private Collection<String> goodWords;
	private Collection<String> badWords;
	private Integer hoursFinder;
	private Integer resultFinder;


	@NotBlank
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}
	@NotBlank
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}
	@NotBlank
	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
	@ElementCollection
	public Collection<String> getSpamWords() {
		return spamWords;
	}

	public void setSpamWords(Collection<String> spamWords) {
		this.spamWords = spamWords;
	}
	@ElementCollection
	public Collection<String> getGoodWords() {
		return goodWords;
	}

	public void setGoodWords(Collection<String> goodWords) {
		this.goodWords = goodWords;
	}
	@ElementCollection
	public Collection<String> getBadWords() {
		return badWords;
	}

	public void setBadWords(Collection<String> badWords) {
		this.badWords = badWords;
	}
	@Range(min=1,max=24)
	public Integer getHoursFinder() {
		
		return hoursFinder;
	}

	public void setHoursFinder(Integer hoursFinder) {
		this.hoursFinder = hoursFinder;
	}
	@Range(min=10,max=100)
	public Integer getResultFinder() {
		return resultFinder;
	}

	public void setResultFinder(Integer resultFinder) {
		this.resultFinder = resultFinder;
	}
	
	
	
}
