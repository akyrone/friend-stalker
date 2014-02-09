package com.itomkinas.friendStalker.ui.user;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.itomkinas.friendStalker.domain.entity.UserEntity;
import com.itomkinas.friendStalker.domain.service.UserService;
import com.itomkinas.friendStalker.ui.utils.FacebookOAuth;
import com.itomkinas.friendStalker.ui.utils.FriendStalkerSession;
import com.restfb.FacebookClient.AccessToken;

public class LoginPage extends WebPage {

	private static final long serialVersionUID = 1L;

	@SpringBean
	UserService userService;

	protected void onInitialize() {
		super.onInitialize();
		
		if (FriendStalkerSession.get().isAuthenticated()) {
			setResponsePage(HomePage.class);
			return;
		}
		
		add(initFbLoginLink("login"));
		loginWithFacebook();
	}
	
	private AccessToken getFacebookToken() {
		StringValue code = RequestCycle.get().getRequest()
				.getRequestParameters().getParameterValue("code");
		if (!code.isEmpty()) {
			return FacebookOAuth.getUser(code);
		}
		return null;
	}

	private void loginWithFacebook() {
		AccessToken token = getFacebookToken();
		if (token != null) {
			UserEntity loggedUser = userService.login(token);
			FriendStalkerSession.get().setUser(loggedUser);
			userService.saveFriends(loggedUser);
			setResponsePage(HomePage.class);
		}
	}

	private Link<Object> initFbLoginLink(String wicketId) {
		return new Link<Object>(wicketId) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				throw new RedirectToUrlException(FacebookOAuth.getRedirectURL());
			}
		};
	}
}
