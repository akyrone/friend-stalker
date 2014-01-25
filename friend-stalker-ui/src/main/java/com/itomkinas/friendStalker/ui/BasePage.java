package com.itomkinas.friendStalker.ui;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;

@SuppressWarnings("all") 
public class BasePage extends WebPage {
    private static final long serialVersionUID = 1L;
    private static String absolutePath;

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }

    private void initializeAbsolutePath() {
        HttpServletRequest req = (HttpServletRequest) ((WebRequest) RequestCycle
                .get().getRequest()).getContainerRequest();
        absolutePath = RequestUtils.toAbsolutePath(req.getRequestURL()
                .toString(), "");
    }

    public static String getAbsolutePath() {
        return absolutePath;
    }
}