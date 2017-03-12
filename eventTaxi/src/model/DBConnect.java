package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBConnect {

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//TODO check if it works on final server
		return DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("catalina.base")+"/dbs/eventtaxi.sqlite");
	}

	public static PreparedStatement getPreparedStatement(String query) {

		Connection connect = null;
		try {
			connect = DBConnect.getConnection();
			// create the mysql preparedstatement
			return connect.prepareStatement(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * saves changes of boolean value
	 * 
	 * @param table
	 * @param parameter
	 * @param value
	 * @param id
	 * @param idValue
	 * 
	 * @author Florian
	 */
	public static boolean updateBoolean(String table, String parameter, boolean value, String id, String idValue) {

		PreparedStatement preparedStmt = getUpdateStatement(table, parameter, id, idValue);
		boolean succses = false;

		try {
			preparedStmt.setBoolean(1, value);
			succses = (preparedStmt.executeUpdate() > 0);
		} catch (SQLException e) {
			e.printStackTrace();

		} // Close the connection and release the resources used.
		try {
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return succses;

	}

	/**
	 * saves changes of String value
	 * 
	 * @param table
	 * @param parameter
	 * @param value
	 * @param id
	 * @param idValue
	 * 
	 * @author Florian
	 */
	public static boolean updateString(String table, String parameter, String value, String id, String idValue) {

		PreparedStatement preparedStmt = getUpdateStatement(table, parameter, id, idValue);
		boolean succses = false;

		try {
			preparedStmt.setString(1, value);
			succses = (preparedStmt.executeUpdate() > 0);
		} catch (SQLException e) {
			e.printStackTrace();

		} // Close the connection and release the resources used.
		try {
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return succses;

	}

	/**
	 * generates update query string and inserts idValue
	 * 
	 * @param table
	 * @param parameter
	 * @param value
	 * @author Florian
	 */
	public static PreparedStatement getUpdateStatement(String table, String parameter, String id, String idValue) {
		String query = "UPDATE `" + table + "` SET `" + parameter + "` = ? WHERE `" + id + "` = ?;";

		PreparedStatement preparedStmt = getPreparedStatement(query);
		try {
			preparedStmt.setString(2, idValue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStmt;
	}
	
}
