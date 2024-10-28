package com.gd.content;

import com.gd.redis.autoconfig.EnableRedisSerialize;
import com.gd.web.advice.EnableDefaultExceptionAdvice;
import com.gd.web.advice.EnableDefaultResponseAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableRedisSerialize
@EnableFeignClients(basePackages = "com.gd.user.client")
@SpringBootApplication
@EnableDefaultExceptionAdvice
@EnableDefaultResponseAdvice
@EnableElasticsearchRepositories(basePackages = "com.gd.content.mapper")
public class BlogContentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogContentServerApplication.class, args);
    }

}
