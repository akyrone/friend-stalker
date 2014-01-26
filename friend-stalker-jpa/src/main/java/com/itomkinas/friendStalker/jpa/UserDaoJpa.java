package com.itomkinas.friendStalker.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.itomkinas.friendStalker.domain.dao.UserDao;
import com.itomkinas.friendStalker.domain.entity.User;

@Repository
public class UserDaoJpa implements UserDao {

    @PersistenceContext
    private EntityManager em;
	
    @Override
    public User loadByUid(Long userUid) {
        return em.find(User.class, userUid);
    }

    @Override
    public List<User> listAll() {
        return em.createQuery("Select c FROM User c", User.class)
                .getResultList();
    }
}
