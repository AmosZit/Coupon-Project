package coupon.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import coupon.bean.Coupon;
import coupon.dao.CompagnyDao;
import coupon.dao.CouponDao;
import coupon.dao.CustomerDao;
import coupon.dao.PurchaseDao;
import coupon.enums.Category;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.utils.DateUtils;

@Controller
public class CouponController {

	@Autowired
	private CouponDao couponDao;
	@Autowired
	private PurchaseDao purchaseDao;
	@Autowired
	private PurchaseController purchaseController;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CompagnyDao compagnyDao;

	public CouponController() {
		super();
	}

	public void creatCoupon(Coupon coupon) throws Exception, ApplicationException {

		if (compagnyDao.isCompanyExsistById(coupon.getCompagnyId())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The id of Compagny you've enter is invalid");
		}
		if (couponDao.isCouponExistsByCompagnie(coupon.getTitle(), coupon.getCompagnyId())) {
			throw new ApplicationException(ErrorType.NAME_IS_ALREADY_EXISTS,
					DateUtils.getCurrentDateAndTime() + " The name you chose is already exist on this compagny");
		}
		if (!DateUtils.isDate1BeforeDate2(coupon.getStartDate(), coupon.getEndDate())) {
			throw new ApplicationException(ErrorType.INVALID_DATES,
					DateUtils.getCurrentDateAndTime() + " The dates you've entered is invalid");
		}
		if (coupon.getAmount() < 0) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT,
					DateUtils.getCurrentDateAndTime() + " The amount you've entered  is invalid");
		}
		if (coupon.getPrice() < 0) {
			throw new ApplicationException(ErrorType.INVALID_PRICE,
					DateUtils.getCurrentDateAndTime() + " The price you've entered is invalid");
		}
		couponDao.creatCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) throws Exception, ApplicationException {

		if (couponDao.isCouponExsistById(coupon.getId())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The id of coupon you've enter is invalid");
		}

		if (!couponDao.isCouponExistsByCompagnie(coupon.getTitle(), coupon.getCompagnyId())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The compagny or the name of coupon  you've enter is invalid");

		}
		if (!DateUtils.isDate1BeforeDate2(coupon.getStartDate(), coupon.getEndDate())) {
			throw new ApplicationException(ErrorType.INVALID_DATES,
					DateUtils.getCurrentDateAndTime() + " The dates you've entered is invalid");
		}
		if (coupon.getAmount() < 0) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT,
					DateUtils.getCurrentDateAndTime() + " The amount you've entered  is invalid");
		}
		if (coupon.getPrice() < 0) {
			throw new ApplicationException(ErrorType.INVALID_PRICE,
					DateUtils.getCurrentDateAndTime() + " The price you've entered is invalid");
		}
		couponDao.updateCoupon(coupon);
	}

	public void deleteCoupon(long couponId) throws Exception, ApplicationException {

		if (couponDao.isCouponExsistById(couponId)) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The id of coupon you've enter is invalid");
		} else {
			purchaseDao.deletePurchaseCoupon(couponId);
			couponDao.deleteCoupon(couponId);
		}

	}

	public ArrayList<Coupon> getAllCoupon() throws ApplicationException, Exception {

		return couponDao.getAllCoupon();
	}

	public ArrayList<Coupon> getCouponByCategory(Category category) throws ApplicationException, Exception {

		return couponDao.getCouponByCategory(category);
	}

	public ArrayList<Coupon> getCouponByCategoryAndCompagny(Category category, long compagnyId)
			throws ApplicationException, Exception {

		return couponDao.getCouponByCategoryAndCompagny(category, compagnyId);
	}

	public Coupon getOneCoupon(long couponId) throws ApplicationException, Exception {

		if (!customerDao.isCustomerExsistById(couponId)) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The id of coupon  you've enter is invalid");
		} else {
			return couponDao.getOneCoupon(couponId);
		}
	}

	public ArrayList<Coupon> getCouponIdByCompagny(long compagnyId) throws ApplicationException, Exception {

		return couponDao.getCouponIdByCompagnie(compagnyId);
	}

	public void byCoupon(Coupon coupon, long customerId, int amount) throws ApplicationException, Exception {

		if (couponDao.isCouponExsistById(coupon.getId())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The ID of coupon  you've enter is invalid");
		}
		if (customerDao.isCustomerExsistById(customerId)) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The ID of customer  you've enter is invalid");
		}
		if (couponDao.getAmontCoupon(coupon.getId()) <= 0 && couponDao.getAmontCoupon(coupon.getId()) <= amount) {
			throw new ApplicationException(ErrorType.COUPON_IS_OUT_OF_ORDER,
					DateUtils.getCurrentDateAndTime() + " Coupon is out of order");
		}
		coupon.setAmount(couponDao.getAmontCoupon(coupon.getId()));
		coupon.setAmount(coupon.getAmount() - amount);
		purchaseController.creatCouponPurchase(coupon.getId(), customerId, amount);
		couponDao.buyCoupon(coupon.getId(), coupon.getAmount());

	}
}
