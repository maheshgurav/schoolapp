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
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.entities.Teacher;
import com.samarthsoft.prabandhak.form.entities.TeacherForm;
import com.samarthsoft.prabandhak.utilities.CommonControllerUtility;
import com.samarthsoft.prabandhak.utilities.TeacherControllerUtility;

@Controller
public class TeachersListController {
	
	@Autowired
	private TeacherControllerUtility teacherControllerUtility;
	@Autowired
	private CommonControllerUtility commonControllerUtility;
    
	@RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ModelAndView listSupportingStaffMembers(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	int pageNumber = 1;
    	if(RequestCommon.getAttributeValueFromRequest("page", request)!=null && !RequestCommon.getAttributeValueFromRequest("page", request).isEmpty())
    		pageNumber = Integer.parseInt(RequestCommon.getAttributeValueFromRequest("page", request));
    	PaginationObject teachers = DBCommunicator.getApiServices().getGenericApi().getPaginatedList(Teacher.class, null,null,pageNumber);
		
    	List<TeacherForm> teacherFormObjects = new ArrayList<TeacherForm>();
		if(teachers.getRecords()!=null){
			for (Object object : teachers.getRecords()) {
				Teacher teacher = (Teacher)object;
				teacherFormObjects.add(teacherControllerUtility.convertOriginalObjectToFromObject(teacher));
			}
			model.put("teachers", teacherFormObjects);
			commonControllerUtility.setCommonAttributesOfPagination(teachers, model, pageNumber);
		}
		return new ModelAndView("teacher_list",model);
    }
}
