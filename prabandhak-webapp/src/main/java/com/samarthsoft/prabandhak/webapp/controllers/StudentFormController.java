package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.CachedDataStore;
import com.samarthsoft.prabandhak.common.ModelAttributesCommon;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Caste;
import com.samarthsoft.prabandhak.entities.Category;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.ScholarshipType;
import com.samarthsoft.prabandhak.entities.State;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.enums.Gender;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.form.entities.StudentForm;
import com.samarthsoft.prabandhak.utilities.StudentControllerUtility;
import com.samarthsoft.prabandhak.webapp.validators.StudentValidator;

@Controller
public class StudentFormController {
	
	@Autowired
	private StudentValidator studentValidator;
	@Autowired
	private StudentControllerUtility studentControllerUtility;
	
    @RequestMapping(value = "/addstudent", method = RequestMethod.GET)
    public ModelAndView renderAddStudentForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	Student student = new Student();
    	student.setGender(Gender.Male);
    	model.put("student", studentControllerUtility.convertOriginalObjectToFormObject(student));
    	return new ModelAndView("student_form",model);
    }
    
    @RequestMapping(value = "/editstudent", method = RequestMethod.GET)
    public ModelAndView renderEditStudentForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String studentGuid = RequestCommon.getAttributeValueFromRequest("id",request);
    	Student student = studentControllerUtility.getStudent(studentGuid);
    	student.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
    	StudentForm studentFormObject = studentControllerUtility.convertOriginalObjectToFormObject(student);
    	model.put("student", studentFormObject);
    	return new ModelAndView("student_form",model);
    }
    
    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    public ModelAndView addNewStudent(@Valid StudentForm studentFormObject , BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	Student student = studentControllerUtility.convertFormObjectToOriginalObject(studentFormObject);
	    	student.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
	    	student.setIsAlumni(false);
	    	if(DBCommunicator.getApiServices().getGenericApi().insert(student))
	    		return new ModelAndView("redirect:/" + "students.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("student", studentFormObject);
    	return new ModelAndView("student_form",model);
    }
    
    @RequestMapping(value = "/editstudent", method = RequestMethod.POST)
    public ModelAndView updateStudent(@Valid StudentForm studentFormObject, BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	Student student = studentControllerUtility.convertFormObjectToOriginalObject(studentFormObject);
	    	student.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
	    	student.setIsAlumni(false);
	    	if(DBCommunicator.getApiServices().getGenericApi().update(student))
	    		return new ModelAndView("redirect:/" + "students.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("student", studentFormObject);
    	return new ModelAndView("student_form",model);
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    	binder.setValidator(studentValidator);
    }
    
    @ModelAttribute(value="castes")
    private List<Caste> setCastesInModel(){
    	return ModelAttributesCommon.getCastesForModelAttributes();
    }
    
    @ModelAttribute(value="categories")
    private List<Category> setCategoriesInModel(){
    	return ModelAttributesCommon.getCategoriesForModelAttributes();
    }
    
    @ModelAttribute(value="classes")
    private List<ClassInfo> setClassesInModel(HttpServletRequest request){
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
    	return ModelAttributesCommon.getClassInfoForModelAttributes(applicationSession);
    }
    
    @ModelAttribute(value="scholarshiptypes")
    private List<ScholarshipType> setScholarshipTypesInModel(){
    	return ModelAttributesCommon.getScholarshipTypesForModelAttributes();
    }
    
    @ModelAttribute(value="states")
    private List<State> setStatesInModel(){
    	return ModelAttributesCommon.getStatesForModelAttributes();
    }
    
    @ModelAttribute(value="nationalities")
    private List<Object> setNationalitiesInModel(){
    	return CachedDataStore.getNationalities();
    }
    
    @ModelAttribute(value="subjects")
    private List<Object> setSubjectsInModel(){
    	return CachedDataStore.getSubjects();
    }
}