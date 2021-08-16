package com.mrmei.cloud.feign.fallback;

import com.mrmei.cloud.feign.UserService;
import entity.User;
import org.springframework.stereotype.Component;
import utils.CommonResult;

/**
 * @Author mrmei
 * @create 2021/8/16 15:48
 * @Description
 * @Version 1.0
 */
@Component
public class UserFallbackService implements UserService {
    @Override
    public CommonResult<User> create(User user) {
        return new CommonResult<>("创建用户操作成功", 200);
    }

    @Override
    public CommonResult<User> getUser(Long id) {
        User user = new User(-1L, "defaultName", "root");
        return new CommonResult<>(user, "操作成功", 200);
    }
}
