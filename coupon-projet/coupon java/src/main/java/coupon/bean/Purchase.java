package coupon.bean;

public class Purchase {

	private long couponId;
	private long customerId;
	private int amounts;
	private Coupon coupon ; 

	
	
	public Purchase(long couponId, long customerId, int amount) {
		super();
		this.couponId = couponId;
		this.customerId = customerId;
		this.amounts = amount;
	}

	public Purchase(long couponId, long customerId) {
		this.couponId = couponId;
		this.customerId = customerId;
	}

	public Purchase() {
	}
	
	
	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public int getAmounts() {
		return amounts;
	}

	public void setAmounts(int amount) {
		this.amounts = amount;
	}

	@Override
	public String toString() {
		return "Purchase [couponId=" + couponId + ", customerId=" + customerId + ", amounts=" + amounts + ", coupon="
				+ coupon + "]";
	}

	

}
