package com.ftl.demo;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.ftl.demo.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@EnableMethodCache(basePackages = "com.ftl.demo")
@EnableCreateCacheAnnotation
@SpringBootApplication
public class JetCacheDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JetCacheDemoApplication.class);
    }

    /**
     * 模拟一个数据库
     *
     * @return
     */
    @Bean
    public Map<Long, User> userMap() {
        return new HashMap<>();
    }
}
