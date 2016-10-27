package Model;

import java.util.Date;

public class Event {

	private string title;
	private string link;
	private string text;
	private string eMail;
	private Location location;
	private Date date;
	private Date deadline;
	private int minParticipants = 0;
	private int maxParticipants = 0;
	private AdditionalInformation additionalInformation;
	private Application[] applications;

	/**
	 * 
	 * @param reason ->why cancel the event?
	 * @param message ->message to the subscribed particpants
	 */
	public void cancelEvent(string reason, string message) {
		// TODO - implement Event.cancelEvent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param title
	 * @param link
	 * @param location
	 * @param date
	 */
	public Event(string title, string link, Location location, datetime date) {
		// TODO - implement Event.Event
		throw new UnsupportedOperationException();
	}

}