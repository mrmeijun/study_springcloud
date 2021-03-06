package com.mrmei.cloud.controller;

import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import utils.CommonResult;

/**
 * @Author mrmei
 * @create 2021/8/3 10:14
 * @Description
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping(value ="/user")
public class UserController {

    @PostMapping("/create")
    public CommonResult<User> create(@RequestBody User user){
        log.info("创建用户"+user+"成功");
        return new CommonResult<>("操作成功", 200);
    }

    @GetMapping("/{id}")
    public CommonResult<User> getUser(@PathVariable("id") Long id){
        log.info("获取用户，id="+id);
        return new CommonResult<>(new User(id,"zhangsan","root"));
    }

}
