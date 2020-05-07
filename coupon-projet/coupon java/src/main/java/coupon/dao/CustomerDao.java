package coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import coupon.bean.Customer;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.idao.ICustomerDao;
import coupon.utils.DateUtils;
import coupon.utils.JdbcUtils;

@Repository
public class CustomerDao implements ICustomerDao {

	public void creatCustomer(Customer customer) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CustomersId is defined as a primary key and Foreign Key
			String sqlStatement = "INSERT INTO customers(customer_id , customer_first_name, customer_last_name) "
					+ "VALUES(?,?,?)";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customer.getId());
			preparedStatement.setString(2, customer.getFirstName());
			preparedStatement.setString(3, customer.getLastName());

			// Executing the update
			preparedStatement.executeUpdate();

			// Executing the query and saving the DB response in the resultSet.

		} catch (SQLException e) {
			e.printStackTrace();
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Create customer failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void updateCustomer(Customer customer) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// the SQL query
			String sqlStatement = "UPDATE customers SET customer_first_name=? , customer_last_name=?  WHERE customer_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setLong(3, customer.getId());


			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + " UPDATE customers failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void deleteCustomer(long customerId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// the SQL query
			String sqlStatement = "DELETE FROM customers WHERE customer_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " DELETE customers failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public ArrayList<Customer> getAllCustomer() throws Exception, ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Customer> AllCustomer = new ArrayList<Customer>();
		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM customers";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				// give all the values of customer from resultSet
				AllCustomer.add(extractCustomerFromResultSet(resultSet));
			}
			return AllCustomer;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {

			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find  customers ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public Customer getOneCustomer(long CustomerId) throws Exception, ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM customers WHERE customer_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, CustomerId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return customer;
			}
			// give the values of this customer from resultSet
			customer = extractCustomerFromResultSet(resultSet);
			return customer;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the customers ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public Customer getOneCustomerAndDetails(long CustomerId) throws Exception, ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;
		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM customers WHERE customer_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, CustomerId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return customer;
			}
			// give the values and details of this customer from resultSet
			customer = extractCustomerAndDetailsFromResultSet(resultSet);
			return customer;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the customers  ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public boolean isCustomerExsist(Customer customer) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM customers WHERE customer_first_name=? AND customer_last_name=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return true;
			}
			return false;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the customers ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public boolean isCustomerExsistById(long customerId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM customers WHERE customer_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);

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
					DateUtils.getCurrentDateAndTime() + "this customer don't Exsist");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	private Customer extractCustomerFromResultSet(ResultSet result) throws Exception {
		Customer customer = new Customer();
		customer.setId(result.getLong("customer_id"));
		customer.setFirstName(result.getString("customer_first_name"));
		customer.setLastName(result.getString("customer_last_name"));

		return customer;

	}

	private Customer extractCustomerAndDetailsFromResultSet(ResultSet result) throws Exception {
		Customer customer = new Customer();
		UsersDao user = new UsersDao();

		customer.setId(result.getLong("customer_id"));
		customer.setFirstName(result.getString("customer_first_name"));
		customer.setLastName(result.getString("customer_last_name"));
		customer.setUser(user.getOneUserId(customer.getId()));		
		
		return customer;

	}

}
