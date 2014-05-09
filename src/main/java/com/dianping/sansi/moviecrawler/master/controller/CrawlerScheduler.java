package com.dianping.sansi.moviecrawler.master.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by sansi on 2014/5/9.
 */
public class CrawlerScheduler {
    private CrawlerScheduler(){
        size=config.size;
    }
    private static CrawlerScheduler instance=null;
    public static CrawlerScheduler getInstance(){
        if (instance == null) {
            instance=new CrawlerScheduler();
        }
        return instance;
    }

    public static final int DISCONNECT_TIMEOUT=30*1000;
    public static final int CRUSH_TIMEOUT=600*1000;

    private CrawlerConfig config=CrawlerConfig.getInstance();
    private int size=0;
    private int mission=Crawler.MISSION_IDLE;
    private int finish=0;
    private HashMap<Integer,Crawler> crawlerMap=new HashMap<>();

    public void connect(int slaveId,Long lastFetchedId,String ip){
        if(slaveId==-1){
            Crawler crawler=createCrawler(ip);
            crawlerMap.put(crawler.id,crawler);
        }else{
            Crawler crawler=crawlerMap.get(slaveId);
            if(crawler==null){
                crawler=createCrawler(ip);
                crawlerMap.put(crawler.id,crawler);
            }else{
                crawler.lastFetchedId=lastFetchedId;
                touch(crawler.id);
            }
        }
    }

    public void touch(int id){
        Crawler crawler=crawlerMap.get(id);
        crawler.lastTouch=new Date();
        if(crawler.status==Crawler.STATUS_DISCONNECT){
            crawler.status=Crawler.STATUS_WAIT;
        }
    }

    public void finish(int id){
        Crawler crawler=crawlerMap.get(id);
        crawler.lastTouch=new Date();
        crawler.status=Crawler.STATUS_WAIT;
        crawler.mission=Crawler.MISSION_IDLE;
        crawler.lastFetchedId=-1;
        finish++;

        if(finish==size){
            mission++;
            finish=0;
        }
    }
    
    private Crawler createCrawler(String ip){
        for (int i = 1; i <= size; i++) {
            Crawler crawler=crawlerMap.get(i);
            if(crawler==null){
                crawler=new Crawler();
                crawler.id=i;
                crawler.ip=ip;
                crawler.lastFetchedId=-1;
                crawler.mission=Crawler.MISSION_IDLE;
                crawler.status=Crawler.STATUS_NEW;
                crawler.lastTouch=new Date();

                return crawler;
            }
        }
        return null;
    }
}
