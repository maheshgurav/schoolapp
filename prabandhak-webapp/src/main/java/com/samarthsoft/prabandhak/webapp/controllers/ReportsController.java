package com.samarthsoft.prabandhak.webapp.controllers;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.ModelAttributesCommon;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.entities.Caste;
import com.samarthsoft.prabandhak.entities.Category;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.ScholarshipType;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.utilities.ReportsControllerUtility;

@Controller
public class ReportsController {
	@Autowired
	private ReportsControllerUtility reportsControllerUtility;
	
    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public ModelAndView viewReports(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
        return new ModelAndView("reports");
    }
    
    @ResponseBody
    @RequestMapping(value = "/getreportashtml", method = RequestMethod.GET)
    public String getReportAsHtml(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        JasperPrint jasperPrint = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
        	String reportType = RequestCommon.getAttributeValueFromRequest("typeofreport",request);
    		Map<String, Object> parameterMap = new HashMap<String, Object>();
    		parameterMap.put("message", "This is student and class wise report of school");
    		List<Object> result = reportsControllerUtility.getStudentsForReport(reportsControllerUtility.getFilterForReport(reportType,request));
            if(reportType.equals("0") || reportType.equals("3") || reportType.equals("6") || reportType.equals("1")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "CLASSWISE_STUDENTS" , parameterMap);
            }
            if(reportType.equals("8")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "ICARDS" , parameterMap);
            }
            if(reportType.equals("9")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getGRReportObjects(), request, "GR" , parameterMap);
            }
            if(reportType.equals("10")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getStudentInfoForAttendanceReport(reportsControllerUtility.getFilterForReport(reportType,request)), request, "ATTENDANCE" , parameterMap);
            }
            if(reportType.equals("11")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getStudentInfoForAttendanceReport(reportsControllerUtility.getFilterForReport(reportType,request)), request, "BLANK_ATTENDANCE" , parameterMap);
            }
            if(reportType.equals("12")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getAttendanceSummary(), request, "ATTENDANCE_PAGE_FIRST" , reportsControllerUtility.getAttendanceReportParameters());
            }
    		if (jasperPrint != null) {
        	    reportsControllerUtility.getHtmlExporter(jasperPrint,outputStream).exportReport();
        	}
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }
    
    @ResponseBody
    @RequestMapping(value = "/getreportaspdf", method = RequestMethod.GET)
    public String getReportAsPDF(HttpServletRequest request, 
            HttpServletResponse response,ModelMap model) {
        JasperPrint jasperPrint = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
        	String reportType = RequestCommon.getAttributeValueFromRequest("typeofreport",request);
    		Map<String, Object> parameterMap = new HashMap<String, Object>();
    		parameterMap.put("message", "This is student and class wise report of school");
    		List<Object> result = reportsControllerUtility.getStudentsForReport(reportsControllerUtility.getFilterForReport(reportType,request));
            if(reportType.equals("0") || reportType.equals("3") || reportType.equals("6") || reportType.equals("1")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "CLASSWISE_STUDENTS" , parameterMap);
            }
            if(reportType.equals("8")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "ICARDS" , parameterMap);
            }
            if(reportType.equals("9")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getGRReportObjects(), request, "GR" , parameterMap);
            }
            if(reportType.equals("10")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getStudentInfoForAttendanceReport(reportsControllerUtility.getFilterForReport(reportType,request)), request, "BLANK_ATTENDANCE_PAGE_SECOND" , parameterMap);
            }
            if(reportType.equals("11")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getStudentInfoForAttendanceReport(reportsControllerUtility.getFilterForReport(reportType,request)), request, "BLANK_ATTENDANCE" , parameterMap);
            }
            if(reportType.equals("12")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getAttendanceSummary(), request, "ATTENDANCE_PAGE_FIRST" , reportsControllerUtility.getAttendanceReportParameters());
            }
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
        return null;
    }


	@ModelAttribute(value="classes")
    private List<ClassInfo> setClassesInModel(HttpServletRequest request){
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
    	return ModelAttributesCommon.getClassInfoForModelAttributes(applicationSession);
    }
	
	@ModelAttribute(value="exams")
    private List<Object> setExamsInModel(HttpServletRequest request){
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
    	return ModelAttributesCommon.getExamsForModelAttributes(applicationSession);
    }
	
    @ModelAttribute(value="castes")
    private List<Caste> setCastesInModel(){
    	return ModelAttributesCommon.getCastesForModelAttributes();
    }
    
    @ModelAttribute(value="categories")
    private List<Category> setCategoriesInModel(){
    	return ModelAttributesCommon.getCategoriesForModelAttributes();
    }
        
    @ModelAttribute(value="scholarshiptypes")
    private List<ScholarshipType> setScholarshipTypesInModel(){
    	return ModelAttributesCommon.getScholarshipTypesForModelAttributes();
    }
}