package com.xulifei.e.service;

import com.xulifei.e.entity.User;

/**
 * Created by john on 2017/7/28.
 */
public interface AccountService extends BaseService<User> {
     User login(User user);
    boolean updatePwdByAccount(User user);
    boolean registerAccountIsExist(User user);
    boolean register(User user);
}
