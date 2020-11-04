package com.ftl.demo.service;

import com.alicp.jetcache.anno.*;
import com.ftl.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    /**
     * 用于模拟数据库的Map
     */
    @Autowired
    private Map<Long, User> userMap;

    /**
     * 创建一个user 会添加一个user到缓存中
     *
     * @param user
     * @return
     */
    // @CacheRefresh 注解可以设置自动刷新，用于同步各个服务节点内的本地缓存
    @CacheRefresh(refresh = 5, timeUnit = TimeUnit.SECONDS)
    @Cached(name = "demo.user.", key = "#user.id", cacheType = CacheType.BOTH, expire = 60)
    public User create(User user) {
        userMap.put(user.getId(), user);
        return user;
    }

    /**
     * 通过Id查找user
     * 查找顺序： 本地缓存 -> redis -> 数据库
     *
     * @param id
     * @return
     */
    @Cached(name = "demo.user.", key = "#id", cacheType = CacheType.BOTH, expire = 60)
    public User findById(Long id) {
        return userMap.get(id);
    }

    /**
     * 通过Id查找全部的user
     * 查找顺序： 本地缓存 -> redis -> 数据库
     *
     * @return
     */
    @Cached(name = "demo.user", expire = 60, cacheType = CacheType.BOTH)
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        return new ArrayList<>(userMap.values());
    }

    /**
     * 更新user 会同步对本地缓存 redis 进行操作
     *
     * @param user
     */
    @CacheUpdate(name = "demo.user.", key = "#user.id", value = "#user")
    public void update(User user) {
        userMap.put(user.getId(), user);
    }

    /**
     * 通过Id删除 会同步对本地缓存 redis 进行操作
     *
     * @param id
     */
    @CacheInvalidate(name = "demo.user.", key = "#id")
    public void delete(Long id) {
        userMap.remove(id);
    }

    /**
     * 尝试将map扔进redis
     * 遇到FastJson反序列化问题
     * 解决办法是：不要将map直接扔进去 封装一个JavaBean扔进去
     *
     * @return
     */
    @Cached(name = "demo.user")
    public Map<Long, User> getUserMap() {
        return userMap;
    }

}
