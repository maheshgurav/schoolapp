package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.JsonConverter;
import com.samarthsoft.prabandhak.common.ModelAttributesCommon;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.LeavingCertificate;
import com.samarthsoft.prabandhak.entities.Name;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.utilities.StudentCertificatesControllerUtility;

@Controller
public class StudentOperationsController {
	
	@Autowired
	private StudentCertificatesControllerUtility studentCertificatesControllerUtility;

	@RequestMapping(value = "/studentoperations", method = RequestMethod.GET)
	public ModelAndView viewSettings(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("student_operations");
	}
	
    @ModelAttribute(value="classes")
    private List<ClassInfo> setClassesInModel(HttpServletRequest request){
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
    	return ModelAttributesCommon.getClassInfoForModelAttributes(applicationSession);
    }
    
	@RequestMapping(value = "/opchangeclass", method = RequestMethod.GET)
    public ModelAndView changeClass(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("student_operations_changeclass");
	}
	
	@RequestMapping(value = "/opmakealumnis", method = RequestMethod.GET)
    public ModelAndView opMakeAlumnis(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("student_operations_markalumnis");
	}
	
	@RequestMapping(value = "/showstudentstomarkalumnis", method = RequestMethod.GET)
    @ResponseBody
    public String loadStudents(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String classGuid = request.getParameter("class");
    	List<Object> students = studentCertificatesControllerUtility.getStudents(classGuid, RequestCommon.getApplicationSession(request).getOrganizationGuid());
    	Map<String, Student> studentMap = new HashMap<String, Student>();
    	List<String> studentGuids = new ArrayList<String>();
    	for (Object object : students) {
			Student student = (Student) object;
			studentGuids.add(student.getGuid());
			studentMap.put(student.getGuid(), student);
		}    	

    	List<Object> leavingCertificates = studentCertificatesControllerUtility.getLeavingCertificatesList(studentGuids);
    	JSONArray array = new JSONArray();
    	try {
	       	 for (Object object : leavingCertificates) {
	       		 	LeavingCertificate leavingCertificate = (LeavingCertificate) object;
	       		 	Student student = studentMap.get(leavingCertificate.getStudentGuid());
	       		 	JSONObject obj = new JSONObject();
		        	obj.put("roll", student.getRollNumber());
		        	Name studentName = (Name) JsonConverter.fromJson(student.getName(), Name.class);
		            obj.put("name", studentName.toString());
		            obj.put("guid",student.getGuid());
		            array.put(obj);
	       	 }
		}catch(Exception ex){
			ex.printStackTrace();
		}
    	return array.toString();
    }
	
    @RequestMapping(value = "/changeclassanddivision", method = RequestMethod.GET)
    @ResponseBody
    public String changeClassAndDivision(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
    	String selectedStudents = request.getParameter("selected_students");
    	String newStandardAndDivision = request.getParameter("newclass");
    	List<Object> students = studentCertificatesControllerUtility.getStudents(selectedStudents);
		List<Object> updatedStudents = new ArrayList<Object>();
		for (Object object : students) {
			Student student = (Student)object;
			String[] classAndDivision = newStandardAndDivision.split("-");
			student.setStandard(classAndDivision[0]);
			student.setDivision(classAndDivision[1]);
			updatedStudents.add(student);
		}
		if(DBCommunicator.getApiServices().getGenericApi().bulkUpdate(updatedStudents))
			return "Success";
		return "Failure";
    }

	@RequestMapping(value = "/makealumnis", method = RequestMethod.GET)
    @ResponseBody
    public String makeAlumnis(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
    	String selectedStudents = request.getParameter("selected_students");
		List<Object> students = studentCertificatesControllerUtility.getStudents(selectedStudents);
		
		List<Object> updatedStudents = new ArrayList<Object>();
		for (Object object : students) {
			Student student = (Student)object;
			student.setIsAlumni(true);
			updatedStudents.add(student);
		}
		List<Object> alumnis = new ArrayList<Object>();
		for (Object object : students) {
			alumnis.add(studentCertificatesControllerUtility.getAlumni((Student)object));
		}
		if(DBCommunicator.getApiServices().getStudentsApi().createAlumniAndMarkStudentAsAlumni(updatedStudents, alumnis))
			return "Success";
		return "Failure";
	}
}