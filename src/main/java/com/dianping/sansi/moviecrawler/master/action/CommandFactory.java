package com.dianping.sansi.moviecrawler.master.action;

import com.dianping.sansi.moviecrawler.master.controller.Crawler;
import com.dianping.sansi.moviecrawler.master.controller.CrawlerScheduler;

import java.util.HashMap;

/**
 * Created by sansi on 2014/5/10.
 */
public class CommandFactory {
    public static final int TYPE_EMPTY=0;
    public static final int TYPE_START_TASK=1;
    public static final int TYPE_WAIT=2;

    public static HashMap<String,String> createCommand(int type, Crawler crawler, CrawlerScheduler cs){
        HashMap<String,String> params=new HashMap<>();
        params.put("type",String.valueOf(type));
        switch (type){
            case TYPE_START_TASK:{
                params.put("task",String.valueOf(cs.currentTask));
                params.put("taskSize",String.valueOf(cs.taskSize));
                params.put("taskPartition",String.valueOf(crawler.taskPartition));
                params.put("start","0");
                break;
            }
            case TYPE_WAIT:{
                break;
            }
            case TYPE_EMPTY:{
                break;
            }
        }
        return params;
    }
}
