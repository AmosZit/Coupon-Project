package coupon.bean;

import coupon.enums.Category;

public class Coupon {

	private long id;
	private long compagnyId;
	private Category category;
	private String title;
	private String description;
	private String startDate;
	private String endDate;
	private int amount;
	private double price;
	private String image;

	// ----------------------CONSTRUCTOR -------------------------

	public Coupon(long id, long compagnyId, Category category, String title, String description, String startDate,
			String endDate, int amount, double price, String image) {
		super();
		this.id = id;
		this.compagnyId = compagnyId;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon(long compagnyId, Category category, String title, String description, String startDate,
			String endDate, int amount, double price, String image) {
		super();
		this.compagnyId = compagnyId;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon() {
		super();
	}

	// ----------------------GETTER ANS SETTERS -------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompagnyId() {
		return compagnyId;
	}

	public void setCompanyId(long companyId) throws Exception {
		this.compagnyId = companyId;

	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String debutDatt) throws Exception {
		this.startDate = debutDatt;

	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDatt) throws Exception {
		this.endDate = endDatt;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// ----------------------METHODE -------------------------
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", compagnyId=" + compagnyId + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

}
