package com.itomkinas.friendStalker.ui.utils;

import com.restfb.DefaultFacebookClient;

public class LoggedInFacebookClient extends DefaultFacebookClient {

    public LoggedInFacebookClient(String appId, String appSecret) {
        AccessToken accessToken = this.obtainAppAccessToken(appId, appSecret);
        this.accessToken = accessToken.getAccessToken();
    }
    
    public String getAccessToken() {
    	return this.accessToken;
    }

}
