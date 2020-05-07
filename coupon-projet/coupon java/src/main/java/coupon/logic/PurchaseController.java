package coupon.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import coupon.bean.Purchase;
import coupon.dao.PurchaseDao;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.utils.DateUtils;

@Controller
public class PurchaseController {

	@Autowired
	private PurchaseDao purchaseDao;

	public PurchaseController() {
		super();
	}

	public void creatCouponPurchase(long couponId, long customerId, int amount) throws Exception, ApplicationException {
		if (amount < 0) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT,
					DateUtils.getCurrentDateAndTime() + " The amount you've entered is invalid");
		}
		Purchase purchase = new Purchase(couponId, customerId, amount);
		if (purchaseDao.isPurchaseExsist(purchase)) {
			purchase.setAmounts(purchaseDao.getAmontPurchase(purchase));
			purchase.setAmounts(purchase.getAmounts() + amount);
			purchaseDao.creatCouponPurchase(purchase);

		} else {
			purchase.setAmounts(purchaseDao.getAmontPurchase(purchase));
			purchase.setAmounts(purchase.getAmounts() + amount);
			purchaseDao.updatePurchase(purchase);
		}

	}
}