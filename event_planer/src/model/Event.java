package model;

import java.util.Date;

//contains informations about an event and an array of applications

public class Event {
	
	private Organizer organizer;
	private String title;
	private String link;
	private String text;
	private String eMail;
	private Location location;
	private Date eventDate;
	private Date deadline;
	private int minParticipants = 0;
	private int maxParticipants = 0;
	private AdditionalInformation additionalInformation;
	private Application[] applications;


	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return this.link;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDate() {
		return this.eventDate;
	}

	public void setDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	public int getMinParticipants() {
		return this.minParticipants;
	}

	public void setMinParticipants(int minParticipants) {
		this.minParticipants = minParticipants;
	}

	public int getMaxParticipants() {
		return this.maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public AdditionalInformation getAdditionalInformation() {
		return this.additionalInformation;
	}

	public void setAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Application[] getApplications() {
		return this.applications;
	}

	public void setApplications(Application[] applications) {
		this.applications = applications;
	}

	/**
	 * 
	 * needs to generate an unique link
	 * @param title
	 * @param location
	 * @param date
	 */
	public Event(Organizer organizer, String title, Location location, Date eventDate) {

		this.link=Functions.randomString(15);
		//TODO check DB if link is unique
		
		this.organizer=organizer;
		this.title=title;
		this.location=location;
		this.eventDate=eventDate;
		additionalInformation=new AdditionalInformation();
	}

	/**
	 * 
	 * @param reason ->why cancel the event?
	 * 
	 * @param message ->message to the subscribed participants
	 */
	public void cancelEvent(String reason, String message) {
		// TODO - remove event from DB
		throw new UnsupportedOperationException();
	}

	//returns all registered participants
	public Participant[] getListOfParticipants() {
		Participant[] listOfParticipants= new Participant[applications.length];

		for (int i=0; i<applications.length; i++){
			listOfParticipants[i]=applications[i].getParticipant();	
		}
		return listOfParticipants;
	}
	
	
	/**
	 * 
	 * @param application ->witch application should be removed?
	 */
	public void removeApplication(Application oldApplication) {
		
		applications=Functions.removeApplication(oldApplication, applications);

	}
	
	/**
	 * 
	 * @param application ->witch application should be removed?
	 */
	public void removeParticipan(Participant participant) {
		
		for(Application application: applications){
			if(application.getParticipant()==participant){
				participant.setApplications(Functions.removeApplication(application, participant.getApplications()));
				applications=Functions.removeApplication(application, applications);
			}		
		}

	}


}