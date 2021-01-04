package com.bonc.cloudhandlesql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bonc.cloudhandlesql.dao.mapper")
public class CloudHandleSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudHandleSqlApplication.class, args);
    }

}
