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

import coupon.bean.Company;
import coupon.bean.User;
import coupon.exeption.ApplicationException;
import coupon.logic.CompanyController;

@RestController
@RequestMapping("/company")
public class CompanyApi {

	@Autowired
	private CompanyController companyController;

	// http://localhost:8080/company
	@PostMapping
	public void createCompany(@RequestBody Company company) throws ApplicationException {
		System.out.println(company);
		this.companyController.creatCompagny(company);
	}

	// http://localhost:8080/company
	@PutMapping
	public void updateCompany(@RequestBody Company company) throws ApplicationException {
		this.companyController.updateCompagny(company);
	}

	// http://localhost:8080/company/3
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") long id) throws Exception, ApplicationException {
		this.companyController.deleteCompagny(id);
	}

	// http://localhost:8080/company
	@GetMapping
	public ArrayList<Company> getAllCompany() throws ApplicationException, Exception {
		System.out.println(companyController.getAllCompagny());
		return this.companyController.getAllCompagny();
	}

	// http://localhost:8080/company/id?id=2
	@GetMapping("/id")
	public Company getCompanyById(@RequestParam("id") long company) throws ApplicationException, Exception {
		System.out.println(this.companyController.getOneCompagnyById(company));
		return this.companyController.getOneCompagnyById(company);
	}

	// http://localhost:8080/company/1
	@GetMapping("/{companyId}")
	public ArrayList<User> getAllUuserByCompany(@PathVariable("companyId") long companyId)
			throws ApplicationException, Exception {
		System.out.println(companyController.getAllUserByCompagny(companyId));
		return this.companyController.getAllUserByCompagny(companyId);
	}
}
