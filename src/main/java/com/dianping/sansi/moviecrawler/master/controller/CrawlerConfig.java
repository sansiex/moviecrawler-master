package com.dianping.sansi.moviecrawler.master.controller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;

/**
 * Created by sansi on 2014/5/9.
 */
public class CrawlerConfig {
    private CrawlerConfig(){init();}
    private static CrawlerConfig instance=new CrawlerConfig();

    public static CrawlerConfig getInstance() {
        return instance;
    }

    private static final URL FILEPATH=CrawlerConfig.class.getResource("/crawler-master.xml");

    public int size=-1;

    private void init(){
        Document doc=null;
        SAXReader reader=new SAXReader();
        try {
            URL fp=CrawlerConfig.class.getResource("/crawler-master.xml");
            System.out.println(fp.getPath());
            doc=reader.read(fp);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        parseDocument(doc);
    }

    private void parseDocument(Document doc){
        Element root=doc.getRootElement();
        Element slaves=root.element("slaves");
        size=slaves.elements("slave").size();
    }
}
