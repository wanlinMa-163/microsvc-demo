package com.xxx.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.xxx.commondatamodel", "com.xxx.base"})
public class BaseMain {

    public static void main(String[] args) {
        SpringApplication.run(BaseMain.class,args);
    }
}
