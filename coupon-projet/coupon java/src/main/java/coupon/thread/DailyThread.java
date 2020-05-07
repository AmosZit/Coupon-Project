package coupon.thread;

import java.util.TimerTask;

import coupon.dao.CouponDao;
import coupon.dao.PurchaseDao;
import coupon.exeption.ApplicationException;
import coupon.utils.DateUtils;

public class DailyThread extends TimerTask {
	private CouponDao couponDao = new CouponDao();
	private PurchaseDao purchaseDao = new PurchaseDao();

	@Override
	public void run() {

		try {
			purchaseDao.deleteCouponPurchaseByDate(DateUtils.getCurrentDate());
			couponDao.deleteCouponByDate(DateUtils.getCurrentDate());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

}