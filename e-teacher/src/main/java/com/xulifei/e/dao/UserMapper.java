package com.xulifei.e.dao;

import com.xulifei.e.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User>{
    int deleteByPrimaryKey(String userId);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    List<User> findUserByAccountAndPwd(User user);
    int updatePwdByAccount(User user);
    int findByAccount(User user);
    int insertSelective(User user);
    List<User> findUserByAccount(User user);

}