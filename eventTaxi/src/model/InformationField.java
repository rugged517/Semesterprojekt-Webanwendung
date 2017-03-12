package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//superclass for all additional information fields

public class InformationField {

	protected int id;
	private String eventLink;
	private String type;
	private String description; // description for the input field (checkbox,
								// date etc.)
	private boolean required = false; // is this information necessary to
										// subscribe to the event?

	public int getId() {
		return id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRequired() {
		return this.required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * Gets eventLink, description and required for every field
	 */
	public InformationField(String eventLink, String description, boolean required) {

		PreparedStatement preparedStmt = null;
		try {
			this.eventLink = eventLink;
			this.description = description;
			this.required = required;

			Connection connect = DBConnect.getConnection();

			preparedStmt = connect.prepareStatement(
					"INSERT INTO `additionalinformation` (`events_link`, `description`, `required`) VALUES (?,?,?);",
					Statement.RETURN_GENERATED_KEYS);

			preparedStmt.setString(1, eventLink);
			preparedStmt.setString(2, description);
			preparedStmt.setBoolean(3, required);

			preparedStmt.executeUpdate();

			// get auto generated location id
			ResultSet resultSet = preparedStmt.getGeneratedKeys();
			resultSet.next();
			this.id = resultSet.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public InformationField(int id) {
		this.id = id;
		PreparedStatement preparedStmt = null;

		try {
			// get data from DB
			// create the sql select preparedstatement
			preparedStmt = DBConnect.getPreparedStatement("SELECT * FROM `additionalinformation` WHERE `id` = ?");

			preparedStmt.setInt(1, id);

			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			// check if there is any data -> returns false if there are no
			// rows in the ResultSet
			if (resultSet.next()) {
				this.eventLink = resultSet.getString("events_link");
				this.description = resultSet.getString("description");
				this.required = resultSet.getBoolean("required");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Updates Type field in DB. Shows type of inputfield
	 * 
	 * @param type
	 *            - text, select, checkbox, date
	 */
	protected void updateType(String type) {
		// create the update preparedstatement
		PreparedStatement preparedStmt = DBConnect
				.getPreparedStatement("UPDATE `additionalinformation` SET `type` = ? WHERE  `id` = ?;");
		try {

			preparedStmt.setString(1, type);
			preparedStmt.setInt(2, id);

			// execute the preparedstatement
			preparedStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void insertSetting(String setting, Object value) {
		PreparedStatement preparedStmt = DBConnect.getPreparedStatement(
				"INSERT INTO `informationsettings` (`additionalinformation_id`,`setting`,`value`) VALUES (?,?,?);");
		try {

			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, setting);
			preparedStmt.setString(3, String.valueOf(value));

			// execute the preparedstatement
			preparedStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected String getSettingsOption(String setting) {
		PreparedStatement preparedStmt = DBConnect.getPreparedStatement(
				"SELECT `value` FROM `informationsettings` WHERE `additionalinformation_id` = ? AND `setting` = ?");
		String option = null;
		try {

			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, setting);

			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			resultSet.next();
			option = resultSet.getString("value");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return option;
	}

}