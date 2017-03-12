package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//contains all possible values for additional select/dropdown fields

public class SelectField extends InformationField {

	private String[] values;

	public String[] getValues() {
		return this.values;
	}

	public void addValues(String[] values) {		
		for (int i = 0; i < values.length; i++) {
			insertSetting("option", values[i]);
		}
	}

	/**
	 * 
	 * @param question
	 *            - inherited from Information
	 * @param link
	 *            - all possible values
	 */
	public SelectField(String link, String description, Boolean isRequired, String[] options) {
		super(link, description, isRequired);

		if (!description.equals("")) {
			this.values = options;

			updateType("select");

			for (int i = 0; i < options.length; i++) {
				insertSetting("option", options[i]);
			}
		}
	}
	
	public SelectField(int id) {
		super(id);
		
		PreparedStatement preparedStmt = DBConnect.getPreparedStatement(
				"SELECT `value` FROM `informationsettings` WHERE `additionalinformation_id` = ? AND `setting` = ?");
		try {

			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, "option");
			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();
			
			List<String> allOptions = new ArrayList<String>();
			while (resultSet.next()) {
				allOptions.add(resultSet.getString("value"));
			}

			String[] allValues= new String[allOptions.size()];
			for (int i = 0; i < allOptions.size(); i++) {
				allValues[i]=allOptions.get(i);
			}

			values=allValues;

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

}