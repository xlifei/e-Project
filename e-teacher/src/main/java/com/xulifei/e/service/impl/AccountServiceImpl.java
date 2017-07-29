package com.xulifei.e.service.impl;

import com.xulifei.e.entity.User;
import com.xulifei.e.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/7/28.
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<User> implements AccountService {
    @Override
    public User login(User user) {
     return userMapper.login(user);
    }

}
