package com.cooper.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan({"com.cooper.Mapper","com.cooper.learn.mapper"})
@EnableTransactionManagement //开启事务  默认是开启的
public class MybatisConfig {

    //开启乐观锁配置
    @Bean
    OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    //开启分页配置
    @Bean
    PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    //开启逻辑删除
    @Bean
    ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }

    //SQL执行效率插件
    @Bean
    @Profile({"dev","test"})
    PerformanceInterceptor performanceInterceptor(){
        //设置了最大耗时时间为100ms（超过该时间的SQL语句将放弃返回最终的结果）,SQL语句输出格式化
        return new PerformanceInterceptor().setMaxTime(100).setFormat(true);
    }
}
