package com.dianping.sansi.moviecrawler.master.controller;

import java.util.Date;

/**
 * Created by sansi on 2014/5/9.
 */
public class Crawler {
    public int id;
    public String ip;
    public int status;//running or stop
    public int task;//running what task
    public long lastFetchedId;
    public int taskPartition;
    public Date lastTouch;

    public static final int STATUS_NEW=0;
    public static final int STATUS_RUNNING=1;
    public static final int STATUS_WAIT=2;
    public static final int STATUS_DISCONNECT=3;

    public static final int TASK_IDLE=0;
    public static final int TASK_CINEMA=1;
    public static final int TASK_SCHEDULE=2;
    public static final int TASK_FINISH=3;
}
