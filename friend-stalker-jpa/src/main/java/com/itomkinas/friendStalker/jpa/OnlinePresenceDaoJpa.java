package com.itomkinas.friendStalker.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.itomkinas.friendStalker.domain.dao.OnlinePresenceDao;
import com.itomkinas.friendStalker.domain.entity.OnlinePresence;

@Repository
public class OnlinePresenceDaoJpa implements OnlinePresenceDao {

    @PersistenceContext
    private EntityManager em;
	
    @Override
    public OnlinePresence merge(OnlinePresence onlinePresence) {
    	return em.merge(onlinePresence);
    }
}
