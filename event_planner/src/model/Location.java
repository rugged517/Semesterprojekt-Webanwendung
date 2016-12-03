package model;

//contains all information about a location

public class Location {

	private String company;
	private String name;
	private String street;
	private String postcode;
	private String city;

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @param company
	 * @param name
	 * @param street
	 * @param postcode
	 * @param city
	 */
	public Location(String company, String name, String street, String postcode, String city) {
		this.company=company;
		this.name=name;
		this.street=street;
		this.postcode=postcode;
		this.city=city;
	}

}