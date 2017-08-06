package com.xulifei.e.service.impl;

import com.xulifei.e.entity.User;
import com.xulifei.e.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/8/6.
 */
@Service("userService")
public class UserServiceImpl extends  BaseServiceImpl<User> implements UserService {

    @Override
    public User findById(String userId) throws Exception {

        return userMapper.selectByPrimaryKey(userId);
    }
}
