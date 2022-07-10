package com.aa.bean.vo;


import java.util.Date;

public class TourVo {

    private Integer id;

    /**
     * 队长编号
     */
    private Integer cid;

    /**
     * 队长姓名
     */
    private String name;
    /**
     * 地点
     */
    private String location;

    /**
     * 主要内容
     */
    private String issue;

    /**
     * 时间通告
     */
    private String time;

    /**
     * 开始时间
     */
    private Date ftime;

    /**
     * 共付金额
     */
    private Integer money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getFtime() {
        return ftime;
    }

    public void setFtime(Date ftime) {
        this.ftime = ftime;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }


}
