package com.itomkinas.friendStalker.ui;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

@Component("wicketApplication")
public class Application extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return BasePage.class;
    }

    @Override
    protected void init() {
        super.init();
        
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this));
    }
}
