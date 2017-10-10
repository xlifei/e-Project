package com.xulifei.e.dao;

import com.xulifei.e.entity.PersonalAttendanceInformationTable;
import com.xulifei.e.entity.PersonalAttendanceInformationTableKey;

import java.util.List;
import java.util.Map;

public interface PersonalAttendanceInformationTableMapper extends BaseMapper<PersonalAttendanceInformationTable> {
    int deleteByPrimaryKey(PersonalAttendanceInformationTableKey key);

    PersonalAttendanceInformationTable selectByPrimaryKey(PersonalAttendanceInformationTableKey key);

    int updateByPrimaryKeySelective(PersonalAttendanceInformationTable record);

    int updateByPrimaryKey(PersonalAttendanceInformationTable record);
    int deleteAllByClassId(String classId);
    List<String> findAllByClassId(String classId);
    int updateFinshByPersonAttendanceInformationId(PersonalAttendanceInformationTable personalAttendanceInformationTable);
    int updateUnFinshByPersonAttendanceInformationId(PersonalAttendanceInformationTable personalAttendanceInformationTable);

    List<PersonalAttendanceInformationTable>selectList(Map<String,Object> map);
    PersonalAttendanceInformationTable findByClassIdAndUserId(PersonalAttendanceInformationTable personalAttendanceInformationTable);
    int updateByIdForchuqin(PersonalAttendanceInformationTable p);
    int updateByIdForchidao(PersonalAttendanceInformationTable p);
    int updateByIdForkuang(PersonalAttendanceInformationTable p);
    int updateByIdForqingjia(PersonalAttendanceInformationTable p);



    int updateAttendanceAndLeaveNumber(PersonalAttendanceInformationTable p);
    int updateLateAndKuangNumber(PersonalAttendanceInformationTable p);
    int updateKuangAndAttendanceNumber(PersonalAttendanceInformationTable p);
    int updateLeaveAndLateNumber(PersonalAttendanceInformationTable p);

    int deleteByClassIdAndUserId(PersonalAttendanceInformationTable p);


}