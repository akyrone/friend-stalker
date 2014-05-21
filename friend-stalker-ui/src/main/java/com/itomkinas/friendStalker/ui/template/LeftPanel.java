package com.itomkinas.friendStalker.ui.template;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.itomkinas.friendStalker.domain.entity.UserEntity;
import com.itomkinas.friendStalker.ui.utils.FriendStalkerSession;
import com.itomkinas.friendStalker.ui.utils.StaticImage;

public class LeftPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public LeftPanel(String id) {
		super(id);
		
		UserEntity user = FriendStalkerSession.get().getUser();
		
		add(new StaticImage("logo", Model.of("http://graph.facebook.com/"+ user.getUid()  +"/picture")));
		add(new Label("name", user.getFullName()));
	}
}
