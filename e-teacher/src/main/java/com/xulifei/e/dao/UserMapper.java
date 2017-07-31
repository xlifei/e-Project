package com.xulifei.e.dao;

import com.xulifei.e.entity.User;

public interface UserMapper extends BaseMapper<User>{
    int deleteByPrimaryKey(String userId);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User login(User user);
    int updatePwdByAccount(User user);
    int findByAccount(User user);
    int insertSelective(User user);
}