package coupon.utils;

import java.sql.*;

public class JdbcUtils {
	
	
	private static String USERNAME = "root";
	private static String PASSWORD = null;

	private final static String URL = "jdbc:mysql://localhost:3306/project";
	private final static String Driver = "com.mysql.jdbc.Driver";

	static {
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		closeResources(connection, preparedStatement);
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement1,
			PreparedStatement preparedStatement2) {
		closeResources(connection, preparedStatement1);
		try {
			if (preparedStatement2 != null) {
				preparedStatement2.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}