package com.itomkinas.friendStalker.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.itomkinas.friendStalker.domain.dao.UserDao;
import com.itomkinas.friendStalker.domain.entity.UserEntity;

@Repository
public class UserDaoJpa implements UserDao {

    @PersistenceContext
    private EntityManager em;
	
    @Override
    public UserEntity loadByUid(String userUid) {
        return em.find(UserEntity.class, userUid);
    }
    
    @Override
    public void merge(UserEntity user) {
    	em.merge(user);
    }

    @Override
    public List<UserEntity> listAll() {
        return em.createQuery("Select c FROM User c", UserEntity.class)
                .getResultList();
    }
}
