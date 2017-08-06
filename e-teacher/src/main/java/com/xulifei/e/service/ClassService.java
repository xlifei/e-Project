package com.xulifei.e.service;

import com.xulifei.e.entity.Class;

import java.util.List;
import java.util.Map;

/**
 * Created by john on 2017/8/2.
 */
public interface ClassService extends BaseService<Class> {

   Boolean addClass(Class c)throws Exception;
   List<Class> findAllClassRoom(String userId) throws  Exception;
   List<Class> findAllArchiveClassRoom(Class c) throws  Exception;
   int updateSerialNumber(String currentClassId,String preClassId) throws  Exception;
   int updateClassName(Class c) throws  Exception;
   int updateIsArchive(Class c) throws  Exception;

   int resetCode(Map<String ,Object> map) throws  Exception;
   int stopCode(Map<String ,Object> map) throws  Exception;
   int useCode(Map<String ,Object> map) throws  Exception;
   List<Class> joinClass(String userId,String code) throws  Exception;
   int joinClass2(String classId,int totalAttendenceNumber,String userId) throws  Exception;

}
