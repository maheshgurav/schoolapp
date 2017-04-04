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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.LeavingCertificate;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.form.entities.LeavingCertificateForm;
import com.samarthsoft.prabandhak.utilities.LeavingCertificateControllerUtility;
import com.samarthsoft.prabandhak.utilities.ReportsControllerUtility;
import com.samarthsoft.prabandhak.utilities.StudentControllerUtility;
import com.samarthsoft.prabandhak.webapp.validators.LeavingCertificateValidator;

@Controller
public class LeavingCertificateController {
	@Autowired
	private LeavingCertificateValidator leavingCertificateValidator;
	@Autowired
	private StudentControllerUtility studentControllerUtility;
	@Autowired
	private LeavingCertificateControllerUtility leavingCertificateControllerUtility;
	@Autowired
	private ReportsControllerUtility reportsControllerUtility;
    
	@RequestMapping(value = "/leavingcertificate", method = RequestMethod.GET)
    public ModelAndView renderLeavingCertificateForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
		Student student = studentControllerUtility.getStudent(RequestCommon.getAttributeValueFromRequest("id", request));
		List<Object> leavingCertificates = DBCommunicator.getApiServices().getGenericApi().getObjectByColumnName(LeavingCertificate.class, "studentGuid", student.getGuid());
		LeavingCertificate leavingCertificate = null;
		LeavingCertificateForm leavingCertificateForm = null;
		if(leavingCertificates!=null && leavingCertificates.size()>0)
			leavingCertificate = (LeavingCertificate)leavingCertificates.get(0);
		if(leavingCertificate!=null){
			model.put("showcertificatebutton", true);
			leavingCertificateForm = leavingCertificateControllerUtility.getLeavingCertificateFormObject(leavingCertificate, student);
		}
		else
			leavingCertificateForm = leavingCertificateControllerUtility.getLeavingCertificateFormObject(new LeavingCertificate(), student);
		model.put("studentGuid", student.getGuid());
		return getLeavingCertificateForm(leavingCertificateForm, model);
    }
	
	@RequestMapping(value = "/leavingcertificate", method = RequestMethod.POST)
    public ModelAndView submitLeavingCertificateForm(@Valid LeavingCertificateForm leavingCertificateFormObject,BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
		if(!bindingResult.hasErrors()){
			Student student = studentControllerUtility.getStudent(leavingCertificateFormObject.getStudentGuid());
			Boolean result = leavingCertificateControllerUtility.saveLeavingCertificateInformation(leavingCertificateFormObject,student);
			if(result){
	    		return new ModelAndView("redirect:/" + "leavingcertificate.htm?id="+student.getGuid());
			}
		}
		return getLeavingCertificateForm(leavingCertificateFormObject, model);
    }
	
    @ResponseBody
    @RequestMapping(value = "/getlc", method = RequestMethod.GET)
    public String getLeavingCertificate(HttpServletRequest request, 
            HttpServletResponse response,ModelMap model) {
        JasperPrint jasperPrint = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
        	String studentGuid = RequestCommon.getAttributeValueFromRequest("id",request);
        	Student student = studentControllerUtility.getStudent(studentGuid);
    		Map<String, Object> parameterMap = new HashMap<String, Object>();
    		parameterMap.put("message", "This is student and class wise report of school");
    		List<Object> result = new ArrayList<Object>();
    		LeavingCertificate leavingCertificate = studentControllerUtility.getLeavingCertificate(student.getGuid());
    		result.add(reportsControllerUtility.getLeavingCertificateReportObject(student,leavingCertificate));
    		jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "LEAVING_CERTIFICATE" , parameterMap);
            if (jasperPrint != null) {
            		response.setContentType("application/pdf");
                    response.setCharacterEncoding("UTF-8");
                    response.setHeader("Content-Disposition","attachment;filename=report.pdf");
                    reportsControllerUtility.getPdfExporter(jasperPrint,outputStream).exportReport();
                    response.getOutputStream().write(outputStream.toByteArray());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }
	
	private ModelAndView getLeavingCertificateForm(LeavingCertificateForm leavingCertificateFormObject,ModelMap model){
        model.put("leavingcertificate", leavingCertificateFormObject);
    	return new ModelAndView("leavingcertificate",model);
	}

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
    	binder.setValidator(leavingCertificateValidator);
    }
}