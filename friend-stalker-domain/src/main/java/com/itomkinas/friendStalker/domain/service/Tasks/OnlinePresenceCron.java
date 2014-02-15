package com.itomkinas.friendStalker.domain.service.Tasks;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itomkinas.friendStalker.domain.service.UserService;

@Service
public class OnlinePresenceCron {
	
	@Autowired
	UserService userService;
	
    @Scheduled(cron="* * * * * *")
    @Transactional
    public void process() {
    	//System.out.println("Dummy cron " + new Date());
    }
}
