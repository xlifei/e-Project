package com.xulifei.e.dao;

import com.xulifei.e.entity.InviteCode;

import java.util.Map;

public interface InviteCodeMapper extends  BaseMapper<InviteCode>{
    int deleteByPrimaryKey(String codeId);

    InviteCode selectByPrimaryKey(String codeId);

    int updateByPrimaryKeySelective(InviteCode record);

    int updateByPrimaryKey(InviteCode record);
    int resetCode(Map<String ,Object> map);
    int stopCode(Map<String ,Object> map);
    int useCode(Map<String ,Object> map);
    InviteCode findCodeByClassId(String classId);
}