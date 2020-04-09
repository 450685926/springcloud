package com.springcloud.service;

import com.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {

    public boolean addDept(Dept dept);

    public Dept queryId(Long id);

    public List<Dept> queryAll();
}
