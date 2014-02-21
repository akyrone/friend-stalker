package com.itomkinas.friendStalker.ui.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
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
				
    	        item.add(new Label("from", dateFormatter.format(item.getModelObject().getFrom())));
    	        item.add(new Label("to", dateFormatter.format(item.getModelObject().getTo())));
    	        item.add(new Label("status", item.getModelObject().getStatus()));
    	    }
    	};
    	
    	return listView;
    }
    
    public List<OnlinePresenceTimeSpan> convert(List<OnlinePresence> list) {
    	List<OnlinePresenceTimeSpan> convertedList = new ArrayList<>(); 
    	OnlinePresenceTimeSpan timeSpan = null;
    	
    	for (OnlinePresence currentElement: list) {
    		if (timeSpan == null) {
    			timeSpan = newTimeSpan(currentElement.getTime(), currentElement.getOnlineStatus());
    		}
    		if(!timeSpan.getStatus().equals(currentElement.getOnlineStatus())) {
    			timeSpan.setTo(currentElement.getTime());
    			convertedList.add(timeSpan);
    			timeSpan = newTimeSpan(currentElement.getTime(), currentElement.getOnlineStatus());
    		}
    	}
    	
    	if (timeSpan != null) {
    		timeSpan.setTo(new Date());
    		convertedList.add(timeSpan);
    	}
    	
    	return convertedList;
    }
    
    private OnlinePresenceTimeSpan newTimeSpan(Date from, String status) {
    	OnlinePresenceTimeSpan timeSpan = new OnlinePresenceTimeSpan();
    	timeSpan.setFrom(from);
    	timeSpan.setStatus(status);
		return timeSpan;
	}

}
