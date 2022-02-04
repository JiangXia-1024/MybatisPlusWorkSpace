package com.jiangxia.mybatisplusdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描mapper接口的包 不加这个注解的话就需要写mapper接口的实现类，否启动会报错，找不到mapper
@MapperScan("com.jiangxia.mybatisplusdemo.mapper")
public class MybatisplusdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusdemoApplication.class, args);
    }

}
