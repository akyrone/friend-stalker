package com.itomkinas.friendStalker.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.itomkinas.friendStalker.domain.dao.UserDao;
import com.itomkinas.friendStalker.domain.entity.UserEntity;

@Repository
public class UserDaoJpa implements UserDao {

    @PersistenceContext
    private EntityManager em;
	
    @Override
    public UserEntity loadByUid(String facebookUid) {
    	TypedQuery<UserEntity> query = em.createQuery(
    			"SELECT u FROM UserEntity u WHERE u.uid = :uid",
    				UserEntity.class);
    	query.setParameter("uid", facebookUid);
    	
    	 try {
             return query.getSingleResult();
         } catch (NoResultException e) {
             return null;
         }
    }
    
    @Override
    public UserEntity merge(UserEntity user) {
    	return em.merge(user);
    }

    @Override
    public List<UserEntity> listAll() {
        return em.createQuery("Select c FROM UserEntity c", UserEntity.class)
                .getResultList();
    }

	@Override
	public List<UserEntity> listAllActiveUsers() {
		return em.createQuery("Select c FROM UserEntity c WHERE c.tokken is not null", UserEntity.class)
				.getResultList();
	}
}
