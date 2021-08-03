package com.mrmei.cloud.controller;

import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import utils.CommonResult;

/**
 * @Author mrmei
 * @create 2021/8/3 11:16
 * @Description
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;


    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        CommonResult commonResult = restTemplate.postForObject(userServiceUrl + "/user/create", user, CommonResult.class);
        log.info(""+commonResult);
        return commonResult;
    }
}
