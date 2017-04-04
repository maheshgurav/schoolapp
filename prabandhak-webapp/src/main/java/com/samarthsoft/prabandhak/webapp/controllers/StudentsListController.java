package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.ApplicationConstants;
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.form.entities.StudentForm;
import com.samarthsoft.prabandhak.utilities.CommonControllerUtility;
import com.samarthsoft.prabandhak.utilities.StudentControllerUtility;

@Controller
public class StudentsListController {

	@Autowired
	private StudentControllerUtility studentControllerUtility;
	@Autowired
	private CommonControllerUtility commonControllerUtility;
	
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ModelAndView listStudents(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	int pageNumber = 1;
    	if(RequestCommon.getAttributeValueFromRequest("page", request)!=null && !RequestCommon.getAttributeValueFromRequest("page", request).isEmpty())
    		pageNumber = Integer.parseInt(RequestCommon.getAttributeValueFromRequest("page", request));
    	PaginationObject students = DBCommunicator.getApiServices().getGenericApi().getPaginatedList(Student.class, studentControllerUtility.getFilteredList(ApplicationConstants.STUDENT_FILTERS, ApplicationConstants.STUDENT_FILTER_MAPPINGS, request),null,pageNumber);
    	List<StudentForm> studentFormObjects = new ArrayList<StudentForm>();
		if(students!=null){
	    	for (Object object : students.getRecords()) {
				Student student = (Student)object;
				studentFormObjects.add(studentControllerUtility.convertOriginalObjectToFormObject(student));
			}
		}
		//Set pagination
		commonControllerUtility.setCommonAttributesOfPagination(students, model, pageNumber);
    	model.put("students", studentFormObjects);
    	//Set filter values
    	studentControllerUtility.setFiltersInModel(ApplicationConstants.STUDENT_FILTERS, model, request);
    	return new ModelAndView("student_list",model);
    }
}