package com.xulifei.e.dao;

import com.xulifei.e.entity.Class;

public interface ClassMapper extends  BaseMapper<ClassMapper> {
    int deleteByPrimaryKey(String classId);

    Class selectByPrimaryKey(String classId);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}