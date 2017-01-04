package model;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import org.apache.commons.dbcp.managed.LocalXAConnectionFactory;
//contains all information about a location
@Entity
@Table (name = "Location")
@PersistenceContext( unitName = "eventPlanner")
public class Location implements java.io.Serializable {
	
	private int Id;
	private String company;
	private String name;
	private String street;
	private String postcode;
	private String city;
	private Collection<Event> events = new ArrayList<Event>();
	private EntityManager em;
	
	@OneToMany (mappedBy = "location")
	public Collection<Event> getEvents(){
		return events;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

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
		Location loc = (Location) em.find(Location.class, this.Id);
		this.company = company;
		this.name = name;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
		em.persist(loc);
	}
	protected Location(){
		
	}

}