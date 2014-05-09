package com.dianping.sansi.moviecrawler.master.action;

import com.dianping.sansi.moviecrawler.master.model.LogEntry;
import com.dianping.sansi.moviecrawler.master.service.LogService;
import com.dianping.sansi.moviecrawler.master.utils.ActionHelper;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by sansi on 2014/5/9.
 */
public class ControllerAction extends ActionSupport implements ServletRequestAware {
    private boolean success = true;

    private HttpServletRequest request;

    private Integer slaveId;
    private Long lastFetchedId;

    @Autowired
    private LogService logService;

    public String connect(){



        return SUCCESS;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @JSON(name="success")
    public boolean isSuccess() {
        return success;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
