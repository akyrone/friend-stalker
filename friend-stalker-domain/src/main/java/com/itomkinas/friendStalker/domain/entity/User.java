package com.itomkinas.friendStalker.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements Comparable<Object> {
	@Id
	@GeneratedValue
	private Long userId;
	
	@Column(name="full_name")
	private String fullName;
	
	private String uid;
	private String tokken;
	
	@Column(name="valid_till")
	private Date validTill;
	
	public User() {
	}

	public User(String fullName, String uid, String tokken, Date validTill) {
		super();
		this.fullName = fullName;
		this.uid = uid;
		this.tokken = tokken;
		this.validTill = validTill;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTokken() {
		return tokken;
	}

	public void setTokken(String tokken) {
		this.tokken = tokken;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	@Override
	public int compareTo(Object o) {
		 User u = (User) o;
	        if (u.getUid() != this.getUid()) {
	            return 1;
	        } else {
	            return 0;
	        }
	}
}
