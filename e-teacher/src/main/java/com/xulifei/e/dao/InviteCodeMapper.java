package com.xulifei.e.dao;

import com.xulifei.e.entity.InviteCode;

public interface InviteCodeMapper extends  BaseMapper<InviteCode>{
    int deleteByPrimaryKey(String codeId);

    InviteCode selectByPrimaryKey(String codeId);

    int updateByPrimaryKeySelective(InviteCode record);

    int updateByPrimaryKey(InviteCode record);
}