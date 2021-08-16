package com.mrmei.cloud.feign;

import com.mrmei.cloud.feign.fallback.UserFallbackService;
import entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import utils.CommonResult;

/**
 * @Author mrmei
 * @create 2021/8/14 15:42
 * @Description
 * @Version 1.0
 */

@FeignClient(value = "user-service",fallback = UserFallbackService.class)
public interface UserService {

    @PostMapping("/user/create")
    CommonResult<User> create(@RequestBody User user);

    @GetMapping("/user/{id}")
    CommonResult<User> getUser(@PathVariable("id") Long id);
}
