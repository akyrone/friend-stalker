package com.itomkinas.friendStalker.ui.utils;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.itomkinas.friendStalker.domain.entity.UserEntity;

public class FriendStalkerSession extends WebSession {
	private static final long serialVersionUID = 1L;
	private UserEntity user;
	private Class<? extends IRequestablePage> cachedPageClassToLoad;
	private PageParameters cachedPagePageParameters;

	public FriendStalkerSession(Request request) {
		super(request);
	}

	public static FriendStalkerSession get() {
		return (FriendStalkerSession) Session.get();
	}

	public UserEntity getUser() {
		return user;
	}

	public boolean isAuthenticated() {
		return (user != null);
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public void setCachedPage(Class<? extends IRequestablePage> cachedPage,
			PageParameters cachedPagePageParameters) {
		this.cachedPageClassToLoad = cachedPage;
		this.cachedPagePageParameters = cachedPagePageParameters;
	}

	public Class<? extends IRequestablePage> getCachedPageClass() {
		return cachedPageClassToLoad;
	}

	public PageParameters getCachedPageParameters() {
		return cachedPagePageParameters;
	}
}