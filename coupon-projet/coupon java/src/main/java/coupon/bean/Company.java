package coupon.bean;

public class Company {

	private long id;
	private String companyName;
	private String contactePhone;
	private User user;
	// ----------------------CONSTRUCTOR -------------------------

	public Company(long id, String companyName, String contactePhone, User user) {
		this(companyName, contactePhone, user);
		this.id = id;

	}

	public Company(String companyName, String contactePhone, User user) {
		this.companyName = companyName;
		this.contactePhone = contactePhone;
		this.user = user;
	}

	public Company(String companyName, String contactePhone) {
		super();
		this.companyName = companyName;
		this.contactePhone = contactePhone;
	}

	public Company() {
	}

	// ----------------------GETTER ANS SETTERS -------------------------

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String name) {
		this.companyName = name;
	}

	public String getContactePhone() {
		return contactePhone;
	}

	public void setContactePhone(String contactePhone) {
		this.contactePhone = contactePhone;
	}

	// ---------------------- METHODE -------------------------

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", contactePhone=" + contactePhone
				+ ", user=" + user + "]";
	}

}