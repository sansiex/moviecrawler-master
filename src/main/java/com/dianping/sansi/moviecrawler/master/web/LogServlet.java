package com.dianping.sansi.moviecrawler.master.web;

import com.dianping.sansi.moviecrawler.master.model.LogEntry;
import com.dianping.sansi.moviecrawler.master.service.LogService;
import com.dianping.sansi.moviecrawler.master.utils.ServletHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by sansi on 2014/5/9.
 */
public class LogServlet extends HttpServlet {

    LogService logService;

    public void setLogService(LogService logService){
        this.logService=logService;
    }

    private static long lastRead=-1;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String content=req.getParameter("content");
        Integer src=Integer.parseInt(req.getParameter("slaveId"));
        String ip= ServletHelper.getRemoteAddress(req);
        Date date=new Date();
        LogEntry ent=new LogEntry();
        ent.setContent(content);
        ent.setSrc(src);
        ent.setIp(ip);
        ent.setTimestamp(date);
        Long id=logService.save(ent);

        HashMap<String,Object> ret=new HashMap<>();
        ret.put("code",0);
        ret.put("id",id);
        ServletHelper.responseJson(resp,ret);
    }
}
