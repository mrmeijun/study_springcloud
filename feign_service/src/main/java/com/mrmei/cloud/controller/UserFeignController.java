package com.mrmei.cloud.controller;

import com.mrmei.cloud.feign.UserService;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.CommonResult;

/**
 * @Author mrmei
 * @create 2021/8/14 15:49
 * @Description
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    CommonResult<User> create(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping("/{id}")
    CommonResult<User> getUser(@PathVariable("id") Long id){
        log.info("获取用户，id=",id);
        return userService.getUser(id);
    }

}
