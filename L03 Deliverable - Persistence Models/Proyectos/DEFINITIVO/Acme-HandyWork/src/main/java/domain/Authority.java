
package domain;

public class Authority extends DomainEntity {

	private final String	customer	= "CUSTOMER";
	private final String	admin		= "ADMIN";
	private final String	referee		= "REFEREE";
	private final String	sponsor		= "SPONSOR";
	private final String	handyWorker	= "HANDYWORKER";
	private String			authority;


	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(final String authority) {
		this.authority = authority;
	}

	public String getCustomer() {
		return this.customer;
	}

	public String getAdmin() {
		return this.admin;
	}

	public String getReferee() {
		return this.referee;
	}

	public String getSponsor() {
		return this.sponsor;
	}

	public String getHandyWorker() {
		return this.handyWorker;
	}

}
