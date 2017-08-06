package com.xulifei.e.service;

import com.xulifei.e.entity.User;

/**
 * Created by john on 2017/8/6.
 */
public interface UserService extends BaseService<User>  {
User findById(String userId) throws Exception;
}
