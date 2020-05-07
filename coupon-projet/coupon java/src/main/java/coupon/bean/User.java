package coupon.bean;

import coupon.enums.ClientType;

public class User {

	private long id;
	private String password;
	private String email;
	private ClientType type;
	private Long companyId;

	public User(long id, String email, String password, Long companyId, ClientType type) {
		this(email, password, type, companyId);
		this.id = id;
	}

	public User(String email, String password, ClientType type, Long companyId) {
		this(email, password, type);
		this.companyId = companyId;

	}

	public User(String email, String password, ClientType type) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
		this.companyId = null;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.companyId = null;
	}

	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;

	}

	public ClientType getType() {
		return type;
	}

	public void setType(ClientType type) {
		this.type = type;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", type=" + type + ", companyId="
				+ companyId + "]";
	}

}
