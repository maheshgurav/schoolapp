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
import com.samarthsoft.prabandhak.common.PasswordEncoder;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.entities.State;
import com.samarthsoft.prabandhak.entities.Teacher;
import com.samarthsoft.prabandhak.enums.UserRole;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.form.entities.TeacherForm;
import com.samarthsoft.prabandhak.utilities.TeacherControllerUtility;
import com.samarthsoft.prabandhak.webapp.validators.TeacherValidator;

@Controller
public class TeacherFormController {
	
	@Autowired
	private TeacherValidator teacherValidator;
	@Autowired
	private TeacherControllerUtility teacherControllerUtility;
	
    @RequestMapping(value = "/addteacher", method = RequestMethod.GET)
    public ModelAndView renderAddTeacherForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	TeacherForm teacherFormObject = new TeacherForm();
    	model.put("teacher", teacherFormObject);
    	return new ModelAndView("teacher_form",model);
    }
    
    @RequestMapping(value = "/editteacher", method = RequestMethod.GET)
    public ModelAndView renderEditTeacherForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String teacherGuid = RequestCommon.getAttributeValueFromRequest("id",request);
    	TeacherControllerUtility teacherControllerUtility = new TeacherControllerUtility();
    	TeacherForm teacherFormObject = teacherControllerUtility.convertOriginalObjectToFromObject(teacherControllerUtility.getTeacher(teacherGuid));
    	model.put("teacher", teacherFormObject);
    	return new ModelAndView("teacher_form",model);
    }
    
    @RequestMapping(value = "/teacherprofile", method = RequestMethod.GET)
    public ModelAndView renderTeacherProfileForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String teacherGuid = RequestCommon.getAttributeValueFromRequest("id",request);
    	TeacherControllerUtility teacherControllerUtility = new TeacherControllerUtility();
    	TeacherForm teacherFormObject = teacherControllerUtility.convertOriginalObjectToFromObject(teacherControllerUtility.getTeacher(teacherGuid));
    	model.put("teacher", teacherFormObject);
    	return new ModelAndView("teacher_profile_form",model);
    }

    @RequestMapping(value = "/addteacher", method = RequestMethod.POST)
    public ModelAndView addNewTeacherMember(@Valid TeacherForm teacherFormObject,BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	Teacher teacher = teacherFormObject.getTeacher();
	    	teacher.setOrganizationGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
	    	teacher.setSchoolGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
	    	LoginInformation loginInformation = teacher.getLoginInformation();
	    	loginInformation.setPassword(PasswordEncoder.hashPassword(loginInformation.getPassword()));
	    	loginInformation.setOrganizationGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
	    	loginInformation.setSchoolGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
	    	loginInformation.setUserRole(UserRole.TEACHER);
	    	if(DBCommunicator.getApiServices().getCommonApi().insertObjectWithLoginInformation(teacher, loginInformation))
	    		return new ModelAndView("redirect:/" + "teachers.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("teacher", teacherFormObject);
		return new ModelAndView("teacher_form",model);
    }
    
    @RequestMapping(value = "/editteacher", method = RequestMethod.POST)
    public ModelAndView updateTeacherMember(@Valid TeacherForm teacherFormObject,BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
    		Teacher teacher = teacherFormObject.getTeacher();
    		teacher.setOrganizationGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
    		teacher.setSchoolGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
    		LoginInformation loginInformation = teacher.getLoginInformation();
	    	loginInformation.setOrganizationGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
	    	loginInformation.setSchoolGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
	    	loginInformation.setUserRole(UserRole.TEACHER);
	    	if(loginInformation.getPassword() != null && !loginInformation.getPassword().isEmpty())
	    		loginInformation.setPassword(PasswordEncoder.hashPassword(loginInformation.getPassword()));
	    	if(DBCommunicator.getApiServices().getCommonApi().updateObjectWithLoginInformation(teacher, loginInformation))
	    		return new ModelAndView("redirect:/" + "teachers.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("teacher", teacherFormObject);
		return new ModelAndView("teacher_form",model);
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(teacherValidator);
    }
    
    @ModelAttribute(value="classes")
    private List<ClassInfo> setClassesInModel(HttpServletRequest request){
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
    	return ModelAttributesCommon.getClassInfoForModelAttributes(applicationSession);
    }
    
    @ModelAttribute(value="states")
    private List<State> setStatesInModel(){
    	return ModelAttributesCommon.getStatesForModelAttributes();
    }
    
    @ModelAttribute(value="designations")
    private List<Object> setTeacherDesignationsInModel(){
    	return CachedDataStore.getTeacherDesignations();
    }

	public void setTeacherControllerUtility(TeacherControllerUtility teacherControllerUtility) {
		this.teacherControllerUtility = teacherControllerUtility;
	}
}