package coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import coupon.bean.User;
import coupon.enums.ClientType;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.utils.DateUtils;
import coupon.utils.JdbcUtils;

@Repository
public class UsersDao {

	public Long createUser(User user) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CompanyID is defined as a primary key and auto incremented

			String sqlStatement = null;

			// 2 types of users, we insert the companyId parameter for a "company" type user
			if (user.getCompanyId() == null) {
				sqlStatement = "INSERT INTO users (user_email, user_password, type) VALUES(?,?,?)";
				// Combining between the syntax and our connection
				preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

				// Replacing the question marks in the statement above with the relevant data
				preparedStatement.setString(1, user.getEmail());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getType().name());

			} else {
				sqlStatement = "INSERT INTO users (user_email, user_password, type, company_id) VALUES(?,?,?,?)";
				// Combining between the syntax and our connection
				preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

				// Replacing the question marks in the statement above with the relevant data
				preparedStatement.setString(1, user.getEmail());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getType().name());
				preparedStatement.setLong(4, user.getCompanyId());

			}

			// Executing the update
			preparedStatement.executeUpdate();

			// Executing the query and saving the DB response in the resultSet.
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long id = resultSet.getLong(1);
				return id;
			}
			resultSet.close();
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Failed to create user id");

		} catch (SQLException e) {
			e.printStackTrace();
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Create user failed");
		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void updateUser(User user) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
System.out.println(user);
		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Update the SQL query
			String sqlStatement = "UPDATE users SET user_password=? ,type=?  WHERE user_email=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing question marks using CustomerID as a cue point to change values
			// already present
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getType().name());
			System.out.println("you have update");

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
			throw new ApplicationException(e, ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + " UPDATE users failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public ClientType login(String email, String password) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM users WHERE user_email = ? and user_password = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			// Login had failed
			if (!resultSet.next()) {
				throw new ApplicationException(ErrorType.LOGIN_FAILED, "Failed login");
			}

			ClientType clientType = ClientType.valueOf(resultSet.getString("type"));
			return clientType;
		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Get users has failed");
		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public void deleteUser(long userId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "DELETE FROM users WHERE user_id =?";
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, userId);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " DELETE user failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void deleteUserByCompagny(long companyId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "DELETE FROM users WHERE company_id =?";
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " DELETE user failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public long getUserId(User user) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long customerId = 0;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM users WHERE  user_email = ? and user_password = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				customerId = resultSet.getLong("user_id");
			}
			return customerId;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the users ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public User getOneUserId(long userId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM users WHERE user_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, userId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return user;
			}
			// give the values from resultSet
			user = getUser(resultSet);

			return user;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			System.out.println(e);
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the Users ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public User getOneUserCompagnyId(long compagnyId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM users WHERE company_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, compagnyId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return user;
			}
			// give the values from resultSet
			user = getUser(resultSet);

			return user;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the Users ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public static ArrayList<User> getAllUserByCompagny(long companyId) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<User> UserByCompagny = new ArrayList<User>();

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM users WHERE company_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				// give all the values from resultSet
				UserByCompagny.add(getUser(resultSet));

			}

			return UserByCompagny;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the users  ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public boolean isUserExistsByEmail(String email) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM users WHERE user_email = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, email);

			// Executing the query and saving the DB response in the resultSet.
			result = preparedStatement.executeQuery();

			if (!result.next()) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Failed to check if user exists by name");
		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, result);
		}

	}

	public boolean isUserExistsById(long userId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM users WHERE user_id = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, userId);

			// Executing the query and saving the DB response in the resultSet.
			result = preparedStatement.executeQuery();

			if (!result.next()) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Failed to check if user exists by name");
		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, result);
		}
	}

	public User getUserIdUser(String email, String password) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = new User();

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM users WHERE  user_email = ? and user_password = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user.setId(resultSet.getLong("user_id"));
				user.setCompanyId(resultSet.getLong("company_id"));
			}
			return user;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the users ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public static User getUser(ResultSet result) throws SQLException {
		User user = new User();
		user.setEmail(result.getString("user_email"));
		user.setPassword(result.getString("user_password"));
		user.setId(result.getLong("user_id"));
		user.setCompanyId(result.getLong("company_id"));
		user.setType(ClientType.valueOf(result.getString("type")));

	
		return user;

	}

}
