package com.dianping.sansi.moviecrawler.master.controller;

import java.util.Date;

/**
 * Created by sansi on 2014/5/9.
 */
public class Crawler {
    public int id;
    public String ip;
    public int status;//running or stop
    public int mission;//scraping what data
    public long lastFetchedId;
    public Date lastTouch;

    public static final int STATUS_NEW=0;
    public static final int STATUS_RUNNING=1;
    public static final int STATUS_WAIT=2;
    public static final int STATUS_DISCONNECT=3;

    public static final int MISSION_IDLE=-1;
    public static final int MISSION_CINEMA=1;
    public static final int MISSION_SCHEDULE=2;
    public static final int MISSION_FINISH=3;
}
