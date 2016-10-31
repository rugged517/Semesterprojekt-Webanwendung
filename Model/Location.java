package Model;

//contains all information about a location

public class Location {

	private string company;
	private string name;
	private string street;
	private string postcode;
	private string city;

	public string getCompany() {
		return this.company;
	}

	public void setCompany(string company) {
		this.company = company;
	}

	public string getName() {
		return this.name;
	}

	public void setName(string name) {
		this.name = name;
	}

	public string getStreet() {
		return this.street;
	}

	public void setStreet(string street) {
		this.street = street;
	}

	public string getPostcode() {
		return this.postcode;
	}

	public void setPostcode(string postcode) {
		this.postcode = postcode;
	}

	public string getCity() {
		return this.city;
	}

	public void setCity(string city) {
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
	public Location(string company, string name, string street, string postcode, string city) {
		// TODO - implement Location.Location
		throw new UnsupportedOperationException();
	}

}