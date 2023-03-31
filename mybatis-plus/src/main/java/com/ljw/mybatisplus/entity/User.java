package com.ljw.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体
 *
 * @author Luo
 * @date 2022/11/25
 */
@Data
@TableName("user")
public class User {

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)        //插入时，更新时间
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //插入更新时，更新时间
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private int version;

    //逻辑删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

}
