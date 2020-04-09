package com.springcloud.controller;

import com.springcloud.pojo.Dept;
import com.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController = @Controller + @ResponseBody组成,
// @RestController注解Controller，则Controller中的方法无法返回jsp页面，
//这里没有前端页面使用@RestController就够了
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("dept/add")
    public boolean addDept(@RequestBody Dept dept) {
        return  deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptService.queryId(id);
    }

    //http://127.0.0.1:8001/dept/list
    @GetMapping("/dept/list")
    public List<Dept> queryAll(Dept dept) {
        System.out.println(deptService.queryAll());
        return deptService.queryAll();
    }

    //获取一些配置信息，得到具体的微服务
    @Autowired
    private DiscoveryClient client;

    @GetMapping("/dept/discovery")
    //注册进来的微服务，获取一些信息
    public Object discovery() {
        //获得微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>services" + services);

        //得到一个具体的微服务信息
        List<ServiceInstance> instance = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance serviceInstance : instance) {
            System.out.println(
                    serviceInstance.getHost()+"\t"+
                            serviceInstance.getPort()+"\t"+
                            serviceInstance.getUri()+"\t"+
                            serviceInstance.getServiceId()
            );
        }
        return  this.client;
    }
}
