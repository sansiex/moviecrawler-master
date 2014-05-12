package com.dianping.sansi.moviecrawler.master.service;

/**
 * Created by sansi on 2014/5/9.
 */
public class LogManager {
    private long lastRead=-1;

    LogService logService;

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
