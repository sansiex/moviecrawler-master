package com.dianping.sansi.moviecrawler.master.action;

import com.dianping.sansi.moviecrawler.master.model.LogEntry;
import com.dianping.sansi.moviecrawler.master.service.LogService;
import com.dianping.sansi.moviecrawler.master.utils.ActionHelper;
import com.dianping.sansi.moviecrawler.master.utils.DateUtils;
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
public class LogAction extends ActionSupport implements ServletRequestAware {
    private boolean success = true;

    private HttpServletRequest request;

    private String content;
    private Integer slaveId;
    private Date start;
    private Date end;

    List<LogEntry> list;

    @Autowired
    private LogService logService;

    public String write(){
        LogEntry ent=new LogEntry();
        ent.setTimestamp(new Date());
        ent.setSrc(slaveId);
        ent.setContent(content);
        String ip= ActionHelper.getRemoteAddress(request);
        ent.setIp(ip);
        logService.save(ent);
        return SUCCESS;
    }

    public String load(){
        list=logService.findByRange(start,end);
        return SUCCESS;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSlaveId(Integer slaveId) {
        this.slaveId = slaveId;
    }

    public void setEnd(String end) {
        this.end = DateUtils.string2Date(end,"yyyy/MM/dd HH:mm:ss");
    }

    public void setStart(String start) {
        this.start = DateUtils.string2Date(start,"yyyy/MM/dd HH:mm:ss");;
    }

    @JSON(name="success")
    public boolean isSuccess() {
        return success;
    }

    @JSON(name="logList")
    public List<LogEntry> getList() {
        return list;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
