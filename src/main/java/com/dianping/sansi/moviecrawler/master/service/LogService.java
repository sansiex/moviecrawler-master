package com.dianping.sansi.moviecrawler.master.service;

import com.dianping.sansi.moviecrawler.master.dao.LogEntryDao;
import com.dianping.sansi.moviecrawler.master.model.LogEntry;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

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

    public List<LogEntry> findByRange(Date start, Date end){
        DetachedCriteria dc=DetachedCriteria.forClass(LogEntry.class);
        dc.add(Restrictions.between("timestamp",start,end));
        return logEntryDao.findByCriteria(dc);
    }
}
