package com.samarthsoft.prabandhak.webapp.controllers;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JasperPrint;

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

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.ModelAttributesCommon;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Alumni;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.DBOperationEntity;
import com.samarthsoft.prabandhak.entities.RemovedStudentHistory;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.enums.CrudOperations;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.form.entities.ClassOrDivisionChangeForm;
import com.samarthsoft.prabandhak.form.entities.RemoveStudentForm;
import com.samarthsoft.prabandhak.utilities.ReportsControllerUtility;
import com.samarthsoft.prabandhak.utilities.StudentCertificatesControllerUtility;
import com.samarthsoft.prabandhak.utilities.StudentControllerUtility;
import com.samarthsoft.prabandhak.webapp.validators.StudentActivityValidator;

@Controller
public class StudentActivityController {
	@Autowired
	private ReportsControllerUtility reportsControllerUtility;
	@Autowired
	private StudentControllerUtility studentControllerUtility;
	@Autowired
	private StudentActivityValidator studentActivityValidator;
	@Autowired
	private StudentCertificatesControllerUtility studentCertificatesControllerUtility;
	
    @ResponseBody
    @RequestMapping(value = "/getbonafide", method = RequestMethod.GET)
    public String printBonafide(HttpServletRequest request, 
            HttpServletResponse response,ModelMap model) {
        JasperPrint jasperPrint = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
        	Map<String, Object> parameterMap = new HashMap<String, Object>();
        	List<Object> result = new ArrayList<Object>();
        	Student student = studentControllerUtility.getStudent(RequestCommon.getAttributeValueFromRequest("id", request));
        	result.add(studentControllerUtility.getBonifideCertificateReportObject(student));
        	jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "BONAFIED" , parameterMap);
            if (jasperPrint != null) {
            	reportsControllerUtility.getHtmlExporter(jasperPrint,outputStream).exportReport();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }
    
	@RequestMapping(value = "/removestudentfromcatalogue", method = RequestMethod.GET)
    public ModelAndView showRemoveStudentFromCatalogueForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
		model.put("studentremove", studentControllerUtility.getRemoveStudentFormObject(RequestCommon.getAttributeValueFromRequest("id", request)));
        return new ModelAndView("student_remove_form",model);
    }
	
	@RequestMapping(value = "/removestudentfromcatalogue", method = RequestMethod.POST)
    public ModelAndView submitRemoveStudentFromCatalogueForm(@Valid RemoveStudentForm removeStudentForm,BindingResult bindingResult,HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
		if(!bindingResult.hasErrors()){
			RemovedStudentHistory removedStudentHistory = new RemovedStudentHistory();
			removedStudentHistory.setDateOfRemoving(DateUtility.convertStringToTime(removeStudentForm.getDateOfRemoving()));
			removedStudentHistory.setReasonOfRemovingFromCatalog(removeStudentForm.getReasonOfRemoving());
			removedStudentHistory.setStudentGuid(removeStudentForm.getStudentGuid());
			List<DBOperationEntity> entities = new ArrayList<DBOperationEntity>();
			entities.add(new DBOperationEntity(removedStudentHistory,null,CrudOperations.INSERT));
			Student student = studentControllerUtility.getStudent(removeStudentForm.getStudentGuid());
			student.setIsAlumni(true);
			entities.add(new DBOperationEntity(removedStudentHistory,null,CrudOperations.INSERT));
			entities.add(new DBOperationEntity(student,"guid",CrudOperations.UPDATE));
			Alumni alumni = studentCertificatesControllerUtility.getAlumni(student);
			entities.add(new DBOperationEntity(alumni,null,CrudOperations.INSERT));
			Boolean result = DBCommunicator.getApiServices().getGenericApi().bulkInserOrUpdate(entities);
			if(result)
	    		return new ModelAndView("redirect:/" + "students.htm?saveSuccess=true");
		}
		model.put("studentremove", removeStudentForm);
        return new ModelAndView("student_remove_form",model);
    }
	
	@RequestMapping(value = "/changeclassordivisionofstudent", method = RequestMethod.GET)
    public ModelAndView showChangeClassorDivisionOfStudentForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
		model.put("changeclass", studentControllerUtility.getClassOrDivisionChangeFormObject(RequestCommon.getAttributeValueFromRequest("id", request)));
		return new ModelAndView("student_changeclass_form",model);
    }

	@RequestMapping(value = "/changeclassordivisionofstudent", method = RequestMethod.POST)
    public ModelAndView submitChangeClassorDivisionOfStudentForm(@Valid ClassOrDivisionChangeForm classOrDivisionChangeForm,BindingResult bindingResult,HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
        return new ModelAndView("student_changeclass_form",model);
    }
	
    @ModelAttribute(value="classes")
    private List<ClassInfo> setClassesInModel(HttpServletRequest request){
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
    	return ModelAttributesCommon.getClassInfoForModelAttributes(applicationSession);
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    	binder.setValidator(studentActivityValidator);
    }
}