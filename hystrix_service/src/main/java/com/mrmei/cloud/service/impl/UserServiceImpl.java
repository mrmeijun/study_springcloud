package com.mrmei.cloud.service.impl;

import com.mrmei.cloud.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import utils.CommonResult;

import javax.annotation.Resource;

/**
 * @Author mrmei
 * @create 2021/8/4 11:32
 * @Description
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @Resource
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public CommonResult getUser(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/1", CommonResult.class, id);
    }

    @HystrixCommand(fallbackMethod = "getDefaultUser1",ignoreExceptions = {NullPointerException.class})
    @Override
    public CommonResult getUserException(Long id) {
        if(id==1){
            throw new NullPointerException();
        }else if(id==2){
            throw new IndexOutOfBoundsException();
        }
        return restTemplate.getForObject(userServiceUrl + "/user/1", CommonResult.class, id);
    }

    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultUser", commandKey = "getUserCache")
    @Override
    public CommonResult getUserCache(Long id) {
        log.info("getUserCache id:{}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/1", CommonResult.class, id);
    }
    @CacheRemove(commandKey = "getUserCache", cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    @Override
    public CommonResult removeCache(Long id) {
        log.info("removeCache id:{}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/1", CommonResult.class, id);
    }

    /**
     * 为缓存生成key的方法
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }

    public CommonResult getDefaultUser(@PathVariable Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    public CommonResult getDefaultUser1(@PathVariable Long id) {
        User defaultUser = new User(-1L, "defaultUser1", "123456");
        return new CommonResult<>(defaultUser);
    }
}
