package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.ModelAttributesCommon;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Attendance;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.form.entities.AttendanceFormObject;
import com.samarthsoft.prabandhak.form.entities.StudentForm;
import com.samarthsoft.prabandhak.utilities.AttendanceControllerUtility;
import com.samarthsoft.prabandhak.utilities.StudentControllerUtility;

@Controller
public class AttendanceController {
	@Autowired
	private AttendanceControllerUtility attendanceControllerUtility;
	@Autowired
	private StudentControllerUtility studentControllerUtility;
	
	@RequestMapping(value = "/attendance", method = RequestMethod.GET)
    public ModelAndView renderAttendanceForm(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		AttendanceFormObject attendanceFormObject = (AttendanceFormObject) attendanceControllerUtility.getAttendanceFormObject(new AttendanceFormObject(RequestCommon.getAttributeValueFromRequest("date",request)),RequestCommon.getApplicationSession(request));
		List<StudentForm> studentFormObjects = new ArrayList<StudentForm>();
		List<Object> students = attendanceControllerUtility.getStudents(attendanceFormObject, RequestCommon.getApplicationSession(request).getOrganizationGuid());
    	for (Object object : students) {
			studentFormObjects.add(studentControllerUtility.convertOriginalObjectToFormObject((Student)object));
		}
		attendanceControllerUtility.setAbsentStudentRollNumbers(attendanceFormObject,students);
		model.put("attendance", attendanceFormObject);
		model.put("students", studentFormObjects);
		return new ModelAndView("attendance",model);
    }
	
	@RequestMapping(value = "/updateattendance", method = RequestMethod.GET)
    @ResponseBody
    public String submitAttendanceForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String date = request.getParameter("date");
    	String absentRollNumbers = request.getParameter("absentrollnumbers");
    	String classguid = request.getParameter("classguid");
    	AttendanceFormObject attendanceFormObject = new AttendanceFormObject(date,absentRollNumbers,classguid);
    	Boolean result = attendanceControllerUtility.saveOrUpdateAttendance(attendanceFormObject, RequestCommon.getApplicationSession(request));
		if(result)
			return "success";
		return "fail";
    }

	@RequestMapping(value = "/getattendance", method = RequestMethod.GET)
    @ResponseBody
    public String getAttendanceForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String date = request.getParameter("date");
    	String absentRollNumbers = request.getParameter("absentrollnumbers");
    	String classAndDivision = request.getParameter("classguid");
    	AttendanceFormObject attendanceFormObject = new AttendanceFormObject(date,absentRollNumbers,classAndDivision);
    	Boolean result = attendanceControllerUtility.saveOrUpdateAttendance(attendanceFormObject, RequestCommon.getApplicationSession(request));
		if(result)
			return "success";
		return "fail";
    }
	
	@RequestMapping(value = "/showattendance", method = RequestMethod.GET)
    @ResponseBody
    public String showAttendance(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String date = request.getParameter("date");
    	String classAndDivision = request.getParameter("class");
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(new Filter("standard", classAndDivision.split("-")[0].trim(), RestrictionType.EQ));
    	filters.add(new Filter("division", classAndDivision.split("-")[1].trim(), RestrictionType.EQ));
    	List<Filter> attendanceFilters = new ArrayList<Filter>();
    	attendanceFilters.add(new Filter("attendanceKey.absentOn", DateUtility.convertStringToTime(date), RestrictionType.EQ));
    	List<Object> attendance = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Attendance.class, attendanceFilters, null);
    	List<Object> students = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, filters, null);
    	return attendanceControllerUtility.getAttendanceInfortmation(attendance, students);
    }

	
	@RequestMapping(value = "/showabsentstudents", method = RequestMethod.GET)
    @ResponseBody
    public String showAbsentStudents(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	//String absentRollNumbers = request.getParameter("absentrollnumbers");
    	
		return "";
    }
	
	
	@ModelAttribute(value="classes")
    private List<ClassInfo> setClassesInModel(HttpServletRequest request){
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
    	return ModelAttributesCommon.getClassInfoForModelAttributes(applicationSession);
    }
}