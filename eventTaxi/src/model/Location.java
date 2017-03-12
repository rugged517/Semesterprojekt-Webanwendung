package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

//contains all information about a location, used for participants, events and applications

public class Location {

	private int id;
	private String company;
	private String name;
	private String surname;
	private String street;
	private int postcode;
	private String city;
	private int phonenumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostcode() {
		return this.postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * 
	 * Loads all location data from DB or if id=0 creates new location in DB
	 * 
	 * @param id
	 *            - Location id. If 0 creates new location in DB.
	 */
	public Location(int id) {

		Connection connect = null;
		try {
			connect = DBConnect.getConnection();

			// if id=0 create new location in DB
			if (id == 0) {
				PreparedStatement preparedInsert = connect.prepareStatement(
						"INSERT INTO `locations` (`id`, `company`, `name`, `surname`, `street`, `postcode`, `city`, `phonenumber`) "
								+ "VALUES (NULL , NULL , NULL , NULL , NULL , '0', NULL , '0');",
						Statement.RETURN_GENERATED_KEYS);

				preparedInsert.executeUpdate();

				// get auto generated location id
				ResultSet resultSet = preparedInsert.getGeneratedKeys();
				resultSet.next();
				this.id = resultSet.getInt(1);

			} else {
				// get data from DB
				// create the sql select preparedstatement
				PreparedStatement preparedStmt = connect.prepareStatement("SELECT * FROM `locations` WHERE `id` = ?");

				preparedStmt.setInt(1, id);

				// execute the preparedstatement
				ResultSet resultSet = preparedStmt.executeQuery();

				// check if there is any data -> returns false if there are no
				// rows in the ResultSet
				if (resultSet.next()) {

					company = resultSet.getString("company");
					name = resultSet.getString("name");
					surname=resultSet.getString("surname");
					street = resultSet.getString("street");
					postcode = resultSet.getInt("postcode");
					city = resultSet.getString("city");
					phonenumber = resultSet.getInt("phonenumber");

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Fills Location with values from Hashtable<String, String> and updates location in DB.
	 * 
	 */
	public void fillAll(Hashtable<String, String> locationValues){		
		company = locationValues.get("company");
		name = locationValues.get("name");
		surname=locationValues.get("surname");
		street = locationValues.get("street");
		postcode = Integer.parseInt(locationValues.get("postcode"));
		city = locationValues.get("city");
		phonenumber = Integer.parseInt(locationValues.get("phonenumber"));
		
		updateAll();
	}
	
	/**
	 * Updates all values in DB
	 * 
	 */
	private void updateAll(){		
		// create the insert preparedstatement for Event
		PreparedStatement preparedStmt = DBConnect.getPreparedStatement(
				"UPDATE `locations` SET `company` = ?, `name` = ?, `surname` = ?, `street` = ?, `postcode` = ?, `city` = ?, `phonenumber` = ?  WHERE  `id` = ?;");

		try {
			preparedStmt.setString(1, company);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, surname);
			preparedStmt.setString(4, street);
			preparedStmt.setInt(5, postcode);
			preparedStmt.setString(6, city);
			preparedStmt.setInt(7, phonenumber);
			preparedStmt.setInt(8, id);
			
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
	public Hashtable<String, String> getLocationData() {
		Hashtable<String, String> locationValues = new Hashtable<String, String>();
		
		String[] locationParameter={"surname", "name", "phonenumber", "company", "street", "postcode", "city"};
		Object[] locationVal = { surname, name, phonenumber, company, street, postcode, city};
		
		for (int i = 0; i < locationParameter.length; i++) {
			locationValues.put(locationParameter[i], String.valueOf(locationVal[i]));
		}
		return locationValues;
	}
	
}