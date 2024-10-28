package com.gd.server;

import com.gd.amqp.autoconfig.EnableAmqpMessageConverterConfig;
import com.gd.redis.autoconfig.EnableRedisSerialize;
import com.gd.tool.mybatisconfig.EnableMybatisPlusIPage;
import com.gd.web.advice.EnableDefaultExceptionAdvice;
import com.gd.web.advice.EnableDefaultResponseAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.gd.clientResource", "com.gd.user.client"}) // 开启feign
@EnableMybatisPlusIPage // 开启mybatis分页
@EnableRedisSerialize
@EnableAmqpMessageConverterConfig
@EnableDefaultExceptionAdvice
@EnableDefaultResponseAdvice
public class BlogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogServerApplication.class, args);
    }

}
