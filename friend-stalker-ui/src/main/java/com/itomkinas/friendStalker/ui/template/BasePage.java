package com.itomkinas.friendStalker.ui.template;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import com.itomkinas.friendStalker.ui.user.LoginPage;
import com.itomkinas.friendStalker.ui.utils.FriendStalkerSession;

public class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		authenticate();
		add(new LeftPanel("leftPanel"));
		add(new TopPanel("topPanel"));
	}
	
	private void authenticate() {
		if (!isLoggedIn()) {
			throw new RestartResponseException(LoginPage.class);
		}
	}

	protected boolean isLoggedIn() {
		return (FriendStalkerSession.get().isAuthenticated());
	}
}