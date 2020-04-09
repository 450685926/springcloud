package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DeptConsumerDashboard {
    //http://127.0.0.1:9001/hystrix
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerDashboard.class, args);
    }
}
