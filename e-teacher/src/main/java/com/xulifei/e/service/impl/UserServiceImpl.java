package com.xulifei.e.service.impl;

import com.xulifei.e.entity.User;
import com.xulifei.e.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by john on 2017/8/6.
 */
@Service("userService")
public class UserServiceImpl extends  BaseServiceImpl<User> implements UserService {

    @Override
    public User findById(String userId) throws Exception {

        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User findTeacherByClassId(String classId) throws Exception {
        User u = userMapper.findTeacherByClassId(classId);
         return u;
    }

    @Override
    public List<User> findStudentByClassId(String classId) throws Exception {
        List<User> studentList = userMapper.findStudentByClassId(classId);
        return studentList;
    }

    @Override
    public List<User> findStudentByClassIdForCloudStatistics(String classId) throws Exception {
        return userMapper.findStudentByClassIdForCloudStatistics(classId);
    }

    @Override
    public int updateNumber(User user) throws Exception {
        return userMapper.updateNumber(user);
    }
}
