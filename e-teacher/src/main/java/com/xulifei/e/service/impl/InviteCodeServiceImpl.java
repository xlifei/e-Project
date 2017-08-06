package com.xulifei.e.service.impl;

import com.xulifei.e.entity.InviteCode;
import com.xulifei.e.service.InviteCodeService;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/8/5.
 */
@Service("inviteCodeService")
public class InviteCodeServiceImpl extends BaseServiceImpl<InviteCode> implements InviteCodeService {


    @Override
    public InviteCode findCodeByClassId(String classId) {

        return   inviteCodeMapper.findCodeByClassId(classId);
    }
}
