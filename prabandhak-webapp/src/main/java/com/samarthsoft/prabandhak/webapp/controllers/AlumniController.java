package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.ArrayList;
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
import com.samarthsoft.prabandhak.entities.Alumni;
import com.samarthsoft.prabandhak.entities.Caste;
import com.samarthsoft.prabandhak.entities.Category;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.ScholarshipType;
import com.samarthsoft.prabandhak.entities.State;
import com.samarthsoft.prabandhak.enums.Gender;
import com.samarthsoft.prabandhak.form.entities.AlumniFormObject;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.utilities.AlumniControllerUtility;
import com.samarthsoft.prabandhak.webapp.validators.AlumniValidator;

@Controller
public class AlumniController {
	
	@Autowired
	private AlumniControllerUtility alumniControllerUtility;
	@Autowired
	private AlumniValidator alumniValidator;

	@RequestMapping(value = "/alumnis", method = RequestMethod.GET)
    public ModelAndView renderLoginForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
		List<Object> alumnis = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Alumni.class, null, null);
		List<AlumniFormObject> result = new ArrayList<AlumniFormObject>();
		for (Object object : alumnis) {
			Alumni alumni = (Alumni)object;
			result.add(alumniControllerUtility.getAlumniFormObject(alumni));
		}
		model.put("alumnis", result);
        return new ModelAndView("alumni_list",model);
    }
	
    @RequestMapping(value = "/addalumni", method = RequestMethod.GET)
    public ModelAndView renderAddStudentForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	Alumni alumni = new Alumni();
    	alumni.setGender(Gender.Male);
    	model.put("alumni", alumniControllerUtility.getAlumniFormObject(alumni));
    	return new ModelAndView("alumni_form",model);
    }
    
    @RequestMapping(value = "/editalumni", method = RequestMethod.GET)
    public ModelAndView renderEditStudentForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String studentGuid = RequestCommon.getAttributeValueFromRequest("id",request);
    	Alumni alumni = alumniControllerUtility.getAlumni(studentGuid);
    	alumni.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
    	AlumniFormObject studentFormObject = alumniControllerUtility.getAlumniFormObject(alumni);
    	model.put("alumni", studentFormObject);
    	return new ModelAndView("alumni_form",model);
    }
    
    @RequestMapping(value = "/addalumni", method = RequestMethod.POST)
    public ModelAndView addNewStudent(@Valid AlumniFormObject studentFormObject , BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	Alumni student = alumniControllerUtility.getAlumniObject(studentFormObject);
	    	student.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
	    	if(DBCommunicator.getApiServices().getGenericApi().insert(student))
	    		return new ModelAndView("redirect:/" + "alumnis.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("alumni", studentFormObject);
    	return new ModelAndView("alumni_form",model);
    }
    
    @RequestMapping(value = "/editalumni", method = RequestMethod.POST)
    public ModelAndView updateStudent(@Valid AlumniFormObject studentFormObject, BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	Alumni student = alumniControllerUtility.getAlumniObject(studentFormObject);
	    	student.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
	    	if(DBCommunicator.getApiServices().getGenericApi().update(student))
	    		return new ModelAndView("redirect:/" + "alumnis.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("alumni", studentFormObject);
    	return new ModelAndView("alumni_form",model);
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    	binder.setValidator(alumniValidator);
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