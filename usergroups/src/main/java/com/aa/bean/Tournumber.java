package com.aa.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tournumber")
public class Tournumber {
    /**
     * 成员编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 队伍编号
     */
    @TableField(value = "tid")
    private Integer tid;

    /**
     * 用户编号
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 用户活动金额
     */
    @TableField(value = "money")
    private Integer money;

    /**
     * 个人追加金额
     */
    @TableField(value = "elsemoney")
    private Integer elsemoney;
}