package com.itomkinas.friendStalker.domain.dao;

import com.itomkinas.friendStalker.domain.entity.OnlinePresence;

public interface OnlinePresenceDao {

	OnlinePresence merge(OnlinePresence user);
	
}
