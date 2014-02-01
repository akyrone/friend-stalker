package com.itomkinas.friendStalker.ui;

import com.itomkinas.friendStalker.domain.entity.UserEntity;
import com.itomkinas.friendStalker.ui.template.BasePage;
import com.itomkinas.friendStalker.ui.utils.FriendStalkerSession;

public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		UserEntity user = FriendStalkerSession.get().getUser();
		System.out.println(user.getFullName());
	}
}
