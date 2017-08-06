package com.xulifei.e.service;

import com.xulifei.e.entity.User;

import java.util.List;

/**
 * Created by john on 2017/7/28.
 */
public interface AccountService extends BaseService<User> {
     List<User> loginByAccountAndPwd(User user) throws  Exception;
    List<User> loginByAccount(User user) throws  Exception;
    boolean updatePwdByAccount(User user) throws  Exception;
    boolean registerAccountIsExist(User user) throws  Exception;
    boolean register(User user) throws  Exception;

}
