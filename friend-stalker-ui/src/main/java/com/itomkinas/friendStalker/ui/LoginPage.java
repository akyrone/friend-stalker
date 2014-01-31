package com.itomkinas.friendStalker.ui;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.util.string.StringValue;

import com.itomkinas.friendStalker.ui.utils.FacebookClient;

public class LoginPage extends BasePage {

	private static final long serialVersionUID = 1L;

	protected void onInitialize() {
		super.onInitialize();
		
		add(initFbLoginLink("login"));
		loginWithFacebook();
	}

	private void loginWithFacebook() {
		StringValue code = RequestCycle.get().getRequest().getRequestParameters().getParameterValue("code");
		if (!code.isEmpty()) {
			com.face4j.facebook.entity.User fbUser = FacebookClient.getUser(code);
			if (fbUser != null) {
				//TODO: mhm, cookies
				//Maybe implement shiro security?
				System.out.println(fbUser.getId());
			} else {
				System.out.println("TOO BAD");
			}
		}
	}
	
    private Link<Object> initFbLoginLink(String wicketId) {
        return new Link<Object>(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                throw new RedirectToUrlException(
                        FacebookClient.getRedirectURL());
            }
        };
    }


}
