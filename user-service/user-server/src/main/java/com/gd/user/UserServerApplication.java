package com.gd.user;

import com.gd.redis.autoconfig.EnableRedisSerialize;
import com.gd.tool.mybatisconfig.EnableMybatisPlusIPage;
import com.gd.web.advice.EnableDefaultExceptionAdvice;
import com.gd.web.advice.EnableDefaultResponseAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/9/30
 */
@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
@SpringBootApplication
@EnableAsync // 开启异步控制
@EnableFeignClients(basePackages = {"com.gd.clientResource", "com.gd.blog.client"}) // 开启feign
@EnableMybatisPlusIPage // 开启mybatis分页助手
@EnableRedisSerialize // 开启RedisTemplate序列化配置
@EnableScheduling // 开启定时任务
@EnableDefaultExceptionAdvice // 注入默认异常处理器
@EnableDefaultResponseAdvice // 注入默认包装器
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }
}
