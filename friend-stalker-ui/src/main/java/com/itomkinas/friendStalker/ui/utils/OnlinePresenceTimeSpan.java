package com.itomkinas.friendStalker.ui.utils;

import java.io.Serializable;
import java.util.Date;

public class OnlinePresenceTimeSpan implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date from;
	private Date to;
	private String status;
	
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
