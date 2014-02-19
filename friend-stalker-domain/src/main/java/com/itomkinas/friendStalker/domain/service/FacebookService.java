package com.itomkinas.friendStalker.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itomkinas.friendStalker.domain.entity.OnlinePresence;
import com.itomkinas.friendStalker.domain.entity.UserEntity;
import com.itomkinas.friendStalker.domain.entity.facebook.FqlUser;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

@Service
public class FacebookService {

	@Autowired
	UserService userService;
	
	@Autowired
	OnlinePresenceService onlinePresenceService;
	
    @Scheduled(fixedRate=60000)
    @Transactional
    public void trackOnlineStatus() {
    	List<UserEntity> userList = userService.loadAllActiveUsers();
    	
    	for (UserEntity user : userList) {
    		saveOnlineStatus(user, user.getVictims());
    	}
    }
	
	@Transactional
	public void saveOnlineStatus(UserEntity currentUser, List<UserEntity> friendList) {
		FacebookClient facebookClient = new DefaultFacebookClient(currentUser.getTokken());
		String query = buildFqlQuery(friendList);
		
		if (query == null) {
			return;
		}
		
		List<FqlUser> fqlUserList = facebookClient.executeFqlQuery(query, FqlUser.class);
		
		for (FqlUser fqlUser : fqlUserList) {
			UserEntity user =  userService.loadByUid(fqlUser.getUid());
			onlinePresenceService.merge(new OnlinePresence(user, fqlUser.getOnline_presence()));
		}
	}
	
	private String buildFqlQuery(List<UserEntity> friendList) {
		if (friendList.size() < 1) {
			return null;
		}
		
		String query = "SELECT uid, online_presence FROM user WHERE ";
		
		query += "uid = " + friendList.get(0).getUid();
		for (int i = 1; i < friendList.size(); i++) {
			query += " OR uid = " + friendList.get(i).getUid();
		}
		
		return query;
	}
}
