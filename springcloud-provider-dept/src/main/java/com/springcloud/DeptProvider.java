package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//启动类
@SpringBootApplication
//如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient
// 如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。
//@EnableEurekaClient
// 在服务启动之后自动注册到eureka中，也可以获取服务(服务注册发现)
@EnableDiscoveryClient
@MapperScan("com.springcloud.mapper")
public class DeptProvider {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider.class, args);
    }
}
