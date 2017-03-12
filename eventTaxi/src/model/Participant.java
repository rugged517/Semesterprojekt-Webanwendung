package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//user that can only subscribe to events

public class Participant extends User {

	protected Location location = null;
	protected int locationId;
	protected boolean[] checkboxValues = { false, false, false };
	protected boolean isOrganizer=false;

	public boolean isOrganizer() {
		return isOrganizer;
	}

	public boolean setOrganizer(boolean isOrganizer) {
		this.isOrganizer = isOrganizer;
		return DBConnect.updateBoolean("participants", "is_organizer", isOrganizer, "user_email", eMail);
	}

	public boolean[] getCheckboxValues() {
		return this.checkboxValues;
	}

	public void setCheckboxValues(boolean[] checkboxValues) {
		this.checkboxValues = checkboxValues;
	}

	/**
	 * @return location. If location = null gets location object with locationId
	 * 
	 * @author Florian
	 */
	public Location getLocation() {
		if (location == null) {
			location = new Location(locationId);
		}
		return this.location;
	}

	// public List<Application> getApplications() {
	// return this.applications;
	// }

	// public boolean removeApplication(Application oldApplication) {
	// return applications.remove(oldApplication);
	// }

	public Participant(String eMail, String password) {		
		super(eMail, password);
		
		// check in DB if table participants contains eMail
		PreparedStatement preparedStmt = DBConnect
				.getPreparedStatement("SELECT * FROM `participants` WHERE `user_email` = ?");
		try {

			preparedStmt.setString(1, eMail);

			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			// check if there is any data -> returns false if there are no rows
			// in the ResultSet
			if (resultSet.next()) {
				locationId = resultSet.getInt("locations_id");
				isOrganizer = resultSet.getBoolean("is_organizer");
			
				// convert String to char array and char to boolean and fill
				// checkboxValues array
				Character[] cBVs = resultSet.getString("checkboxvalues").chars().mapToObj(c -> (char) c)
						.toArray(Character[]::new);
				for (int i = 0; i < cBVs.length; i++) {
					if (cBVs[i].equals(0)) {
						checkboxValues[i] = false;
					} else {
						checkboxValues[i] = true;
					}
				}

			} else {
				preparedStmt.close();
				
				// create location and get ID
				location = new Location(0);
				locationId = location.getId();

				preparedStmt = DBConnect.getPreparedStatement(
						"INSERT INTO 'participants' ('user_email', 'locations_id', 'checkboxvalues') VALUES (?, ?, '000');");
				
				preparedStmt.setString(1, eMail);
				preparedStmt.setInt(2, locationId);
				
				preparedStmt.execute();

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
	 * if the participant wants to unsubscribe
	 * 
	 * @param reason
	 *            -> why does the participant want to unsubscribe?
	 * 
	 * @param oldApplication
	 *            ->witch application should be removed?
	 */
	public boolean signOutFromApplication(String reason) {
		return false;

	}

}