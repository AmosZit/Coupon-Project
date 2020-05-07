package coupon.idao;

import java.util.ArrayList;

import coupon.bean.Customer;
import coupon.exeption.ApplicationException;

public interface ICustomerDao {

	void creatCustomer(Customer customer) throws ApplicationException;

	Customer getOneCustomer(long customerID) throws Exception, ApplicationException;

	Customer getOneCustomerAndDetails(long CustomerId) throws Exception, ApplicationException;

	void updateCustomer(Customer customer) throws ApplicationException;

	void deleteCustomer(long customerID) throws ApplicationException;

	ArrayList<Customer> getAllCustomer() throws Exception, ApplicationException;

	boolean isCustomerExsistById(long customerId) throws ApplicationException;

	boolean isCustomerExsist(Customer customer) throws ApplicationException;

}