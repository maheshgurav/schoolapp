package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Attendance;
import com.samarthsoft.prabandhak.entities.AttendanceKey;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.enums.UserRole;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.form.entities.AttendanceFormObject;

@Component
public class AttendanceControllerUtility {

	public Object getAttendanceFormObject(AttendanceFormObject attendanceFormObject,ApplicationSession applicationSession) {
		if(attendanceFormObject.getDate() == null)
			attendanceFormObject.setDate(DateUtility.convertTimeToString(DateUtility.getCalendarWithoutTime().getTimeInMillis()));
		else attendanceFormObject.setDate(DateUtility.convertTimeToString(DateUtility.getCalendarWithoutTime().getTimeInMillis()));
		if(applicationSession.getUserRole() == UserRole.TEACHER){
			attendanceFormObject.setAttendanceForClass(applicationSession.getAssociatedClassGuid());
		}
		
		return attendanceFormObject;
	}
	
	public void setAbsentStudentRollNumbers(AttendanceFormObject attendanceFormObject,List<Object> studentsFromDB){
		List<Filter> filters = new ArrayList<Filter>();
		List<AttendanceKey> attendanceKeys = new ArrayList<AttendanceKey>();
		for(Object object : studentsFromDB){
			attendanceKeys.add(new AttendanceKey(DateUtility.getCalendarWithoutTime().getTimeInMillis(), ((Student)object).getGuid()));
		}
		filters.add(new Filter("attendanceKey", attendanceKeys , RestrictionType.IN));
		List<Object> attendanceObjects = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Attendance.class, filters, null);
		List<String> students = new ArrayList<String>();
		for (Object attendanceObject : attendanceObjects) {
			students.add(((Attendance)attendanceObject).getAttendanceKey().getStudentGuid());
		}
		String absentRollNumbers = "";
		if(!students.isEmpty()){
			for(Object studentObj : studentsFromDB){
				Student student = (Student)studentObj;
				if(students.contains(student.getGuid())){
					if(absentRollNumbers.equals(""))
						absentRollNumbers = student.getRollNumber();
					else
						absentRollNumbers = absentRollNumbers + "," + student.getRollNumber();
				}
			}
		}
		attendanceFormObject.setAbsentRollNumbers(absentRollNumbers);
	}

	public Object convertFormObjectToOriginalObject(Object formObject) {
		return null;
	}
	
	public List<Object> getStudents(AttendanceFormObject attendanceFormObject,String organizationGuid){
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("currentClass", attendanceFormObject.getAttendanceForClass(), RestrictionType.EQ));
		//TODO:Enable organization filter
		//filters.add(new Filter("organizationGuid", organizationGuid, RestrictionType.EQ));
		return DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, filters, null);
	}
	
	public List<Object> getStudentsByRollNumbers(List<String> rollNumbers,String classGuid ,String organizationGuid){
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("rollNumber", rollNumbers, RestrictionType.IN));
		if(!ValidatorCommon.checkFieldNullOrEmpty(classGuid)){
			filters.add(new Filter("standard", classGuid.split("-")[0].trim(), RestrictionType.EQ));
			filters.add(new Filter("division", classGuid.split("-")[1].trim(), RestrictionType.EQ));
		}
		return DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, filters, null);
	}
	
	public boolean saveOrUpdateAttendance(AttendanceFormObject attendanceFormObject,ApplicationSession applicationSession){
		String[] absentNumbers = attendanceFormObject.getAbsentRollNumbers().split(",");
		List<String> absentNumbersList = new ArrayList<String>();
		List<String> oldAbsentNumbersList = new ArrayList<String>();
		Long attendanceTime = DateUtility.convertStringToTime(attendanceFormObject.getDate());
		
		if(absentNumbers.length>0){
			for (String absentNumber : absentNumbers) {
				if(!absentNumber.isEmpty())
					absentNumbersList.add(absentNumber);
			}
		}
		List<String> studentRecords = new ArrayList<String>();
		studentRecords.addAll(absentNumbersList);
		studentRecords.addAll(oldAbsentNumbersList);
		List<Attendance> attendanceRecordsToInsert = new ArrayList<Attendance>();
		List<Attendance> attendanceRecordsToDelete = new ArrayList<Attendance>();
		if(!absentNumbersList.isEmpty()){
			List<Object> students = getStudentsByRollNumbers(studentRecords, attendanceFormObject.getAttendanceForClass() , applicationSession.getSchoolGuid());
			for (Object object : students) {
				Student student = (Student)object;
				if(oldAbsentNumbersList.contains(student.getRollNumber())){
					Attendance attendanceObj = new Attendance();
					attendanceObj.setAttendanceKey(new AttendanceKey(attendanceTime,student.getGuid()));
					attendanceRecordsToDelete.add(attendanceObj);
				} 
				if(absentNumbersList.contains(student.getRollNumber())){
					Attendance attendanceObj = new Attendance();
					attendanceObj.setAttendanceKey(new AttendanceKey(attendanceTime,student.getGuid()));
					attendanceRecordsToInsert.add(attendanceObj);
				}
			}
		}
		return DBCommunicator.getApiServices().getAttendanceApi().fillAttendance(attendanceRecordsToDelete,attendanceRecordsToInsert);
	}
	
	public String getAttendanceInfortmation(List<Object> attendanceInfo, List<Object> students){
		String result = "";
		try{
			JSONArray jsonArray = new JSONArray();
			for (Object object : students) {
				Student student = (Student) object;
				JSONObject jsonObject = new JSONObject();
				jsonObject.append("name", student.getName().toString());
				jsonObject.append("roll", student.getRollNumber());
				if(isStudentAbsent(attendanceInfo, student.getGuid()))
					jsonObject.append("absent",1);
				jsonArray.put(jsonObject);
			}
			result = jsonArray.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public boolean isStudentAbsent(List<Object> attendanceInfo,String studentGuid){
		try{
			for (Object object : attendanceInfo) {
				Attendance attendance = (Attendance) object;
				if(attendance.getAttendanceKey().getStudentGuid().equals(studentGuid))
					return true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}