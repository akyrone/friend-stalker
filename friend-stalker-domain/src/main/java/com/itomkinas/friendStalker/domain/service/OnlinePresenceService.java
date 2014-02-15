package com.itomkinas.friendStalker.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itomkinas.friendStalker.domain.dao.OnlinePresenceDao;
import com.itomkinas.friendStalker.domain.entity.OnlinePresence;

@Service
public class OnlinePresenceService {

	@Autowired
	private OnlinePresenceDao onlinePresenceDao;

	@Transactional
	public OnlinePresence merge(OnlinePresence onlinePresence) {
		return onlinePresenceDao.merge(onlinePresence);
	}
}
