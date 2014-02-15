package com.itomkinas.friendStalker.domain.service.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itomkinas.friendStalker.domain.service.UserService;

@Service
public class OnlinePresenceCron {
	
	@Autowired
	UserService userService;
	
    @Scheduled(fixedRate=5000)
    @Transactional
    public void process() {
    	//System.out.println("testing");
    }
}
