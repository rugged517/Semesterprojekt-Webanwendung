package Model;

//is an user that can subscribe to events and create events

public class Organizer extends Participant {

	private Event[] events;

	public Event[] getEvents() {
		return this.events;
	}

	public void setEvents(Event[] events) {
		this.events = events;
	}

}