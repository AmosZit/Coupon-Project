 package coupon.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import coupon.bean.Company;
import coupon.bean.User;
import coupon.dao.CompagnyDao;
import coupon.dao.CouponDao;
import coupon.dao.PurchaseDao;
import coupon.dao.UsersDao;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.utils.DateUtils;
import coupon.utils.VerificationUtils;

@Controller
public class CompanyController {

	@Autowired
	private CompagnyDao compagnyDao;
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private PurchaseDao purchaseDao;

	public CompanyController() {
		super();
	}

	public void creatCompagny(Company compagny) throws ApplicationException {
		if (compagnyDao.isCompanyNameExists(compagny.getCompanyName())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The name  you chose is already exist");
		}
		if (compagnyDao.isCompanyPhoneExists(compagny.getContactePhone())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The phone you chose is already exist");
		}
		if (!VerificationUtils.isValidNumberPhone(compagny.getContactePhone())) {
			throw new ApplicationException(ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + " The Contacte Phone you've entered is invalid.");
		}

		compagnyDao.CreatCompagny(compagny);
	}

	public void updateCompagny(Company compagny) throws ApplicationException {

		if (!compagnyDao.isCompanyNameExists(compagny.getCompanyName())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The compagny don't exist");
		}
		if (compagnyDao.isCompanyPhoneExists(compagny.getContactePhone())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The phone you chose is already exist");
		}
		if (!VerificationUtils.isValidNumberPhone(compagny.getContactePhone())) {
			throw new ApplicationException(ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + "  Phone number is not valide .");
		}

		compagnyDao.updateCompany(compagny);
	}

	public void deleteCompagny(long compagnyId) throws Exception, ApplicationException {

		
			purchaseDao.deleteCouponPurchaseByCompagnie(compagnyId);
			couponDao.deleteCouponByCompagny(compagnyId);
			usersDao.deleteUserByCompagny(compagnyId);
			compagnyDao.deleteCompanyById(compagnyId);
		}
	

	public ArrayList<Company> getAllCompagny() throws ApplicationException, Exception {
		return compagnyDao.getAllCompany();
	}

	public Company getOneCompagnyById(long compagnyId) throws ApplicationException, Exception {
		return compagnyDao.getOneCompanyById(compagnyId);

	}

	public ArrayList<User> getAllUserByCompagny(long compagnyId) throws ApplicationException, Exception {
		if (compagnyDao.isCompanyExsistById(compagnyId)) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The compagny don't exist");
		}
		return UsersDao.getAllUserByCompagny(compagnyId);

	}

}
