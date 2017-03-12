package controller;

import java.util.Date;
import java.util.List;

import model.Application;
import model.Event;
import model.GetAll;
import model.Participant;

public class ListObjectsC {

	public static String[][] getUserList() {

		// get user as Participants to get isOrganizer value
		List<Participant> user = GetAll.getAllParticipants();

		String[][] resultList = new String[user.size()][4];

		for (int i = 0; i < user.size(); i++) {
			// insert eMail in array
			resultList[i][0] = user.get(i).getEMail();

			// insert boolean values in array -> admin, organizer, activated
			resultList[i][1] = String.valueOf(user.get(i).isAdmin());
			resultList[i][2] = String.valueOf(user.get(i).isOrganizer());
			resultList[i][3] = String.valueOf(user.get(i).isActivated());

		}
		return resultList;
	}

	public static String[][] getAllEventsList(String userID) {
		// get user as Participants to get isOrganizer value
		List<Event> events = GetAll.getAllEvents();

		String[][] resultList = new String[events.size()][7];

		for (int i = 0; i < events.size(); i++) {
			// insert values in array
			Event event = events.get(i);
			String link = event.getLink();

			resultList[i][0] = link;
			resultList[i][1] = event.getTitle();
			resultList[i][2] = event.getLocation().getCity();
			resultList[i][3] = String.valueOf(event.getStart());

			// check if max = applications
			int max = event.getMax();
			List<Application> applications = GetAll.getAllApplications(link, null);
			boolean isOpen=true;

			if (max != 0) {
				resultList[i][4] = applications.size() + "/" + max;

				if (applications.size() >= max) {
					isOpen = false;
				}
			} else {
				resultList[i][4] = String.valueOf(applications.size());
			}

			// check if user has application for this event
			Application application = new Application(link, userID);
			boolean hasApplication=false;

			if (application.getEvent() != null) {
				hasApplication=true;
			}
			String timeStatus;
			
			// check if deadline or startdate < now
			if (event.getStart().after(new Date())) {
				// event hasn't started
				timeStatus = "live";
				
				if (!event.getDead().after(new Date())) {
					//deadline is over
					timeStatus = "dead";
				}
			} else {
				timeStatus = "over";
			}

			resultList[i][5]="over";
			
			if(hasApplication&& !timeStatus.equals("over")){
				//user can edit application while start after now
				resultList[i][5]="edit";
			}else if(!hasApplication&&timeStatus.equals("live")&&isOpen){
				//user can create a new application
				resultList[i][5]="create";
			}else if(!hasApplication&&timeStatus.equals("dead")){
				//deadline
				resultList[i][5]="dead";
			}else if(!isOpen){
				//deadline
				resultList[i][5]="max";
			}

		}
		return resultList;
	}

}
