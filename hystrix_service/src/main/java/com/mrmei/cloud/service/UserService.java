package com.mrmei.cloud.service;

import utils.CommonResult;

/**
 * @Author mrmei
 * @create 2021/8/4 11:32
 * @Description
 * @Version 1.0
 */
public interface UserService {

    CommonResult getUser(Long id);

    CommonResult getUserException(Long id);

    CommonResult getUserCache(Long id);

    CommonResult removeCache(Long id);
}
