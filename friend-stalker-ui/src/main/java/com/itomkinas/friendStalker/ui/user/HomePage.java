package com.itomkinas.friendStalker.ui.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;

import com.itomkinas.friendStalker.domain.entity.UserEntity;
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
		add(initFriendList(FriendStalkerSession.get().getUser()));
	}
	
	public Label initLabel(String wicketId, String value) {
		return new Label(wicketId, Model.of(value));
	}
	
	@SuppressWarnings("rawtypes")
	public ListView initFriendList(UserEntity userEntity) {
		
		List<UserEntity> friendList = new ArrayList<UserEntity>(userEntity.getFriends());
		
		@SuppressWarnings("unchecked")
		ListView friendListListView = new ListView("friendList", friendList) {
			private static final long serialVersionUID = 1L;

			@Override
		    protected void populateItem(ListItem item) {
		        UserEntity user = (UserEntity) item.getModelObject();
		        
		        item.add(new Label("id", item.getId()));
		        item.add(new Label("name", user.getFullName()));
		    }
		};
		
		return friendListListView;
	}
}
