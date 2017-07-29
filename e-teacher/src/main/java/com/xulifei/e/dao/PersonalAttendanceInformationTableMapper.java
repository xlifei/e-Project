package com.xulifei.e.dao;

import com.xulifei.e.entity.PersonalAttendanceInformationTable;
import com.xulifei.e.entity.PersonalAttendanceInformationTableKey;

public interface PersonalAttendanceInformationTableMapper extends BaseMapper<PersonalAttendanceInformationTable> {
    int deleteByPrimaryKey(PersonalAttendanceInformationTableKey key);

    PersonalAttendanceInformationTable selectByPrimaryKey(PersonalAttendanceInformationTableKey key);

    int updateByPrimaryKeySelective(PersonalAttendanceInformationTable record);

    int updateByPrimaryKey(PersonalAttendanceInformationTable record);
}