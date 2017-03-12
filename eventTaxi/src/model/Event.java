package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import controller.CheckFunctionsC;

//contains informations about an event

public class Event {

	private String link;
	private String organizerMail;
	private String title;
	private String eMail;
	private Location location;
	private String text;
	private int min;
	private int max;
	private Timestamp start;
	private Timestamp end;
	private Timestamp dead;
	
	public String getTitle() {
		return title;
	}

	public Location getLocation() {
		return location;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public Timestamp getStart() {
		return start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public Timestamp getDead() {
		return dead;
	}

	public String getLink() {
		return link;
	}

	public String getOrganizerMail() {
		return organizerMail;
	}
	
	/**
	 * Gets Event from DB or creates a new Event with unique link
	 * 
	 * @param organizerMail
	 *            -id from organizer
	 * @param eventValues
	 *            -contains: "title", "surname", "name", "eMail", "phonenumber",
	 *            "company", "street", "postcode", "city", "min", "max",
	 *            "start", "end", "dead", "text"
	 */
	public Event(String organizerMail, Hashtable<String, String> eventValues) {
		this.organizerMail = organizerMail;

			// check if event link is unique
			link = Functions.randomString(15);

			while (!checkUniqeLink()) {
				link = Functions.randomString(15);
			}

			// insert event in DB
			// create location and get ID
			location = new Location(0);


			// create the insert preparedstatement for Event
			PreparedStatement preparedStmt = DBConnect.getPreparedStatement(
					"INSERT INTO `events` (`link`,`participants_user_email`,`locations_id`,`title`,`email`,`text`,`startdate`,`deadline`,`enddate`,`min`,`max`) VALUES (?,?,?,?,?,?,?,?,?,?,?);");

			try {
				preparedStmt.setString(1, link);
				preparedStmt.setString(2, organizerMail);
				preparedStmt.setInt(3, location.getId());
				preparedStmt.setString(4, eventValues.get("title"));
				preparedStmt.setString(5, eventValues.get("eMail"));
				preparedStmt.setString(6, eventValues.get("text"));
				preparedStmt.setTimestamp(7, Functions.prepareDate(eventValues.get("start")));
				preparedStmt.setTimestamp(8, Functions.prepareDate(eventValues.get("dead")));
				preparedStmt.setTimestamp(9, Functions.prepareDate(eventValues.get("end")));
				preparedStmt.setInt(10, Integer.parseInt(eventValues.get("min")));
				preparedStmt.setInt(11, Integer.parseInt(eventValues.get("max")));
				
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
			
			// fill location with values -> remove event parameter from eventValues
			String[] eventParameter = { "title", "eMail", "min", "max", "start", "end", "dead", "text" };
			
			for (int i = 0; i < eventParameter.length; i++) {
				eventValues.remove(eventParameter[i]);
			}
			location.fillAll(eventValues);


	}

	public Event(String link) {		
		this.link=link;
		
		PreparedStatement preparedStmt = DBConnect
				.getPreparedStatement("SELECT * FROM `events` WHERE `link` = ?");
		try {
			preparedStmt.setString(1, link);

			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			// check if there is any data -> returns false if there are no rows
			// in the ResultSet
			if (resultSet.next()) {
				location = new Location(resultSet.getInt("locations_id"));
			
				organizerMail=resultSet.getString("participants_user_email");
				title=resultSet.getString("title");
				text=resultSet.getString("text");
				eMail=resultSet.getString("email");
				min=resultSet.getInt("min");
				max=resultSet.getInt("max");
				start=resultSet.getTimestamp("startdate");
				end=resultSet.getTimestamp("enddate");
				dead=resultSet.getTimestamp("deadline");
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

	private boolean checkUniqeLink() {
		boolean isUniqueLink = false;

		// create the select preparedstatement
		PreparedStatement preparedStmt = DBConnect
				.getPreparedStatement("SELECT COUNT( * ) FROM `events` WHERE `link` = ?");

		try {
			preparedStmt.setString(1, link);

			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			resultSet.next();
			isUniqueLink = (resultSet.getInt("COUNT( * )") == 0);

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
		return isUniqueLink;
	}

	public Hashtable<String, String> getEventData() {
		Hashtable<String, String> eventValues = new Hashtable<String, String>();
		
		String[] eventParameter = { "title", "eMail", "min", "max", "start", "end", "dead", "text" };
		Object[] eventVal = { title, eMail, min, max, start, end, dead, text };
		
		for (int i = 0; i < eventParameter.length; i++) {
			eventValues.put(eventParameter[i], String.valueOf(eventVal[i]));
		}
		
		Hashtable<String, String> locationValues =location.getLocationData();

		Hashtable<String, String> returnValues= new Hashtable<String, String>();
		returnValues.putAll(eventValues);
		returnValues.putAll(locationValues);

		return returnValues;
	}



}