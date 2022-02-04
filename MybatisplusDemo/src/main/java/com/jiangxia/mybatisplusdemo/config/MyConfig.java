package com.jiangxia.mybatisplusdemo.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author jiangxia
 * @date 2022年02月03日 22:22
 */
@Configuration
@MapperScan("com.jiangxia.mybatisplusdemo.mapper")
public class MyConfig {

    //乐观锁组件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    //mp分页组件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    //mp逻辑删除组件

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 
     * @author jiangxia
     * @date 2022/2/4 11:45
     * @param: No such property: code for class: Script1
     * @return No such property: code for class: Script1
     * @description:sql执行性能分析组件，推荐开发环境使用，线上不推荐
     * maxtime值得是sql最大执行时长
     * 一般有三种运行环境：
     * test：测试环境
     * dev：开发环境
     * prod：生成环境
     */
    @Bean
    @Profile({"dev","dev"})//设置开发环境开启
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //ms，超过此处设置的ms则sql不执行
        performanceInterceptor.setMaxTime(100);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
