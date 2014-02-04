package com.itomkinas.friendStalker.ui;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;

import com.itomkinas.friendStalker.domain.service.UserService;
import com.itomkinas.friendStalker.ui.template.BasePage;
import com.itomkinas.friendStalker.ui.utils.FriendStalkerSession;

public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	@Autowired
	UserService userService;
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(initLabel("name", FriendStalkerSession.get().getUser().getFullName()));
	}
	
	public Label initLabel(String wicketId, String value) {
		return new Label(wicketId, Model.of(value));
	}
}
