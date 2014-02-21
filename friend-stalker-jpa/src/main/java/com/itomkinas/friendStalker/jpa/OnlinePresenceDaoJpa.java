package com.itomkinas.friendStalker.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.itomkinas.friendStalker.domain.dao.OnlinePresenceDao;
import com.itomkinas.friendStalker.domain.entity.OnlinePresence;
import com.itomkinas.friendStalker.domain.entity.UserEntity;

@Repository
public class OnlinePresenceDaoJpa implements OnlinePresenceDao {

    @PersistenceContext
    private EntityManager em;
	
    @Override
    public OnlinePresence merge(OnlinePresence onlinePresence) {
    	return em.merge(onlinePresence);
    }

	@Override
	public List<OnlinePresence> getByUser(UserEntity user) {
		TypedQuery<OnlinePresence> query = em.createQuery("Select c FROM OnlinePresence c WHERE c.user = :user", OnlinePresence.class);
		query.setParameter("user", user);
		return query.getResultList();
	}
}
