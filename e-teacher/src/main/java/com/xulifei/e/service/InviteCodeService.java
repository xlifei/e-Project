package com.xulifei.e.service;

import com.xulifei.e.entity.InviteCode;

/**
 * Created by john on 2017/8/5.
 */
public interface InviteCodeService extends  BaseService<InviteCode>{
      InviteCode findCodeByClassId(String classId);
}
