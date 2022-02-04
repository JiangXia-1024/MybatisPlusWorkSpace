package com.jiangxia.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxia.mybatisplusdemo.bean.User;
import org.springframework.stereotype.Repository;

//需要继承mybatisplus的basemapper接口，并且泛型类型是实体类
@Repository
public interface UserMapper extends BaseMapper<User> {
}
