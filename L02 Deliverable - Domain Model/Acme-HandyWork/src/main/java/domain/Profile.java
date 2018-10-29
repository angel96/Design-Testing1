
package domain;

import javax.persistence.Entity;

@Entity
public class Profile extends DomainEntity {

	private String	nick;
	private String	socialNetworkName;
	private String	link;


	public String getNick() {
		return this.nick;
	}

	public void setNick(final String nick) {
		this.nick = nick;
	}

	public String getSocialNetworkName() {
		return this.socialNetworkName;
	}

	public void setSocialNetworkName(final String socialNetworkName) {
		this.socialNetworkName = socialNetworkName;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

}
