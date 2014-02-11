package com.itomkinas.friendStalker.ui.utils;

import org.apache.wicket.util.string.StringValue;

import com.face4j.facebook.Client;
import com.face4j.facebook.OAuthAccessToken;
import com.face4j.facebook.enums.Display;
import com.face4j.facebook.enums.Permission;
import com.face4j.facebook.exception.FacebookException;
import com.face4j.facebook.factory.FacebookFactory;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient.AccessToken;

public final class FacebookOAuth {
	private static final String APP_ID = "478714635571704";
	private static final String APP_SECRET = "61f12f7eb0638812dd08600831e171a7";
	private static final String CALLBACK_URL = "http://localhost:8080/login";
	private static final FacebookFactory FACEBOOK_FACTORY = new FacebookFactory(
			new Client(APP_ID, APP_SECRET));

	private FacebookOAuth() {
	}

	public static String getRedirectURL() {
		return FACEBOOK_FACTORY.getRedirectURL(CALLBACK_URL, Display.PAGE,
				Permission.EMAIL, Permission.OFFLINE_ACCESS,
				Permission.FRIENDS_ONLINE_PRESENCE,
				Permission.USER_ONLINE_PRESENCE);
	}

	private static OAuthAccessToken getOAuthAccessToken(StringValue c)
			throws FacebookException {
		String code = c.toString();
		return FACEBOOK_FACTORY.getOAuthAccessToken(code, CALLBACK_URL);
	}

	public static AccessToken getUser(StringValue code) {
		try {
			OAuthAccessToken token = getOAuthAccessToken(code);

			AccessToken longTermToken = new DefaultFacebookClient()
					.obtainExtendedAccessToken(APP_ID, APP_SECRET,
							token.getAccessToken());
			return longTermToken;
		} catch (FacebookException e) {
			return null;
		}
	}
}