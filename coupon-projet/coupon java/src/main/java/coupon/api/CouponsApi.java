package coupon.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coupon.bean.Coupon;
import coupon.enums.Category;
import coupon.exeption.ApplicationException;
import coupon.logic.CouponController;

@RestController
@RequestMapping("/coupons")
public class CouponsApi {

	@Autowired
	private CouponController couponsController;

	// http://localhost:8080/coupons
	@PostMapping
	public void createCoupon(@RequestBody Coupon coupon) throws ApplicationException, Exception {
		System.out.println(coupon.toString());
		this.couponsController.creatCoupon(coupon);
	}

	// http://localhost:8080/coupons
	@PutMapping
	public void updateCoupon(@RequestBody Coupon coupon) throws ApplicationException, Exception {
		this.couponsController.updateCoupon(coupon);
	}

	// http://localhost:8080/coupons/3
	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long id) throws ApplicationException, Exception {
		this.couponsController.deleteCoupon(id);

	}

	// http://localhost:8080/coupons/4
	@GetMapping("/{couponId}")
	public Coupon getOneCoupon(@PathVariable("couponId") long id) throws ApplicationException, Exception {
		System.out.println(couponsController.getOneCoupon(id));
		return this.couponsController.getOneCoupon(id);
	}

	// http://localhost:8080/coupons
	@GetMapping
	public ArrayList<Coupon> getAllCoupon() throws ApplicationException, Exception {
		System.out.println(couponsController.getAllCoupon());
		return this.couponsController.getAllCoupon();
	}

	// http://localhost:8080/coupons/category?type=ELECTRICITY
	@GetMapping("/category")
	public ArrayList<Coupon> getCouponByCategory(@RequestParam("type") Category category)
			throws ApplicationException, Exception {
		System.out.println(couponsController.getCouponByCategory(category));
		return this.couponsController.getCouponByCategory(category);

	}

	// http://localhost:8080/coupons/categoryAndCompanyId?type=ELECTRICITY&companyId=1
	@GetMapping("/categoryAndCompanyId")
	public ArrayList<Coupon> getCouponByCategoryAndCompagny(@RequestParam("type") Category category,
			@RequestParam("companyId") long id) throws ApplicationException, Exception {
		System.out.println(couponsController.getCouponByCategoryAndCompagny(category, id));
		return this.couponsController.getCouponByCategoryAndCompagny(category, id);
	}

	// http://localhost:8080/coupons/couponByCompany?companyId=4
	@GetMapping("/couponByCompany")
	public ArrayList<Coupon> getCouponByCompany(@RequestParam("companyId") long companyId)
			throws ApplicationException, Exception {
		System.out.println(couponsController.getCouponIdByCompagny(companyId));
		return this.couponsController.getCouponIdByCompagny(companyId);

	}

	// http://localhost:8080/coupons/byCoupon?customer=6&amount=20
	@PutMapping("/byCoupon")
	public void byCoupon(@RequestBody Coupon coupon, @RequestParam("customer") long customerId,
			@RequestParam("amount") int amount) throws ApplicationException, Exception {
		this.couponsController.byCoupon(coupon, customerId, amount);

	}

}
