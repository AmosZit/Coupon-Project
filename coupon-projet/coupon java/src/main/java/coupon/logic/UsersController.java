package coupon.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import coupon.bean.User;
import coupon.bean.UserDataClient;
import coupon.bean.UserDataMap;
import coupon.dao.CompagnyDao;
import coupon.dao.CustomerDao;
import coupon.dao.UsersDao;
import coupon.enums.ClientType;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.idao.ICacheManager;
import coupon.utils.DateUtils;
import coupon.utils.VerificationUtils;

@Controller
public class UsersController {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CompagnyDao compagnyDao;
	@Autowired
	private ICacheManager cacheManager;

	public UsersController() {
		super();
	}

	public Long creatUser(User user) throws ApplicationException {

		if (user == null) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The user is null Please try again.");
		}
		if ((user.getCompanyId() != null) && compagnyDao.isCompanyExsistById(user.getCompanyId())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " The compagny is not exist  Please try again.");
		}
		if (usersDao.isUserExistsByEmail(user.getEmail())) {
			throw new ApplicationException(ErrorType.NAME_IS_ALREADY_EXISTS,
					DateUtils.getCurrentDateAndTime() + "User name already exist");
		}
		if (!VerificationUtils.isValidPassword(user.getPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PASSWORD,
					DateUtils.getCurrentDateAndTime() + " The password you've entered is invalid. Please try again.");
		}
		if (!VerificationUtils.validateEmail(user.getEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL,
					DateUtils.getCurrentDateAndTime() + " The email you've entered is invalid");
		}
		return usersDao.createUser(user);
	}

	public UserDataClient login(User user) throws ApplicationException {
		ClientType clientType = usersDao.login(user.getEmail(), user.getPassword());
		int token = generateEncryptedToken(user.getEmail());
		User user1 = usersDao.getUserIdUser(user.getEmail(),user.getPassword());
		UserDataMap userDataMap = new UserDataMap(user1.getId(), user1.getCompanyId(), user1.getType());
		cacheManager.put(token, userDataMap);
		UserDataClient userDataClient = new UserDataClient(token, clientType,user1.getId() , user1.getCompanyId());
		System.out.println(userDataClient.toString());
		return userDataClient;
	}


	private int generateEncryptedToken(String user) {
		String token = "Salt - junk data" + user + "Sheker kolshehu";
		return token.hashCode();
	}

	public void updateUser(User user) throws ApplicationException {

		if (!usersDao.isUserExistsByEmail(user.getEmail())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " this user don't Exsist");
		}
		if (!VerificationUtils.isValidPassword(user.getPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PASSWORD,
					DateUtils.getCurrentDateAndTime() + " The password you've entered is invalid. Please try again.");
		}
		if (!VerificationUtils.validateEmail(user.getEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL,
					DateUtils.getCurrentDateAndTime() + " The email you've entered is invalid");
		}
		usersDao.updateUser(user);
	}

	public void deleteUser(long userId) throws ApplicationException {

		if (!usersDao.isUserExistsById(userId)) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " this user don't Exsist");
		}
		if (usersDao.isUserExistsById(userId) && !customerDao.isCustomerExsistById(userId)) {
			customerDao.deleteCustomer(userId);
			usersDao.deleteUser(userId);
		} else {
			usersDao.deleteUser(userId);
		}
	}

	public User getOneUserById(long userId) throws ApplicationException {
		if (!usersDao.isUserExistsById(userId)) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " this user don't Exsist");
		}
		return usersDao.getOneUserId(userId);

	}

}