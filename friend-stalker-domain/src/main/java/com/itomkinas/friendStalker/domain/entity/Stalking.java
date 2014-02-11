package com.itomkinas.friendStalker.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Stalkings")
public class Stalking {

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(name = "uid1")
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity stalker;

	@JoinColumn(name = "uid2")
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity victim;

	public UserEntity getStalker() {
		return stalker;
	}

	public UserEntity getVictim() {
		return victim;
	}
}
