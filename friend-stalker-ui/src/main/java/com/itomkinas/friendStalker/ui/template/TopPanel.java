package com.itomkinas.friendStalker.ui.template;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.itomkinas.friendStalker.ui.user.LoginPage;
import com.itomkinas.friendStalker.ui.utils.FriendStalkerSession;

public class TopPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public TopPanel(String id) {
		super(id);
		add(initLogoutLink("logout"));
	}
	
	private Link<Object> initLogoutLink(String wicketId) {
		return new Link<Object>(wicketId) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				FriendStalkerSession.get().setUser(null);
				setResponsePage(LoginPage.class);
			}
		};
	}
}
