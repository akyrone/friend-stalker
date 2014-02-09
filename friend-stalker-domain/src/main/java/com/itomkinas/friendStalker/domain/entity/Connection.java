package com.itomkinas.friendStalker.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Connections")
public class Connection {

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(name = "uid1")
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user1;

	@JoinColumn(name = "uid2")
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user2;

	public UserEntity getUser1() {
		return user1;
	}

	public UserEntity getUser2() {
		return user2;
	}
}
