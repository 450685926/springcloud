package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.pojo.Dept;
import com.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController = @Controller + @ResponseBody组成,
// @RestController注解Controller，则Controller中的方法无法返回jsp页面，
//这里没有前端页面使用@RestController就够了
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //http://127.0.0.1:8001/dept/get/2
    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.queryId(id);
        if (dept == null) {
            throw new RuntimeException("用户不存在");
        }
        return dept;
    }


    public Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("用户不存在").setDb_source("");
    }
}
