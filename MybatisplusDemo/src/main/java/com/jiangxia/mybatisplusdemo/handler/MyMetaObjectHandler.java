package com.jiangxia.mybatisplusdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author jiangxia
 * @date 2022年02月03日 15:29
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //使用mp的添加操作，则执行这个方法
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        //版本号表示字段
        this.setFieldValByName("version", 1, metaObject);
        //用于逻辑删除字段
        this.setFieldValByName("deleted", 0, metaObject);
    }

    //使用mp的更新操作，则这个方法执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
