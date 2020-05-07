package coupon.idao;

import java.util.ArrayList;

import coupon.bean.Company;
import coupon.exeption.ApplicationException;

public interface ICompanyDao {

	Long CreatCompagny(Company company) throws ApplicationException;

	void updateCompany(Company company) throws ApplicationException;

	ArrayList<Company> getAllCompany() throws Exception, ApplicationException;

	void deleteCompanyById(long compagnyId) throws ApplicationException;

	boolean isCompanyNameExists(String compagnieName) throws ApplicationException;

	Company getOneCompanyById(long compagnyId) throws Exception, ApplicationException;

	boolean isCompanyExsistById(long companyId) throws ApplicationException;

	boolean isCompanyPhoneExists(String compagniePhone) throws ApplicationException;

}
