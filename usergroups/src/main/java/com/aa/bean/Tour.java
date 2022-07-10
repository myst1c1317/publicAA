package com.aa.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tour")
public class Tour {
    /**
     * 活动编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 队长编号
     */
    @TableField(value = "cid")
    private Integer cid;

    /**
     * 地点
     */
    @TableField(value = "location")
    private String location;

    /**
     * 主要内容
     */
    @TableField(value = "issue")
    private String issue;

    /**
     * 时间通告
     */
    @TableField(value = "time")
    private String time;

    /**
     * 开始时间
     */
    @TableField(value = "ftime")
    private Date ftime;

    /**
     * 共付金额
     */
    @TableField(value = "money")
    private Integer money;
}