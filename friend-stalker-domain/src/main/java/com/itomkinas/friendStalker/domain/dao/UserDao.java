package com.itomkinas.friendStalker.domain.dao;

import java.util.List;

import com.itomkinas.friendStalker.domain.entity.User;

public interface UserDao {
	User loadByUid(Long id);
	
	List<User> listAll();
}
