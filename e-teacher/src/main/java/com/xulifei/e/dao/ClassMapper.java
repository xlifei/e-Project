package com.xulifei.e.dao;

import com.xulifei.e.entity.Class;

import java.util.List;

public interface ClassMapper extends  BaseMapper<Class> {
    int deleteByPrimaryKey(String classId);

    Class selectByPrimaryKey(String classId);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
    List<Class> findAll(String userId);
    List<Class> findArchiveClassRoom (Class c);
    int findSum(String userId);
    int updateCurrentSerialNumber(String currentClassId);
    int updatePreSerialNumber(String preClassId);
    int updateClassName(Class c);
    int updateIsArchive(Class c);
   List<Class> findByCode(String code);
     int updateMemberByClassId(String classId);
}