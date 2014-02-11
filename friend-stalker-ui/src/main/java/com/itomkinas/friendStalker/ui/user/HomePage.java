package com.itomkinas.friendStalker.ui.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.itomkinas.friendStalker.domain.entity.UserEntity;
import com.itomkinas.friendStalker.domain.service.UserService;
import com.itomkinas.friendStalker.ui.template.BasePage;
import com.itomkinas.friendStalker.ui.utils.FriendStalkerSession;
import com.itomkinas.friendStalker.ui.utils.StaticImage;

public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	@SpringBean
	UserService userService;
	
	private UserEntity loggedInUser;
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		loggedInUser = userService.loadByUid(FriendStalkerSession.get().getUser().getUid());
		
		add(initLabel("name", FriendStalkerSession.get().getUser().getFullName()));
		add(initFriendList(FriendStalkerSession.get().getUser()));
	}
	
	public Label initLabel(String wicketId, String value) {
		return new Label(wicketId, Model.of(value));
	}
	
	@SuppressWarnings("rawtypes")
	public ListView initFriendList(UserEntity userEntity) {

		List<UserEntity> friendList = new ArrayList<UserEntity>(loggedInUser.getFriends());
		
		@SuppressWarnings("unchecked")
		ListView friendListListView = new ListView("friendList", friendList) {
			private static final long serialVersionUID = 1L;

			@Override
		    protected void populateItem(ListItem item) {
		        UserEntity friend = (UserEntity) item.getModelObject();
		        
		        item.add(new Label("id", (parseLong(item.getId()) + 1) + "" ));
		        item.add(new StaticImage("image", new Model<String>("http://graph.facebook.com/"+ friend.getUid()  +"/picture")));
		        item.add(new Label("name", friend.getFullName()));
		        item.add(initStalkButton("stalkButton", friend, loggedInUser));
		    }
		};
		
		return friendListListView;
	}
	
	public AjaxFallbackLink<Void> initStalkButton(String wicketId, final UserEntity friend, final UserEntity currentUser) {
		AjaxFallbackLink<Void> button = new AjaxFallbackLink<Void>(wicketId){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				UserEntity currentUser = userService.loadByUid(FriendStalkerSession.get().getUser().getUid());
				currentUser.getVictims().add(userService.loadByUid(friend.getUid()));
 				this.setVisible(false);
				target.add(this);
				userService.merge(currentUser);
			}
		};
		button.setVisible(!loggedInUser.getVictims().contains(friend));
		return button;
	}
	
	public Long parseLong(String number) {
		return Long.parseLong(number);
	}
}
