package com.itomkinas.friendStalker.ui;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

import com.itomkinas.friendStalker.ui.user.LoginPage;
import com.itomkinas.friendStalker.ui.utils.FriendStalkerSession;

@Component("wicketApplication")
public class Application extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    public final Session newSession(Request request, Response response) {
        return new FriendStalkerSession(request);
    }

    @Override
    protected void init() {
        super.init();
        
        mountPage("/login", LoginPage.class);
        
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this));
    }
}
