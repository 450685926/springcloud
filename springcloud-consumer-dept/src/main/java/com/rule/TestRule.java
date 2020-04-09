package com.rule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestRule {
    @Bean
    public IRule rule() {
        return new RandomRuleNew();//默认是轮询，现在改为自定义
    }
}
