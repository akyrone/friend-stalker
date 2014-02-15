package com.itomkinas.friendStalker.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Online_presence")
public class OnlinePresence {

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;
	
	@Column(name = "online_status")
	private String onlineStatus;
	
	private Date time;

	public OnlinePresence() {}
	
	public OnlinePresence(UserEntity user, String onlineStatus) {
		this.user = user;
		this.onlineStatus = onlineStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
