package com.itomkinas.friendStalker.ui.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.itomkinas.friendStalker.domain.entity.OnlinePresence;
import com.itomkinas.friendStalker.ui.utils.OnlinePresenceTimeSpan;

public class OnlinePresenceTablePanel extends Panel {

	private static final long serialVersionUID = 1L;
	List<OnlinePresence> onlinePresenceList;

	public OnlinePresenceTablePanel(String id, List<OnlinePresence> onlinePresenceList) {
		super(id);
		this.onlinePresenceList = onlinePresenceList;
	}
	
    @Override
    protected void onInitialize() {
    	super.onInitialize();
    	List<OnlinePresenceTimeSpan> timespanList = convert(onlinePresenceList);
    	ListModel<OnlinePresenceTimeSpan> timeSpanList = new ListModel<OnlinePresenceTimeSpan>(timespanList);
    	add(initListView("listview", timeSpanList));
    }
    
    private ListView<OnlinePresenceTimeSpan> initListView(String wicketId, ListModel<OnlinePresenceTimeSpan> timeSpanList) {
    	ListView<OnlinePresenceTimeSpan> listView = new ListView<OnlinePresenceTimeSpan>(wicketId, timeSpanList) {
			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<OnlinePresenceTimeSpan> item) {
				DateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				OnlinePresenceTimeSpan timeSpan = item.getModelObject();
				
				
    	        item.add(new Label("from", dateFormatter.format(timeSpan.getFrom())));
    	        item.add(new Label("to", dateFormatter.format(timeSpan.getTo())));
    	        Label status = new Label("status", timeSpan.getStatus());
    	        status.add(new AttributeAppender("class", new Model<String>("presence-" + timeSpan.getStatus()), " "));
    	        item.add(status);
    	        item.add(new Label("duration", getDuration(timeSpan.getFrom(), timeSpan.getTo())));
			}
    	};
    	
    	return listView;
    }
    
    public String getDuration(Date from, Date to) {
    	long diff = to.getTime() - from.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        
        return diffHours + ":" + twoDigits(diffMinutes) + ":" + twoDigits(diffSeconds);
    }
    
    public String twoDigits(Long num) {
    	String number = num + "";
    	while (number.length() < 2) {
    		number = "0" + number;
    	}
    	return number;
    }
    
    public List<OnlinePresenceTimeSpan> convert(List<OnlinePresence> list) {
    	List<OnlinePresenceTimeSpan> convertedList = new ArrayList<>(); 
    	OnlinePresenceTimeSpan timeSpan = null;
    	
    	Date lastSpan = null;
    	
    	for (OnlinePresence currentElement: list) {
    		
    		if (timeSpan == null) {
    			timeSpan = newTimeSpan(currentElement.getTime(), currentElement.getOnlineStatus());
    		}
    		
    		if (isMoreThanTwoMinutes(lastSpan, currentElement.getTime())) {
    			timeSpan.setStatus("undefined");
    			timeSpan.setTo(currentElement.getTime());
    			convertedList.add(timeSpan);
    			timeSpan = newTimeSpan(currentElement.getTime(), currentElement.getOnlineStatus());
			} else
    		
    		if(!timeSpan.getStatus().equals(currentElement.getOnlineStatus())) {
    			timeSpan.setTo(currentElement.getTime());
    			convertedList.add(timeSpan);
    			timeSpan = newTimeSpan(currentElement.getTime(), currentElement.getOnlineStatus());
    		}
    		lastSpan = currentElement.getTime();
    	}
    	
    	if (timeSpan != null) {
    		timeSpan.setTo(new Date());
    		convertedList.add(timeSpan);
    	}
    	
    	return convertedList;
    }
    
    public boolean isMoreThanTwoMinutes(Date from, Date to) {
    	if (from == null) {
    		return false;
    	}
    	
    	long diff = to.getTime() - from.getTime();
    	long diffSeconds = diff / 1000;
    	return (diffSeconds > 120);
    }
    
    private OnlinePresenceTimeSpan newTimeSpan(Date from, String status) {
    	OnlinePresenceTimeSpan timeSpan = new OnlinePresenceTimeSpan();
    	timeSpan.setFrom(from);
    	timeSpan.setStatus(status);
		return timeSpan;
	}

}
