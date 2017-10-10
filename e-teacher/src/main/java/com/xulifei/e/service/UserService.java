package com.xulifei.e.service;

import com.xulifei.e.entity.User;

import java.util.List;

/**
 * Created by john on 2017/8/6.
 */
public interface UserService extends BaseService<User>  {
User findById(String userId) throws Exception;
    User findTeacherByClassId(String classId) throws  Exception;
  List<User> findStudentByClassId(String classId) throws  Exception;
    List<User> findStudentByClassIdForCloudStatistics(String classId) throws  Exception;
    int updateNumber(User user) throws  Exception;

}
