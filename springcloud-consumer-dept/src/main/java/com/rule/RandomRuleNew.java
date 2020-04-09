package com.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomRuleNew extends AbstractLoadBalancerRule {
    // 每个服务访问5次，调用下一个
    private int total = 0;//被调用的次数

    private int currentIndex = 0;// 当前调用的服务

    public RandomRuleNew() {
    }

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                // 获得活着的服务
                List<Server> upList = lb.getReachableServers();
                // 获得全部的服务
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

//                int index = this.chooseRandomInt(serverCount);
//                server = (Server)upList.get(index);

                if (total < 4) {
                    total++;
                } else {
                    total = 0;
                    currentIndex++;
                    if (currentIndex > upList.size() - 1) {
                        currentIndex = 0;
                    }
                }
                System.out.println(total+ "---" + currentIndex);
                server = upList.get(currentIndex);

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
