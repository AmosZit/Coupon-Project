package coupon.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import coupon.bean.Customer;
import coupon.bean.Purchase;
import coupon.bean.User;
import coupon.dao.CustomerDao;
import coupon.dao.PurchaseDao;
import coupon.dao.UsersDao;
import coupon.enums.ClientType;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.utils.DateUtils;
import coupon.utils.VerificationUtils;

@Controller
public class CustomerController {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private PurchaseDao purchaseDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private UsersController usersController ; 

	public CustomerController() {
		super();
	}

	public void creatCustomer(Customer customer) throws Exception, ApplicationException {

		User user2 = new User(customer.getUser().getEmail(), customer.getUser().getPassword(),
				ClientType.Customer);

		if (!VerificationUtils.isValidName(customer.getFirstName())) {
			throw new ApplicationException(ErrorType.NAME_IS_ALREADY_EXISTS,
					DateUtils.getCurrentDateAndTime() + " The First Name you chose is not valide ");
		}
		if (!VerificationUtils.isValidName(customer.getLastName())) {
			throw new ApplicationException(ErrorType.NAME_IS_ALREADY_EXISTS,
					DateUtils.getCurrentDateAndTime() + " The Last Name you chose is not valide ");
		}

		if (!customerDao.isCustomerExsistById(customer.getId())) {
			throw new ApplicationException(ErrorType.NAME_IS_ALREADY_EXISTS,
					DateUtils.getCurrentDateAndTime() + " this user already has an existing account");
		}
		if (!usersDao.isUserExistsByEmail(user2.getEmail()) && customer.getId() == 0) {
			usersController.creatUser(user2);
		}
		customer.setId(usersDao.getUserId(user2));
		customerDao.creatCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws ApplicationException {

		if (customerDao.isCustomerExsistById(customer.getId())) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " this customer don't Exsist");
		}
		if (!VerificationUtils.isValidName(customer.getFirstName())) {
			throw new ApplicationException(ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + " this first name is not valide .");
		}
		if (!VerificationUtils.isValidName(customer.getLastName())) {
			throw new ApplicationException(ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + "  this last name is not valide .");
		}
		customerDao.updateCustomer(customer);

	}

	public void deleteCustomer(long customerId) throws ApplicationException {

		if (customerDao.isCustomerExsistById(customerId)) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + " this customer don't Exsist");
		}
		purchaseDao.deletePurchaseCustomer(customerId);
		customerDao.deleteCustomer(customerId);
		usersDao.deleteUser(customerId);

	}

	public ArrayList<Customer> getAllCustomers() throws ApplicationException, Exception {
		return customerDao.getAllCustomer();
	}

	public Customer getOneCustomer(long customerId) throws ApplicationException, Exception {
		if (customerDao.isCustomerExsistById(customerId)) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + "this customer don't Exsist");
		}
		return customerDao.getOneCustomer(customerId);

	}

	public Customer getOneCustomerAndDetails(long customerId) throws ApplicationException, Exception {
		if (customerDao.isCustomerExsistById(customerId)) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "this customer don't Exsist");
		}
		return customerDao.getOneCustomerAndDetails(customerId);

	}



	public ArrayList<Purchase> getPurchaseByCustomer(long customerId) throws ApplicationException, Exception {
		if (customerDao.isCustomerExsistById(customerId)) {
			throw new ApplicationException(ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + "this customer don't Exsist");
		}
		return purchaseDao.purchaseByCustomer(customerId);

	}
}
