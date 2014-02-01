package com.itomkinas.friendStalker.ui.user;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.itomkinas.friendStalker.domain.entity.UserEntity;
import com.itomkinas.friendStalker.domain.service.UserService;
import com.itomkinas.friendStalker.ui.HomePage;
import com.itomkinas.friendStalker.ui.utils.FacebookOAuth;
import com.itomkinas.friendStalker.ui.utils.FriendStalkerSession;

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

	private UserEntity getFacebookUser() {
		StringValue code = RequestCycle.get().getRequest()
				.getRequestParameters().getParameterValue("code");
		if (!code.isEmpty()) {
			return FacebookOAuth.getUser(code);
		}
		return null;
	}
	
	private void loginWithFacebook() {
		UserEntity user = getFacebookUser();
		if (user != null) {
			userService.merge(user);
			FriendStalkerSession.get().setUser(user);
			setResponsePage(HomePage.class);
		}
	}

	private Link<Object> initFbLoginLink(String wicketId) {
		return new Link<Object>(wicketId) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				throw new RedirectToUrlException(
						FacebookOAuth.getRedirectURL());
			}
		};
	}
}