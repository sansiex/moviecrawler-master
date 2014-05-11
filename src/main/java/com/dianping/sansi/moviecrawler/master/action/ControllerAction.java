package com.dianping.sansi.moviecrawler.master.action;

import com.dianping.sansi.moviecrawler.master.controller.CrawlerScheduler;
import com.dianping.sansi.moviecrawler.master.model.LogEntry;
import com.dianping.sansi.moviecrawler.master.service.LogService;
import com.dianping.sansi.moviecrawler.master.utils.ActionHelper;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sansi on 2014/5/9.
 */
public class ControllerAction extends ActionSupport implements ServletRequestAware {
    private boolean success = true;
    private HashMap<String,String> params=new HashMap<>();

    private HttpServletRequest request;

    private int slaveId;
    private Long lastFetchedId;

    @Autowired
    private LogService logService;

    public String connect(){
        System.out.println("connect:slaveId="+slaveId+"&lastFetchedId="+lastFetchedId);
        String ip=ActionHelper.getRemoteAddress(request);
        CrawlerScheduler cs=CrawlerScheduler.getInstance();
        int id=cs.connect(slaveId,lastFetchedId,ip);
        params=new HashMap<>();
        params.put("slaveId",String.valueOf(id));
        return SUCCESS;
    }

    public String touch(){
        CrawlerScheduler cs=CrawlerScheduler.getInstance();
        params=cs.touch(slaveId);
        return SUCCESS;
    }

    public String finish(){
        System.out.println("finish:slaveId="+slaveId+"&lastFetchedId="+lastFetchedId);
        CrawlerScheduler cs=CrawlerScheduler.getInstance();
        params= cs.finish(slaveId);
        return SUCCESS;
    }

    public String startTask(){
        CrawlerScheduler cs=CrawlerScheduler.getInstance();
        params=cs.startTask();
        return SUCCESS;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @JSON(name="success")
    public boolean isSuccess() {
        return success;
    }

    @JSON(name="params")
    public HashMap<String, String> getParams() {
        return params;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }

    public void setLastFetchedId(Long lastFetchedId) {
        this.lastFetchedId = lastFetchedId;
    }
}
