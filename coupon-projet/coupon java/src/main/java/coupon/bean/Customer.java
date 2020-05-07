package coupon.bean;

public class Customer {

	private long id;
	private String firstName;
	private String lastName;
	private User user;

	// ----------------------CONSTRUCTOR -------------------------

	public Customer(long id, String firstName, String lastName, User user) {
		this(firstName, lastName, user);
		this.id = id;

	}

	public Customer(String firstName, String lastName, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.user = user;

	}

	public Customer(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer(long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer() {
		super();
	}

	// ----------------------GETTER ANS SETTERS -------------------------

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

	}

	// ---------------------- METHODE -------------------------

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", user="
				+ user + "]";
	}
}
