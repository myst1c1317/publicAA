package com.aa.bean.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class TournumberVo {
    /**
     * 成员编号
     */
    private Integer id;

    /**
     * 队伍编号
     */
    private Integer tid;

    /**
     * 队长编号
     */
    private Integer cid;

    /**
     * 队长姓名
     */
    private String cname;
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
     * 用户编号
     */
    private Integer uid;
    /**
     * 姓名
     */
    private String uname;
    /**
     * 用户活动金额
     */
    private Integer money;

    /**
     * 事件备注
     */
    private String remark;

    /**
     * 用户需缴纳金额
     */
    private Integer needmoney;

    /**
     * 个人追加金额
     */
    private Integer elsemoney;

    public Integer getElsemoney() {
        return elsemoney;
    }

    public void setElsemoney(Integer elsemoney) {
        this.elsemoney = elsemoney;
    }

    public Integer getNeedmoney() {
        return needmoney;
    }

    public void setNeedmoney(Integer needmoney) {
        this.needmoney = needmoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
