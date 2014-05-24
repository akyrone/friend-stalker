package com.itomkinas.friendStalker.domain.dao;

import java.util.List;

import com.itomkinas.friendStalker.domain.entity.OnlinePresence;
import com.itomkinas.friendStalker.domain.entity.UserEntity;

public interface OnlinePresenceDao {

	OnlinePresence merge(OnlinePresence user);
	List<OnlinePresence> getByUser(UserEntity user);
	List<OnlinePresence> getByUser(UserEntity user, int hours);
}
