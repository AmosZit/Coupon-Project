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

import coupon.bean.Customer;
import coupon.bean.Purchase;
import coupon.exeption.ApplicationException;
import coupon.logic.CustomerController;

@RestController
@RequestMapping("/customer")
public class CustomerApi {

	@Autowired
	private CustomerController customerController;

	// http://localhost:8080/customer
	@PostMapping
	public void createCustomer(@RequestBody Customer customer) throws ApplicationException, Exception {
		System.out.println(customer);
		this.customerController.creatCustomer(customer);
	}

	// http://localhost:8080/customer
	@PutMapping
	public void updateCustomer(@RequestBody Customer customer) throws ApplicationException, Exception {
		System.out.println(customer);
		this.customerController.updateCustomer(customer);
	}

	// http://localhost:8080/customer/3
	@DeleteMapping("/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") long id) throws ApplicationException, Exception {
		this.customerController.deleteCustomer(id);

	}

	// http://localhost:8080/customer
	@GetMapping
	public ArrayList<Customer> getAllCustomer() throws ApplicationException, Exception {
		System.out.println(customerController.getAllCustomers());
		return this.customerController.getAllCustomers();
	}

	// http://localhost:8080/customer/4
	@GetMapping("/{customerId}")
	public Customer getOneCustomer(@PathVariable("customerId") long id) throws ApplicationException, Exception {
		System.out.println(customerController.getOneCustomer(id));
		return this.customerController.getOneCustomer(id);
	}

	// http://localhost:8080/customer/detailOfCustomer?id=10
	@GetMapping("/detailOfCustomer")
	public Customer detailOfOneCustomer(@RequestParam("id") long id)
			throws ApplicationException, Exception {
		System.out.println(customerController.getOneCustomerAndDetails(id));
		return this.customerController.getOneCustomerAndDetails(id);
	}

	// http://localhost:8080/customer/allCouponByCustomer?id=4
		@GetMapping("/allPurchaseByCustomer")
		public ArrayList<Purchase> getAllPurchaseOfCustomer(@RequestParam("id") long id)
				throws ApplicationException, Exception {
			System.out.println(customerController.getPurchaseByCustomer(id));
			return this.customerController.getPurchaseByCustomer(id);
		}

}
