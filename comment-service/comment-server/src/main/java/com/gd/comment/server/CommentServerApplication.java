package com.gd.comment.server;

import com.gd.tool.mybatisconfig.EnableMybatisPlusIPage;
import com.gd.web.advice.EnableDefaultExceptionAdvice;
import com.gd.web.advice.EnableDefaultResponseAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableMybatisPlusIPage
@EnableFeignClients(basePackages = {"com.gd.blog.client", "com.gd.user.client"})
@EnableDefaultExceptionAdvice
@EnableDefaultResponseAdvice
public class CommentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentServerApplication.class, args);
    }

}
