package com.jiangxia.mybatisplusdemo.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author jiangxia
 * @date 2022年02月02日 18:10
 * desc：user实体类
 */
@Data
public class User {

    //@TableId(type = IdType.ID_WORKER) //mybatisplus自带策略，生成19位值，数字类型使用这种策略，比如long
    //@TableId(type = IdType.ID_WORKER_STR) //mybatisplus自带策略，生成19位值，字符串类型使用这种策略
    // @TableId(type = IdType.AUTO)  //自动增长策略
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //create_time
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    //update_time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version; //版本号

    //逻辑删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
