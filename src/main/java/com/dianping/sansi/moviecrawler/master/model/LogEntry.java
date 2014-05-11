package com.dianping.sansi.moviecrawler.master.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sansi on 2014/5/6.
 */

@Entity
@Table(name = "Zuhai_LogEntry")
public class LogEntry {
    private Long id;
    private String content;
    private int src;
    private String ip;
    private Date timestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "content", length=500)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "src")
    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "timestamp")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
