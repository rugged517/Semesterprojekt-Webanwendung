package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAll {

	public static List<Participant> getAllParticipants() {

		List<Participant> allParticipants = new ArrayList<Participant>();

		// TODO remove password from Select if not needed
		PreparedStatement preparedStmt = DBConnect.getPreparedStatement("SELECT * FROM `user`");
		try {

			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				// TODO remove password if not needed
				allParticipants.add(new Participant(resultSet.getString("email"), resultSet.getString("password")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allParticipants;
	}

	public static List<Event> getAllEvents() {

		List<Event> allEvents = new ArrayList<Event>();

		PreparedStatement preparedStmt = DBConnect.getPreparedStatement("SELECT `link` FROM `events`");
		try {

			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				allEvents.add(new Event(resultSet.getString("link")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allEvents;
	}

	/**
	 * Gets all Applications from DB, depending on input parameter. If link and
	 * userMail are null returns all Applications. If eventLink and userMail are
	 * not null it will return all applications for this event.
	 * 
	 * @param eventLink
	 *            -if not null returns all applications for this event
	 * @param userMail
	 *            -if not null returns all applications for this user
	 */
	public static List<Application> getAllApplications(String eventLink, String userMail) {
		List<Application> allApplications = new ArrayList<Application>();

		PreparedStatement preparedStmt = null;

		if (eventLink != null) {
			preparedStmt = DBConnect.getPreparedStatement(
					"SELECT `events_link`, `participants_user_email` FROM `applications` WHERE `events_link` = ?");
		} else if (userMail != null) {
			preparedStmt = DBConnect.getPreparedStatement(
					"SELECT `events_link`, `participants_user_email` FROM `applications` WHERE `participants_user_email` = ?");
		} else {
			preparedStmt = DBConnect
					.getPreparedStatement("SELECT `events_link`, `participants_user_email` FROM `applications`");
		}

		try {

			if (eventLink != null) {
				preparedStmt.setString(1, eventLink);
			} else if (userMail != null) {
				preparedStmt.setString(1, userMail);
			}

			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				allApplications.add(new Application(resultSet.getString("participants_user_email"),
						resultSet.getString("events_link")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allApplications;
	}
	
	public static List<Object> getAllFields(String eventLink, String type) {

		List<Object> allInformation = new ArrayList<Object>();

		PreparedStatement preparedStmt = DBConnect.getPreparedStatement("SELECT `id` FROM `additionalInformation` WHERE `events_link` = ? AND `type` = ?");
		try {
			preparedStmt.setString(1, eventLink);
			preparedStmt.setString(2, type);
			
			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				if(type.equals("text")){
					allInformation.add(new TextField(resultSet.getInt("id")));
				}else if(type.equals("select")){
					allInformation.add(new SelectField(resultSet.getInt("id")));
				}else if(type.equals("checkbox")){
					allInformation.add(new CheckboxField(resultSet.getInt("id")));
				}else if(type.equals("date")){
					allInformation.add(new DateField(resultSet.getInt("id")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allInformation;
	}

}
