package com.samarthsoft.prabandhak.webapp.controllers;

import java.io.ByteArrayOutputStream;
import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Caste;
import com.samarthsoft.prabandhak.entities.Category;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.ScholarshipType;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.enums.Gender;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.reports.AttendanceSheetStudentDetails;
import com.samarthsoft.prabandhak.reports.AttendanceSummary;
import com.samarthsoft.prabandhak.reports.StudentClassificationForAttendance;
import com.samarthsoft.prabandhak.reports.StudentCountByGender;
import com.samarthsoft.prabandhak.utilities.ReportsControllerUtility;

import net.sf.jasperreports.engine.JRPrintAnchor;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperPrint;

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
    		if(reportType.equals("0") || reportType.equals("3") || reportType.equals("6") || reportType.equals("1")){
    			List<Object> result = reportsControllerUtility.getStudentsForReport(reportsControllerUtility.getFilterForReport(reportType,request));
                jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "CLASSWISE_STUDENTS" , parameterMap);
            }
            if(reportType.equals("8")){
            	List<Object> result = reportsControllerUtility.getStudentsForReport(reportsControllerUtility.getFilterForReport(reportType,request));
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
            	String classGuid = RequestCommon.getAttributeValueFromRequest("classguid", request);
            	String month = RequestCommon.getAttributeValueFromRequest("month", request);
            	String year = RequestCommon.getAttributeValueFromRequest("year", request);
            	String standard = "";
            	String division = "";
            	if (!ValidatorCommon.checkFieldNullOrEmpty(classGuid)) {
            		standard = classGuid.split("-")[0].trim();
            		division = classGuid.split("-")[1].trim();
        		}
        		List<Filter> filteredList = new ArrayList<Filter>();
        		filteredList.add(new Filter("standard",standard,RestrictionType.EQ));
        		filteredList.add(new Filter("division",division,RestrictionType.EQ));		
        		filteredList.add(new Filter("isAlumni",false,RestrictionType.EQ));
        		List<Object> students = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, filteredList, null);
        		int totalBoys = 0;
				int totalGirls = 0;
        		for (Object object : students) {
					Student student = (Student) object;
					if(student.getGender() == Gender.Male)
						totalBoys++;
					else totalGirls++;
				}
        		AttendanceSummary attendanceSummary = reportsControllerUtility.getAttendanceSummary(standard,division,totalBoys,totalGirls,month,year);
        		List<Object> summary = new ArrayList<Object>();
            	summary.add(attendanceSummary);
            	jasperPrint = reportsControllerUtility.fillJasperReport(summary, request, "ATTENDANCE_PAGE_FIRST" , reportsControllerUtility.getAttendanceReportParameters(students));
            }
            if(reportType.equals("13")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(new ArrayList<Object>(), request, "ATTENDANCE_PAGE_THIRD" , parameterMap);
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

    		parameterMap.put("school_organisation", "Bharati Vidyapeeth");
    		parameterMap.put("school_name", "M K Gurav Vidyalaya");
    		parameterMap.put("school_address", "Daundaj, Tal : Purandar, Dist : Pune, Pin Code : 412 305");
    		parameterMap.put("school_grant_details", "School Grant No. S/1/PA-NS-PR Poona-4. Dt. 12 May 1964");
    		parameterMap.put("other_info", "(Grant in Code Rule No. 17)");
    		
    		List<Object> result = reportsControllerUtility.getStudentsForReport(reportsControllerUtility.getFilterForReport(reportType,request));
            if(reportType.equals("0") || reportType.equals("3") || reportType.equals("6") || reportType.equals("1")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "CLASSWISE_STUDENTS" , parameterMap);
            	//reportsControllerUtility.generateHTML(result, "students.vm");
            }
            if(reportType.equals("8")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "ICARDS" , parameterMap);
            }
            if(reportType.equals("9")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getGRReportObjects(), request, "GR" , parameterMap);
            }
            if(reportType.equals("10")){
            	List<Filter> filters = reportsControllerUtility.getFilterForReport(reportType,request);
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getStudentInfoForAttendanceReport(filters), request, "BLANK_ATTENDANCE_PAGE_SECOND" , parameterMap);
            }
            if(reportType.equals("11")){
            	jasperPrint = reportsControllerUtility.fillJasperReport(reportsControllerUtility.getStudentInfoForAttendanceReport(reportsControllerUtility.getFilterForReport(reportType,request)), request, "BLANK_ATTENDANCE" , parameterMap);
            }
            if(reportType.equals("12")){
            	String classGuid = RequestCommon.getAttributeValueFromRequest("classguid", request);
            	String month = RequestCommon.getAttributeValueFromRequest("month", request);
            	String year = RequestCommon.getAttributeValueFromRequest("year", request);
            	String standard = "";
            	String division = "";
            	if (!ValidatorCommon.checkFieldNullOrEmpty(classGuid)) {
            		standard = classGuid.split("-")[0].trim();
            		division = classGuid.split("-")[1].trim();
        		}
        		List<Filter> filteredList = new ArrayList<Filter>();
        		filteredList.add(new Filter("standard",standard,RestrictionType.EQ));
        		filteredList.add(new Filter("division",division,RestrictionType.EQ));		
        		filteredList.add(new Filter("isAlumni",false,RestrictionType.EQ));
        		List<Object> students = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, filteredList, null);
        		int totalBoys = 0;
				int totalGirls = 0;
        		for (Object object : students) {
					Student student = (Student) object;
					if(student.getGender() == Gender.Male)
						totalBoys++;
					else totalGirls++;
				}
        		AttendanceSummary attendanceSummary = reportsControllerUtility.getAttendanceSummary(standard,division,totalBoys,totalGirls,month,year);
        		
        		List<StudentCountByGender> studentCountByGender = new ArrayList<StudentCountByGender>();
        		StudentCountByGender studentCountByGender2 = new StudentCountByGender();
        		studentCountByGender2.setFemaleCount(20);
        		studentCountByGender2.setMaleCount(10);
        		studentCountByGender2.setName("Jain");
        		
        		StudentCountByGender studentCountByGender3 = new StudentCountByGender();
        		studentCountByGender3.setFemaleCount(20);
        		studentCountByGender3.setMaleCount(10);
        		studentCountByGender3.setName("Buddha");
        		studentCountByGender.add(studentCountByGender2);
        		studentCountByGender.add(studentCountByGender3);
        		attendanceSummary.setStudentCountByGender(studentCountByGender);
        		
        		List<StudentClassificationForAttendance> studentClassificationForAttendance = new ArrayList<StudentClassificationForAttendance>();
        		StudentClassificationForAttendance s = new StudentClassificationForAttendance();
        		s.setFeeTypeName("PTC");

        		StudentClassificationForAttendance s1 = new StudentClassificationForAttendance();
        		s1.setFeeTypeName("HETC");
        				
        		studentClassificationForAttendance.add(s);
        		studentClassificationForAttendance.add(s1);
        		attendanceSummary.setStudentClassificationForAttendance(studentClassificationForAttendance);
        		
        		List<StudentCountByGender> casteWiseStudentCountByGenderOnMonthStart = new ArrayList<StudentCountByGender>();
        		studentCountByGender2.setName("SC");
        		casteWiseStudentCountByGenderOnMonthStart.add(studentCountByGender2);
        		studentCountByGender3.setName("ST");
        		casteWiseStudentCountByGenderOnMonthStart.add(studentCountByGender3);
        		
        		List<StudentCountByGender> casteWiseStudentCountByGenderOnMonthEnd = new ArrayList<StudentCountByGender>();
        		StudentCountByGender studentCountByGender4 = new StudentCountByGender();
        		studentCountByGender4.setFemaleCount(20);
        		studentCountByGender4.setMaleCount(10);
        		studentCountByGender4.setName("Jain1");
        		
        		StudentCountByGender studentCountByGender5 = new StudentCountByGender();
        		studentCountByGender5.setFemaleCount(20);
        		studentCountByGender5.setMaleCount(10);
        		studentCountByGender5.setName("Buddha1");
        		casteWiseStudentCountByGenderOnMonthEnd.add(studentCountByGender4);
        		casteWiseStudentCountByGenderOnMonthEnd.add(studentCountByGender5);        		
        		
        		attendanceSummary.setCasteWiseStudentCountByGenderOnMonthStart(casteWiseStudentCountByGenderOnMonthStart);
        		attendanceSummary.setCasteWiseStudentCountByGenderOnMonthEnd(casteWiseStudentCountByGenderOnMonthEnd);
        		
        		List<Object> summary = new ArrayList<Object>();
            	summary.add(attendanceSummary);
        		jasperPrint = reportsControllerUtility.fillJasperReport(summary, request, "ATTENDANCE_PAGE_FIRST" , reportsControllerUtility.getAttendanceReportParameters(students));
        		
            	List<Object> result1 = reportsControllerUtility.getStudentInfoForAttendanceReport(reportsControllerUtility.getFilterForReport(reportType,request));
                for (int i = 0; i<100;i++) {
                	result1.add(new AttendanceSheetStudentDetails());
				}

            	JasperPrint jasperPrint1 = reportsControllerUtility.fillJasperReport(result1, request, "BLANK_ATTENDANCE_PAGE_SECOND" , parameterMap);
                
                JasperPrint jasperPrint2 = reportsControllerUtility.fillJasperReport(result1, request, "BLANK_ATTENDANCE" , parameterMap);

                List<JRPrintPage> pages = jasperPrint1.getPages();
            	for (JRPrintPage jrPrintPage : pages) {
            		jasperPrint.addPage(jrPrintPage);
				}
            	
            	List<JRPrintPage> pages1 = jasperPrint2.getPages();
            	for (JRPrintPage jrPrintPage : pages1) {
            		jasperPrint.addPage(jrPrintPage);
				}
            	
            }
            if(reportType.equals("13")){
            	List<Object> testObjects = new ArrayList<Object>();
            	testObjects.add(new Object());
            	jasperPrint = reportsControllerUtility.fillJasperReport(testObjects, request, "ATTENDANCE_PAGE_THIRD" , parameterMap);
            }
            if (result != null) {
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