package com.xulifei.e.service.impl;

import com.xulifei.e.entity.User;
import com.xulifei.e.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by john on 2017/7/28.
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<User> implements AccountService {
    @Override
        public List<User> loginByAccountAndPwd(User user)
        {
            return userMapper.findUserByAccountAndPwd(user);
        }

        @Override
        public List<User> loginByAccount(User user) {
            List<User> userList = userMapper.findUserByAccount(user);
            return userList;
    }

    @Override
    public boolean updatePwdByAccount(User user) {

        int num = userMapper.updatePwdByAccount(user);
        if (num>0){
            return true;
        }
     return false;

    }

    @Override
    public boolean registerAccountIsExist(User user) {
        int num = userMapper.findByAccount(user);
         if (num>0){
             return true;
         }
        return false;
    }

    @Override
    public boolean register(User user) {
         int num = userMapper.insertSelective(user);
         if (num>0){
             return true;
         }
        return false;
    }

}
