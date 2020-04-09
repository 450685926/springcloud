package com.springcloud;

import com.rule.TestRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

//Ribbon 和 Eureka 整合以后,客户端可以直接调用, 不用关心ip地址和端口号
@SpringBootApplication
@EnableEurekaClient
// 此注解如果没有配置，则默认是轮询，配置则按配置后的规则调用服务
@RibbonClient(name = "springcloud-provider-dept", configuration = TestRule.class)
public class DeptConsumer {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer.class, args);
    }
}
