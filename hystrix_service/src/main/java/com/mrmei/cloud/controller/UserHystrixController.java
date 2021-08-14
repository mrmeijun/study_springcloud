package com.mrmei.cloud.controller;

import com.mrmei.cloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.CommonResult;

import javax.annotation.Resource;

/**
 * @Author mrmei
 * @create 2021/8/4 10:59
 * @Description
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping(value ="user")
public class UserHystrixController {

    @Resource
    private UserService userService;

    @GetMapping("/testFallback/{id}")
    public CommonResult testFallback(@PathVariable Long id) {
        CommonResult commonResult = userService.getUser(id);
        log.info("hystrix-service 调用成功！！！");
        return commonResult;
    }

    @GetMapping("/testFallbackByException/{id}")
    public CommonResult testFallback1(@PathVariable Long id) {
        CommonResult commonResult = userService.getUserException(id);
        log.info("hystrix-service 调用成功！！！");
        return commonResult;
    }

    @GetMapping("/testCache/{id}")
    public CommonResult testCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.getUserCache(id);
        userService.getUserCache(id);
        return new CommonResult("操作成功", 200);
    }

    @GetMapping("/testRemoveCache/{id}")
    public CommonResult testRemoveCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.removeCache(id);
        userService.getUserCache(id);
        return new CommonResult("操作成功", 200);
    }


}
