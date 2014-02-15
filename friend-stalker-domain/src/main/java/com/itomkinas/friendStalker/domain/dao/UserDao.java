package com.itomkinas.friendStalker.domain.dao;

import java.util.List;

import com.itomkinas.friendStalker.domain.entity.UserEntity;

public interface UserDao {
	UserEntity loadByUid(String id);
	
	List<UserEntity> listAll();
	
	List<UserEntity> listAllActiveUsers();

	UserEntity merge(UserEntity user);
	
}
