package com.itomkinas.friendStalker.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class UserEntity implements Comparable<Object> {

	@Id
	@GeneratedValue
	private Long userId;

	@Column(name = "full_name")
	private String fullName;

	private String tokken;

	private String uid;

	@Column(name = "token_valid_till")
	private Date tokenValidTill;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="Connections", 
                joinColumns={@JoinColumn(name="uid1")}, 
                inverseJoinColumns={@JoinColumn(name="uid2")})
	private Set<UserEntity> friends = new HashSet<UserEntity>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="Stalkings", 
                joinColumns={@JoinColumn(name="stalker_id")}, 
                inverseJoinColumns={@JoinColumn(name="victim_id")})
	private Set<UserEntity> victims = new HashSet<UserEntity>();

	public UserEntity() {
	}

	public UserEntity(String uid, String fullName, String tokken,
			Date tokenValidTill) {
		this.uid = uid;
		this.fullName = fullName;
		this.tokken = tokken;
		this.tokenValidTill = tokenValidTill;
	}

	public Set<UserEntity> getVictims() {
		return victims;
	}

	public void setVictims(Set<UserEntity> victims) {
		this.victims = victims;
	}

	public String getUserId() {
		return uid;
	}

	public void setUserId(String userId) {
		this.uid = userId;
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

	public Date getTokenValidTill() {
		return tokenValidTill;
	}

	public void setTokenValidTill(Date tokenValidTill) {
		this.tokenValidTill = tokenValidTill;
	}
	
	public Set<UserEntity> getFriends() {
		return friends;
	}

	@Override
	public int compareTo(Object o) {
		UserEntity u = (UserEntity) o;
		if (u.getUid() != this.getUid()) {
			return 1;
		} else {
			return 0;
		}
	}
}
