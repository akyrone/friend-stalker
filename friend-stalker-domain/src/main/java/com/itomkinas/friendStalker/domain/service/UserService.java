package com.itomkinas.friendStalker.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itomkinas.friendStalker.domain.dao.UserDao;
import com.itomkinas.friendStalker.domain.entity.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    
    @Transactional(readOnly = true)
    public List<User> listAll() {
        return userDao.listAll();
    }
    
    @Transactional
    public User loadByUid(Long id) {
        return userDao.loadByUid(id);
    }
	
}
