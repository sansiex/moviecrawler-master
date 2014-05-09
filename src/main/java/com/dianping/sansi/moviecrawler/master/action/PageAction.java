package com.dianping.sansi.moviecrawler.master.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by sansi on 2014/5/9.
 */
public class PageAction extends ActionSupport {
    private String page;

    private String forward;

    private final String REDIRECT="redirect";

    public String redirect(){
        if(page!=null){
            forward=page;
        }
        return REDIRECT;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }
}
