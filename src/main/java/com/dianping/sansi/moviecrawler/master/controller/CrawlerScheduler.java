package com.dianping.sansi.moviecrawler.master.controller;

import com.dianping.sansi.moviecrawler.master.action.CommandFactory;
import com.dianping.sansi.moviecrawler.master.utils.JsonUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by sansi on 2014/5/9.
 */
public class CrawlerScheduler {
    private CrawlerScheduler(){
        size=CrawlerConfig.getInstance().size;
        taskSize=size;
        crawlers=new Crawler[size];
    }
    private static CrawlerScheduler instance=new CrawlerScheduler();
    public static CrawlerScheduler getInstance(){
        return instance;
    }

    public static final int DISCONNECT_TIMEOUT=30*1000;
    public static final int CRUSH_TIMEOUT=600*1000;

    //private CrawlerConfig config;
    public int size=0;
    public int taskSize=0;
    public int currentTask=Crawler.TASK_IDLE;
    public int finish=0;
    private Crawler[] crawlers;

    public int connect(int slaveId,Long lastFetchedId,String ip){
        for(Crawler crawler:crawlers){
            if(crawler!=null && crawler.ip.equals(ip)){
                touch(crawler.id);
                return crawler.id;
            }
        }

        if(slaveId==-1){
            Crawler crawler=createCrawler(ip);
            crawlers[crawler.id]=crawler;
            slaveId=crawler.id;
        }else{
            Crawler crawler=crawlers[slaveId];
            if(crawler==null){
                crawler=createCrawler(ip);
                crawlers[crawler.id]=crawler;
            }else{
                crawler.lastFetchedId=lastFetchedId;
                crawler.id=slaveId;
                crawler.ip=ip;
                touch(crawler.id);
            }
        }
        return slaveId;
    }

    public HashMap<String,String> touch(int id){
        Crawler crawler=crawlers[id];
        //System.out.println("touch:size="+size+"&task="+currentTask+"&finish="+finish+"&c="+JsonUtils.toJson(crawler));
        crawler.lastTouch=new Date();
        if(crawler.status==Crawler.STATUS_DISCONNECT){
            crawler.status=Crawler.STATUS_WAIT;
        }

        HashMap<String,String> params=null;
        if(crawler.status==Crawler.STATUS_WAIT) {
            if (crawler.task != currentTask) {
                params=CommandFactory.createCommand(CommandFactory.TYPE_START_TASK,crawler,this);
                crawler.status=Crawler.STATUS_RUNNING;
                crawler.lastFetchedId=0;
                crawler.task=currentTask;
            }else{
                params=CommandFactory.createCommand(CommandFactory.TYPE_WAIT,crawler,this);
            }
        }else if(crawler.status==Crawler.STATUS_NEW){
            params=finish(crawler.id);
        }else if(crawler.status==Crawler.STATUS_RUNNING){
            params=CommandFactory.createCommand(CommandFactory.TYPE_EMPTY,crawler,this);
        }
        return params;
    }

    public HashMap<String,String> finish(int id){
        Crawler crawler=crawlers[id];
        crawler.lastTouch=new Date();
        crawler.status=Crawler.STATUS_WAIT;
        crawler.lastFetchedId=-1;
        finish++;

        if(finish==taskSize && currentTask!=Crawler.TASK_FINISH){
            currentTask++;
            finish=0;
            int prt=0;
            int ts=0;
            for(Crawler c:crawlers){
                c.lastFetchedId=-1;
                if(c.status==Crawler.STATUS_WAIT){
                    ts++;
                    c.taskPartition=prt;
                    prt++;
                }
                taskSize=ts;
            }
        }

        return CommandFactory.createCommand(CommandFactory.TYPE_WAIT,crawler,this);
    }
    
    private Crawler createCrawler(String ip){
        for (int i = 0; i <size; i++) {
            if(crawlers[i]==null){
                Crawler crawler=new Crawler();
                crawler.id=i;
                crawler.ip=ip;
                crawler.lastFetchedId=-1;
                crawler.task=Crawler.TASK_IDLE;
                crawler.status=Crawler.STATUS_NEW;
                crawler.lastTouch=new Date();

                return crawler;
            }
        }
        return null;
    }
}
