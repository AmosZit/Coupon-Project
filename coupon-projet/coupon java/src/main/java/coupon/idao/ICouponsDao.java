package coupon.idao;

import java.util.ArrayList;

import coupon.bean.Coupon;
import coupon.enums.Category;
import coupon.exeption.ApplicationException;

public interface ICouponsDao {

	Long creatCoupon(Coupon coupon) throws ApplicationException;

	void updateCoupon(Coupon coupon) throws ApplicationException;

	void deleteCoupon(long CouponId) throws ApplicationException;

	ArrayList<Coupon> getAllCoupon() throws Exception, ApplicationException;

	Coupon getOneCoupon(long couponId) throws Exception, ApplicationException;

	void deleteCouponByCompagny(long compagnyID) throws ApplicationException;

	boolean isCouponExistsByCompagnie(String title, long compagnieID) throws ApplicationException;

	ArrayList<Coupon> getCouponIdByCompagnie(long customerId) throws Exception, ApplicationException;

	ArrayList<Coupon> getCouponByCategory(Category categorie) throws Exception, ApplicationException;

	ArrayList<Coupon> getCouponByCategoryAndCompagny(Category categorie, long compagnyId)
			throws Exception, ApplicationException;

	void buyCoupon(long couponId, int amount) throws ApplicationException;

	int getAmontCoupon(long couponId) throws ApplicationException;

	void deleteCouponByDate(String endDate) throws ApplicationException;

	boolean isCouponExsistById(long couponId) throws ApplicationException;
}