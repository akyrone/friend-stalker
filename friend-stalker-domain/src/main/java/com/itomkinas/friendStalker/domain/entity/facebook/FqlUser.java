package com.itomkinas.friendStalker.domain.entity.facebook;

import com.restfb.Facebook;

public class FqlUser {

	@Facebook
	String uid;

	@Facebook
	String online_presence;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOnline_presence() {
		return online_presence;
	}

	public void setOnline_presence(String online_presence) {
		this.online_presence = online_presence;
	}

}
