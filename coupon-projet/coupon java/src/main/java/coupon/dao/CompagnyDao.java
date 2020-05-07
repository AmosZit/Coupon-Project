
package coupon.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import coupon.bean.Company;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.idao.ICompanyDao;
import coupon.utils.DateUtils;
import coupon.utils.JdbcUtils;

@Repository
public class CompagnyDao implements ICompanyDao {

	public Long CreatCompagny(Company company) throws ApplicationException {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "INSERT INTO company(company_name, company_phone) " + "VALUES(?,?)";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getContactePhone());

			// Executing the update
			preparedStatement.execute();

			// Executing the query and saving the DB response in the resultSet.
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long id = resultSet.getLong(1);
				return id;

			}
			resultSet.close();
			throw new ApplicationException(ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "failed  Create company id");

		} catch (SQLException e) {
			e.printStackTrace();
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Create Company failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

	public void updateCompany(Company company) throws ApplicationException {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String query = "UPDATE company SET company_phone=?  WHERE company_name=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(query);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(2, company.getCompanyName());
			preparedStatement.setString(1, company.getContactePhone());

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + " UPDATE Company failed ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

	public ArrayList<Company> getAllCompany() throws Exception, ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Company> allCompanies = new ArrayList<Company>();
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM company";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				// give all the values of customer from resultSet
				allCompanies.add(extractCompanyFromResultSet(resultSet));
			}

			return allCompanies;

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find all the company");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public Company getOneCompanyById(long compagnyId) throws Exception, ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM company WHERE company_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, compagnyId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return company;
			}
			// give the values of this customer from resultSet
			company = extractCompanyFromResultSet(resultSet);
			return company;

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the company");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public boolean isCompanyExsistById(long companyId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM company WHERE company_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Company Exsist By id don't work");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public boolean isCompanyNameExists(String compagnieName) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM company WHERE company_name =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, compagnieName);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Company Exsist By compagnie name don't work");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return false;
	}

	public boolean isCompanyPhoneExists(String compagniePhone) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM company WHERE company_phone =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, compagniePhone);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Company Exsist By compagnie name don't work");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return false;
	}

	public void deleteCompanyById(long compagnyId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "DELETE FROM company WHERE company_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, compagnyId);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "DELETE company failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	private Company extractCompanyFromResultSet(ResultSet result) throws Exception, SQLException {
		Company company = new Company();
		company.setId(result.getLong("company_id"));
		company.setCompanyName(result.getString("company_name"));
		company.setContactePhone(result.getString("company_phone"));

		return company;

	}

	

}
