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
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.samarthsoft.prabandhak.entities.StudentContactDetails;
import com.samarthsoft.prabandhak.entities.StudentParentDetails;
import com.samarthsoft.prabandhak.entities.StudentSubjectDetails;
import com.samarthsoft.prabandhak.entities.Subject;
import com.samarthsoft.prabandhak.enums.Gender;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.form.entities.StudentForm;
import com.samarthsoft.prabandhak.form.entities.SubjectFormObject;
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
    	student.setSchoolGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
    	model.put("student", studentControllerUtility.convertOriginalObjectToFormObject(student));
    	return new ModelAndView("student_form",model);
    }
    
    @RequestMapping(value = "/editstudent", method = RequestMethod.GET)
    public ModelAndView renderEditStudentForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String studentGuid = RequestCommon.getAttributeValueFromRequest("id",request);
    	Student student = studentControllerUtility.getStudent(studentGuid);
    	StudentForm studentFormObject = studentControllerUtility.convertOriginalObjectToFormObject(student);
    	model.put("student", studentFormObject);
    	return new ModelAndView("student_form",model);
    }

    @RequestMapping(value = "/editstudentcontactdetails", method = RequestMethod.GET)
    public ModelAndView renderEditStudentContactForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String studentGuid = RequestCommon.getAttributeValueFromRequest("id",request);
    	if(studentGuid!=null && !studentGuid.isEmpty()){
	    	StudentContactDetails studentContactDetails = studentControllerUtility.getStudentContactDetails(studentGuid);
	    	studentContactDetails.setStudentId(studentGuid);
	    	model.put("student", studentContactDetails);
	    	return new ModelAndView("student_contact_details",model);
    	}
    	model.put("error", "Student not selected");
    	return new ModelAndView("error",model);
    }
    
    @RequestMapping(value = "/editstudentcontactdetails", method = RequestMethod.POST)
    public ModelAndView updateStudentContactDetails(@Valid StudentContactDetails studentContactDetails , BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	if(DBCommunicator.getApiServices().getGenericApi().insertOrUpdate(studentContactDetails,"studentId"))
	    		return new ModelAndView("redirect:/" + "students.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("student", studentContactDetails);
    	return new ModelAndView("student_contact_details",model);
    }

    @RequestMapping(value = "/editparentdetails", method = RequestMethod.GET)
    public ModelAndView renderEditStudentParentForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String studentGuid = RequestCommon.getAttributeValueFromRequest("id",request);
    	if(studentGuid!=null && !studentGuid.isEmpty()){
	    	StudentParentDetails studentParentDetails = studentControllerUtility.getStudentParentDetails(studentGuid);
	    	studentParentDetails.setStudentGuid(studentGuid);
	    	model.put("parent", studentParentDetails);
	    	return new ModelAndView("student_parent_details",model);
    	};
    	model.put("error", "Student not selected");
    	return new ModelAndView("error",model);
    }
    
    @RequestMapping(value = "/editparentdetails", method = RequestMethod.POST)
    public ModelAndView updateStudentParentDetails(@Valid StudentParentDetails studentParentDetails, BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	if(DBCommunicator.getApiServices().getGenericApi().insertOrUpdate(studentParentDetails,"guid"))
	    		return new ModelAndView("redirect:/" + "students.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("parent", studentParentDetails);
    	return new ModelAndView("student_parent_details",model);
    }
    
    @RequestMapping(value = "/setsubjects", method = RequestMethod.GET)
    public ModelAndView showSubjectsForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String studentGuid = RequestCommon.getAttributeValueFromRequest("id",request);
    	if(studentGuid!=null && !studentGuid.isEmpty()){
    		Student student = studentControllerUtility.getStudent(studentGuid);
    		if(student!=null){
	    		StudentSubjectDetails studentSubjectDetails = studentControllerUtility.getStudentSubjectDetails(studentGuid, student.getStandard(), student.getDivision());
	    		studentSubjectDetails.setStudentId(studentGuid);
		    	model.put("student", studentSubjectDetails);
		    		String[] subjects = null;
		    		if(studentSubjectDetails.getSubjects()!=null && !studentSubjectDetails.getSubjects().isEmpty()){
			    		subjects = studentSubjectDetails.getSubjects().split(",");
		    		}

		    		List<SubjectFormObject> subjectsTakenByStudent = new ArrayList<SubjectFormObject>();
		    		List<Subject> allsubjects = ModelAttributesCommon.getSubjectsForModelAttributes();
		    		for (Subject subject : allsubjects) {
	    				SubjectFormObject subjectFormObject = new SubjectFormObject();
	    				if(subjects!=null){
	    					subjectFormObject.setIsTakenByStudent(isSubjectTakenByStudent(subjects,subject.getCode()));
	    				}else{
	    					subjectFormObject.setIsTakenByStudent(false);
	    				}
		    			subjectFormObject.setSubjectCode(subject.getCode());
		    			subjectFormObject.setSubjectName(subject.getName());
		    			subjectFormObject.setGuid(subject.getGuid());
		    			subjectsTakenByStudent.add(subjectFormObject);
					}
		    		model.put("subjects",subjectsTakenByStudent);
		    	return new ModelAndView("student_subject_details",model);
    		}
    	}
    	model.put("error", "No Student selected");
    	return new ModelAndView("error",model);
    }
    
    private Boolean isSubjectTakenByStudent(String[] subjects,String subjectCodeToCheck){
    	for (String subject : subjects) {
			if(subject.equalsIgnoreCase(subjectCodeToCheck))
				return true;
		}
    	return false;
    }
    
	@RequestMapping(value = "/savesubjects", method = RequestMethod.GET)
    @ResponseBody
    public String submitSubjectsForm(HttpServletRequest request,
            HttpServletResponse response) {
    		String studentGuid = RequestCommon.getAttributeValueFromRequest("studentguid", request);
    		String subjects = RequestCommon.getAttributeValueFromRequest("subjects", request);
	    	Student student = studentControllerUtility.getStudent(studentGuid);
	    	StudentSubjectDetails studentSubjectDetails = new StudentSubjectDetails();
	    	studentSubjectDetails.setDivision(student.getDivision());
	    	studentSubjectDetails.setStandard(student.getStandard());
	    	studentSubjectDetails.setStudentId(student.getGuid());
	    	studentSubjectDetails.setSubjects(subjects);
    		Boolean result = DBCommunicator.getApiServices().getGenericApi().insertOrUpdate(studentSubjectDetails);
    		if(result)
    			return "Success";
    		return "Failure";
    }
    
    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    public ModelAndView addNewStudent(@Valid StudentForm studentFormObject , BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	Student student = studentControllerUtility.convertFormObjectToOriginalObject(studentFormObject);
	    	student.setSchoolGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
	    	student.setOrganizationGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
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
	    	student.setOrganizationGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
	    	student.setSchoolGuid(RequestCommon.getApplicationSession(request).getSchoolGuid());
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