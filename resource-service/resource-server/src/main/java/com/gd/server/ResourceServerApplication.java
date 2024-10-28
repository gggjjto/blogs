package com.gd.server;

import com.gd.web.advice.EnableDefaultExceptionAdvice;
import com.gd.web.advice.EnableDefaultResponseAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDefaultExceptionAdvice
@EnableDefaultResponseAdvice
@SpringBootApplication
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

}
