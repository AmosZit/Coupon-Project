package coupon.bean;

public class UserDetails {

	private String password;
	private String email;
	private Long id;

	public UserDetails() {
		super();
	}

	public UserDetails(String password, String email, Long id) {
		super();
		this.password = password;
		this.email = email;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserDetails [ id=" + id + ", email=" + email + ", password=" + password + "]";
	}

}
