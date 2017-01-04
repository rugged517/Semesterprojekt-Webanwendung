package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//user that can only subscribe to events
@Entity
@Table (name = "Participant")
@PersistenceContext(unitName = "eventPlanner")
public class Participant extends User implements java.io.Serializable{

	protected String name;
	protected Location location;
	protected int phoneNumber;
	protected List<Application> applications = new ArrayList<Application>();
	protected boolean[] checkboxValues;
	private EntityManager em;
	
	@Id
	public String getEmail(){
		return this.getEMail();
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	public Location getLocation() {
		return this.location;
	}
	
	public int getLocationId(){
		return this.location.getId();	
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@OneToMany (mappedBy = "participant")
	public List<Application> getApplications() {
		return this.applications;
	}
	
	public boolean removeApplication(Application oldApplication) {
		return applications.remove(oldApplication);
	}
	
	public boolean[] getCheckboxValues() {
		return this.checkboxValues;
	}

	public void setCheckboxValues(boolean[] checkboxValues) {
		this.checkboxValues = checkboxValues;
	}

	public Participant(String eMail, String password) {
		super(eMail, password);
		//check in DB if table participants contains eMail
		if(em.find(Participant.class, eMail))  {
			// TODO Do something if Participant exists in DB
		}
		
		
	}
	protected Participant(){
		
	}
	
	/**
	 * if the participant wants to unsubscribe
	 * 
	 * @param reason -> why does the participant want to unsubscribe?
	 * 
	 * @param oldApplication ->witch application should be removed?
	 */
	public boolean signOutFromApplication(String reason, Application oldApplication){
		/*
		 * TODO EntityManager connection to DB to remove or save data
		 * either from Participant or from Application!?
		 */
		
		oldApplication.signOut(reason);
		return applications.remove(oldApplication);
		
	}
	
}