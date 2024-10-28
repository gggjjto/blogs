package com.gd.blink;

import com.gd.tool.mybatisconfig.EnableMybatisPlusIPage;
import com.gd.web.advice.EnableDefaultExceptionAdvice;
import com.gd.web.advice.EnableDefaultResponseAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableMybatisPlusIPage
@EnableDefaultExceptionAdvice
@EnableDefaultResponseAdvice
@EnableFeignClients(basePackages = {"com.gd.user.client"})
public class BlinkServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlinkServerApplication.class, args);
    }

}
