package com.dianping.sansi.moviecrawler.master.service;

import com.dianping.sansi.moviecrawler.master.dao.LogEntryDao;
import com.dianping.sansi.moviecrawler.master.model.LogEntry;

/**
 * Created by sansi on 2014/5/9.
 */
public class LogService {
    LogEntryDao logEntryDao;

    public void setLogEntryDao(LogEntryDao logEntryDao){
        this.logEntryDao=logEntryDao;
    }

    public Long save(LogEntry ent){
        return logEntryDao.save(ent);
    }
}
