package coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import coupon.bean.Purchase;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.utils.DateUtils;
import coupon.utils.JdbcUtils;

@Repository
public class PurchaseDao {

	public Long creatCouponPurchase(Purchase purchase) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = " INSERT INTO purchases (customer_id , coupon_id , purchase_amount)"
					+ "VALUES (?,?,?) ";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, purchase.getCustomerId());
			preparedStatement.setLong(2, purchase.getCouponId());
			preparedStatement.setLong(3, purchase.getAmounts());

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
					DateUtils.getCurrentDateAndTime() + "failed  Create coupons id");

		} catch (SQLException e) {
			e.printStackTrace();
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " Create purchases failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

	public void deletePurchaseCustomer(long CustomerId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "DELETE FROM purchases WHERE customer_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, CustomerId);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " DELETE purchase failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void deletePurchaseCoupon(long couponId) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "DELETE FROM purchases WHERE coupon_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, couponId);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " DELETE purchase failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	
	public ArrayList<Purchase> purchaseByCustomer(long customerId) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Purchase> allListByCustomers = new ArrayList<Purchase>();

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM purchases WHERE customer_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				// give all the values from resultSet
				allListByCustomers.add(extractPurchaseFromResultSet(resultSet));

			}

			return allListByCustomers;

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the customer ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

//----------------------------------
	public void deleteCouponPurchaseByCompagnie(long compagnyId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "delete from purchases where coupon_id in (select coupon_id from coupons where company_id = ?)";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, compagnyId);

			// Executing the update
			preparedStatement.executeUpdate();

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "DELETE purchases failed ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void updatePurchase(Purchase purchase) throws ApplicationException {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String query = "UPDATE purchases SET purchase_amount=?  WHERE customer_id=? and coupon_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(query);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(2, purchase.getCustomerId());
			preparedStatement.setLong(3, purchase.getCouponId());
			preparedStatement.setLong(1, purchase.getAmounts());

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + " UPDATE purchase failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

	public int getAmontPurchase(Purchase purchase) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM purchases WHERE customer_id=? AND coupon_id =? ";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, purchase.getCustomerId());
			preparedStatement.setLong(2, purchase.getCouponId());

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("purchase_amount");
			}
			return id;
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the purchase ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public boolean isPurchaseExsist(Purchase purchase) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM purchases WHERE customer_id=? AND coupon_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, purchase.getCustomerId());
			preparedStatement.setLong(2, purchase.getCouponId());

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
					DateUtils.getCurrentDateAndTime() + "I can not find the purchases ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public void buyCouponPurchase(Purchase purchase) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Update the SQL query
			String sqlStatement = "UPDATE purchases SET  purchase_amount=? WHERE customer_id=? AND coupon_id =? ";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, purchase.getAmounts());
			preparedStatement.setLong(3, purchase.getCustomerId());
			preparedStatement.setLong(2, purchase.getCouponId());

			// Executing the update
			preparedStatement.executeUpdate();

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + "UPDATE  purchase  failed ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void deleteCouponPurchaseByDate(String endDate) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "delete from purchases where coupon_id in (select coupon_id from coupons where coupon_end_date <?)";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, endDate);

			// Executing the update
			preparedStatement.executeUpdate();

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "DELETE purchases failed ");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	private Purchase extractPurchaseFromResultSet(ResultSet result) throws Exception, SQLException {
		Purchase purchase = new Purchase();
        CouponDao couponDao = new CouponDao(); 
        
		purchase.setCouponId(result.getLong("coupon_id"));
		purchase.setCustomerId(result.getLong("customer_id"));
		purchase.setAmounts(result.getInt("purchase_amount"));
		purchase.setCoupon(couponDao.getOneCoupon(purchase.getCouponId()));
		
		return purchase;

	}

}
