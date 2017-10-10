package com.xulifei.e.service.impl;

import com.xulifei.e.entity.TotalAttendanceInfomation;
import com.xulifei.e.service.TotalAttendanceInfomationService;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/8/20.
 */
@Service("totalAttendanceInfomationService")
public class TotalAttendanceInfomationServiceImpl extends BaseServiceImpl<TotalAttendanceInfomation> implements TotalAttendanceInfomationService {


    @Override
    public TotalAttendanceInfomation findByClassId(String classId) {

        return totalAttendanceInfomationMapper.findByClassId(classId);
    }
}
