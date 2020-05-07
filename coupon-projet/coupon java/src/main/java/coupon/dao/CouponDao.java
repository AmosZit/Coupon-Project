package coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import coupon.bean.Coupon;
import coupon.enums.Category;
import coupon.enums.ErrorType;
import coupon.exeption.ApplicationException;
import coupon.idao.ICouponsDao;
import coupon.utils.DateUtils;
import coupon.utils.JdbcUtils;

@Repository
public class CouponDao implements ICouponsDao {

	public Long creatCoupon(Coupon coupon) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "INSERT INTO coupons(coupon_title ,coupon_description , coupon_start_date , coupon_end_date, coupon_amount, coupon_price, coupon_image , company_id , category_id ) "
					+ "VALUES (?,?,?,?,?,?,?,?,?) ";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, coupon.getTitle());
			preparedStatement.setString(2, coupon.getDescription());
			preparedStatement.setString(3, coupon.getStartDate());
			preparedStatement.setString(4, coupon.getEndDate());
			preparedStatement.setInt(5, coupon.getAmount());
			preparedStatement.setDouble(6, coupon.getPrice());
			preparedStatement.setString(7, coupon.getImage());
			preparedStatement.setLong(8, coupon.getCompagnyId());
			preparedStatement.setLong(9, coupon.getCategory().getValue());

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
					DateUtils.getCurrentDateAndTime() + " Create coupons failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

	public void updateCoupon(Coupon coupon) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Update the SQL query
			String sqlStatement = "UPDATE coupons SET coupon_description=?, coupon_start_date=?,coupon_end_date=?, coupon_amount=?, coupon_price=?, coupon_image=?,  category_id=? ,  coupon_title =? "
					+ "WHERE company_id=? And coupon_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, coupon.getDescription());
			preparedStatement.setString(2, coupon.getStartDate());
			preparedStatement.setString(3, coupon.getEndDate());
			preparedStatement.setInt(4, coupon.getAmount());
			preparedStatement.setDouble(5, coupon.getPrice());
			preparedStatement.setString(6, coupon.getImage());
			preparedStatement.setLong(7, coupon.getCategory().getValue());
			preparedStatement.setString(8, coupon.getTitle());
			preparedStatement.setLong(9, coupon.getCompagnyId());
			preparedStatement.setLong(10, coupon.getId());

			// Executing the update
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + " UPDATE coupons failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

	public void deleteCoupon(long CouponId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "DELETE FROM coupons WHERE coupon_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, CouponId);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " DELETE coupons failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

	public void deleteCouponByCompagny(long compagnyId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "DELETE FROM coupons WHERE company_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, compagnyId);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " DELETE coupons  failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

	public ArrayList<Coupon> getAllCoupon() throws Exception, ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Coupon> AllCoupons = new ArrayList<Coupon>();
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				// give all the values of customer from resultSet
				AllCoupons.add(extractCompanyFromResultSet(resultSet));
			}
			return AllCoupons;

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find all the coupons");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public Coupon getOneCoupon(long couponId) throws Exception, ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE coupon_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// give all the values of a coupon according to his id
			preparedStatement.setLong(1, couponId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return coupon;
			}
			// give the values of this coupon from resultSet
			coupon = extractCompanyFromResultSet(resultSet);
			return coupon;

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the coupons");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public boolean isCouponExistsByCompagnie(String title, long compagnieId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE company_id=? AND coupon_title=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, compagnieId);
			preparedStatement.setString(2, title);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the coupons");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public ArrayList<Coupon> getCouponIdByCompagnie(long compagnyId) throws Exception, ApplicationException {
		// Turn on the connections
		ArrayList<Coupon> AllCustomerCategorie = new ArrayList<Coupon>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE company_id=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, compagnyId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				long id = resultSet.getLong("coupon_id");

				// give all the values from resultSet
				AllCustomerCategorie.add(getOneCoupon(id));
			}
			return AllCustomerCategorie;

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the coupons");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public int getAmontCoupon(long couponId) throws ApplicationException {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;

		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE coupon_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// give all the values of a coupon according to his id
			preparedStatement.setLong(1, couponId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getInt("coupon_amount");
			}
			return id;
		} catch (SQLException e) {

			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find the coupons");
		} finally {

			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public void buyCoupon(long couponId, int amount) throws ApplicationException {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "UPDATE coupons SET coupon_amount=? WHERE coupon_id=? ";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setInt(1, amount);
			preparedStatement.setLong(2, couponId);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.FIELD_IS_IRREPLACEABLE,
					DateUtils.getCurrentDateAndTime() + " I can not find the coupons");

		} finally {
			// Closing the resources

			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public ArrayList<Coupon> getCouponByCategory(Category categorie) throws Exception, ApplicationException {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> AllCompagnyCategorie = new ArrayList<Coupon>();
		long couponId;
		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE category_id=? ";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, categorie.getValue());

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				couponId = resultSet.getLong("coupon_id");

				// give all the values from resultSet
				AllCompagnyCategorie.add(getOneCoupon(couponId));
			}

		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find this categorie ");

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return AllCompagnyCategorie;
	}

	public ArrayList<Coupon> getCouponByCategoryAndCompagny(Category categorie, long compagnyId)
			throws Exception, ApplicationException {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> AllCompagnyCategorie = new ArrayList<Coupon>();
		long couponId;
		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE category_id=? and company_id=? ";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, categorie.getValue());
			preparedStatement.setLong(2, compagnyId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				couponId = resultSet.getLong("coupon_id");

				// give all the values from resultSet
				AllCompagnyCategorie.add(getOneCoupon(couponId));
			}

		} catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "I can not find this categorie ");

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return AllCompagnyCategorie;
	}

	public void deleteCouponByDate(String endDate) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "DELETE FROM coupons WHERE coupon_end_date < ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, endDate);
			System.out.println("the coupon is delete");

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + " DELETE coupons failed");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);

		}
	}

	public boolean isCouponExsistById(long couponId) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE coupon_id =?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, couponId);

			// Executing the query and saving the DB response in the resultSet.
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(e, ErrorType.INVALID_ID,
					DateUtils.getCurrentDateAndTime() + "this coupon don't Exsist");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	private Coupon extractCompanyFromResultSet(ResultSet result) throws Exception {
		Coupon coupon = new Coupon();
		coupon.setId(result.getLong("coupon_id"));
		coupon.setCompanyId(result.getLong("company_id"));
		coupon.setTitle(result.getString("coupon_title"));
		coupon.setDescription(result.getString("coupon_description"));
		coupon.setStartDate(result.getString("coupon_start_date"));
		coupon.setEndDate(result.getString("coupon_end_date"));
		coupon.setAmount(result.getInt("coupon_amount"));
		coupon.setPrice(result.getDouble("coupon_price"));
		coupon.setImage(result.getString("coupon_image"));
		coupon.setCategory(Category.valueOf(result.getLong("category_id")));
		return coupon;

	}

}