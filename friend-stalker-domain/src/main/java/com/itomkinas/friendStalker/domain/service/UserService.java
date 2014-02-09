package com.itomkinas.friendStalker.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itomkinas.friendStalker.domain.dao.UserDao;
import com.itomkinas.friendStalker.domain.entity.UserEntity;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	public List<UserEntity> listAll() {
		return userDao.listAll();
	}

	@Transactional
	public UserEntity loadByUid(String uid) {
		return userDao.loadByUid(uid);
	}

	@Transactional
	public UserEntity merge(UserEntity user) {
		return userDao.merge(user);
	}

	public User fetchFacebookUser(AccessToken token) {
		FacebookClient facebookClient = new DefaultFacebookClient(token.getAccessToken());
		return facebookClient.fetchObject("me", User.class);
	}
	
	@Transactional
	public UserEntity login(AccessToken token) {
		User fbUser = fetchFacebookUser(token);

		if (fbUser == null) {
			return null;
		}

		UserEntity user = loadByUid(fbUser.getId());
		if (user == null) {
			user = merge(new UserEntity(fbUser.getId(), fbUser.getName(),
					token.getAccessToken(), token.getExpires()));
		} else {
			user.setTokken(token.getAccessToken());
			user.setTokenValidTill(token.getExpires());
			user = merge(user);
		}
		
		return user;
	}
	
	@Transactional
	public void saveFriends(UserEntity currentUser) {
		FacebookClient facebookClient = new DefaultFacebookClient(currentUser.getTokken());
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		List<User> friends = myFriends.getData();
		
		for (int i = 0; i < friends.size(); i++) {
			User fbUser = friends.get(i);
			UserEntity friend = loadByUid(fbUser.getId());
			if (friend == null) {
				friend = new UserEntity();
				friend.setFullName(fbUser.getName());
				friend.setUid(fbUser.getId());
				currentUser.getFriends().add(friend);
			} else {
				currentUser.getFriends().add(friend);
			}
		}
		merge(currentUser);
	}
}
