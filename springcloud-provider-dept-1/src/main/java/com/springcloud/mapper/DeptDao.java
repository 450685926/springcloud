package com.springcloud.mapper;

import com.springcloud.pojo.Dept;

import java.util.List;


// 两个注解省略则需要在启动类加@MapperScan
//将dao交给spring管理    不用写mapper.xml了      自动注册一个接口实现类
//@Mapper
//将dao类声明为bean
//@Repository
public interface DeptDao {

    boolean addDept(Dept dept);

    Dept queryId(Long id);

    List<Dept> queryAll();
}