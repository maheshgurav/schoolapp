package com.samarthsoft.prabandhak.webapp.controllers;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.JsonConverter;
import com.samarthsoft.prabandhak.common.ModelAttributesCommon;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.LeavingCertificate;
import com.samarthsoft.prabandhak.entities.Name;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.utilities.ReportsControllerUtility;
import com.samarthsoft.prabandhak.utilities.StudentCertificatesControllerUtility;

@Controller
public class StudentCertificatesController {
	
	@Autowired
	private StudentCertificatesControllerUtility studentCertificatesControllerUtility;
	@Autowired
	private ReportsControllerUtility reportsControllerUtility;

	@RequestMapping(value = "/certificates", method = RequestMethod.GET)
	public ModelAndView viewCertificates(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("student_certificates");
	}
	
	@RequestMapping(value = "/generatelcs", method = RequestMethod.GET)
	public ModelAndView generateLcs(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("student_leaving_certificates");
	}
	
	@RequestMapping(value = "/showstudents", method = RequestMethod.GET)
    @ResponseBody
    public String loadStudents(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String classGuid = request.getParameter("class");
    	List<Object> students = studentCertificatesControllerUtility.getStudents(classGuid, RequestCommon.getApplicationSession(request).getOrganizationGuid());
    	JSONArray array = new JSONArray();
    	try {
	       	 for (Object object : students) {
		        	Student student = (Student) object;
		        	JSONObject obj = new JSONObject();
		        	obj.put("roll", student.getRollNumber());
		        	Name studentName = (Name) JsonConverter.fromJson(student.getName(), Name.class);
		            obj.put("name", studentName.toString());
		            obj.put("guid",student.getGuid());
		            array.put(obj);
	       	 }
		}catch(Exception ex){
			ex.printStackTrace();
		}
    	return array.toString();
    }
	
	@RequestMapping(value = "/generatebonafides", method = RequestMethod.GET)
	public ModelAndView generateBonafides(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("student_bonafide_certificates");
	}
	
	@RequestMapping(value = "/generatebonafidecertificatespdf", method = RequestMethod.GET)
    public String generateBonafideCertificatesPdf(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String selectedStudents = request.getParameter("selected_students");
    	JasperPrint jasperPrint = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
        	Map<String, Object> parameterMap = new HashMap<String, Object>();
        	jasperPrint = reportsControllerUtility.fillJasperReport(studentCertificatesControllerUtility.getBonafideCertificates(selectedStudents), request, "BONAFIED" , parameterMap);
            if (jasperPrint != null) {
        	    	response.setContentType("application/pdf");
                    response.setCharacterEncoding("UTF-8");
                    response.setHeader("Content-Disposition","attachment;filename=report.pdf");
                    reportsControllerUtility.getPdfExporter(jasperPrint,outputStream).exportReport();
                    response.getOutputStream().write(outputStream.toByteArray());
                    return null;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@RequestMapping(value = "/generatebonafidecertificates", method = RequestMethod.GET)
    @ResponseBody
    public String generateBonafideCertificates(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String selectedStudents = request.getParameter("selected_students");
    	JasperPrint jasperPrint = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
        	Map<String, Object> parameterMap = new HashMap<String, Object>();
        	jasperPrint = reportsControllerUtility.fillJasperReport(studentCertificatesControllerUtility.getBonafideCertificates(selectedStudents), request, "BONAFIED" , parameterMap);
            if (jasperPrint != null) {
            	reportsControllerUtility.getHtmlExporter(jasperPrint,outputStream).exportReport();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }

	@RequestMapping(value = "/generateleavingcertificates", method = RequestMethod.GET)
    @ResponseBody
    public String generateLeavingCertificates(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String selectedStudents = request.getParameter("selected_students");
    	
    	String progress = request.getParameter("progress");
    	String conduct = request.getParameter("conduct");
    	String reasonOfLeaving = request.getParameter("reasonOfLeaving");
    	String remark = request.getParameter("remark");
    	String leavingDate = request.getParameter("leavingDate");
    	String studyingSinceMonthAndYear = request.getParameter("studyingSinceMonthAndYear");
    	
    	String[] studentGuids = selectedStudents.split(",");
    	List<Object> studentLeavingCertificates = new ArrayList<Object>();
    	for (String studentGuid : studentGuids) {
        	LeavingCertificate leavingCertificate = new LeavingCertificate();
        	leavingCertificate.setConduct(conduct);
        	leavingCertificate.setProgress(progress);
        	leavingCertificate.setReasonOfLeaving(reasonOfLeaving);
        	leavingCertificate.setRemark(remark);
        	leavingCertificate.setDateOfLeaving(DateUtility.convertStringToTime(leavingDate));
        	leavingCertificate.setStudentGuid(studentGuid);
        	leavingCertificate.setStudyingSinceMonthAndYear(studyingSinceMonthAndYear);
        	studentLeavingCertificates.add(leavingCertificate);
		}
    	
    	boolean resultFromDb = DBCommunicator.getApiServices().getGenericApi().bulkUpdate(studentLeavingCertificates);

    	if(resultFromDb){
			List<Object> result = new ArrayList<Object>();
			Map<String,Object> leavingCertificates = studentCertificatesControllerUtility.getLeavingCertificates(selectedStudents);
			List<Object> students = studentCertificatesControllerUtility.getStudents(selectedStudents);
			for (Object object : students) {
				Student student = (Student)object;
				LeavingCertificate leavingCertificate = (LeavingCertificate)leavingCertificates.get(student.getGuid());
				if(leavingCertificate!=null)
					result.add(reportsControllerUtility.getLeavingCertificateReportObject(student,leavingCertificate));
			}
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JasperPrint jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "LEAVING_CERTIFICATE" , new HashMap<String, Object>());
	        if (jasperPrint != null) {
	        	try {
					reportsControllerUtility.getHtmlExporter(jasperPrint,outputStream).exportReport();
					return outputStream.toString();
	        	} catch (JRException e) {
					e.printStackTrace();
				}
	        }
    	}
    	return "";
    }
	
	@RequestMapping(value = "/generatelcpdf", method = RequestMethod.GET)
    public String generateLeavingCertificatesPdf(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String selectedStudents = request.getParameter("selected_students");
    	
    	String progress = request.getParameter("progress");
    	String conduct = request.getParameter("conduct");
    	String reasonOfLeaving = request.getParameter("reasonOfLeaving");
    	String remark = request.getParameter("remark");
    	String leavingDate = request.getParameter("leavingDate");
    	String studyingSinceMonthAndYear = request.getParameter("studyingSinceMonthAndYear");
    	
    	String[] studentGuids = selectedStudents.split(",");
    	List<Object> studentLeavingCertificates = new ArrayList<Object>();
    	for (String studentGuid : studentGuids) {
        	LeavingCertificate leavingCertificate = new LeavingCertificate();
        	leavingCertificate.setConduct(conduct);
        	leavingCertificate.setProgress(progress);
        	leavingCertificate.setReasonOfLeaving(reasonOfLeaving);
        	leavingCertificate.setRemark(remark);
        	leavingCertificate.setDateOfLeaving(DateUtility.convertStringToTime(leavingDate));
        	leavingCertificate.setStudentGuid(studentGuid);
        	leavingCertificate.setStudyingSinceMonthAndYear(studyingSinceMonthAndYear);
        	studentLeavingCertificates.add(leavingCertificate);
		}
    	
    	boolean resultFromDb = DBCommunicator.getApiServices().getGenericApi().bulkUpdate(studentLeavingCertificates);

    	if(resultFromDb){
			List<Object> result = new ArrayList<Object>();
			Map<String,Object> leavingCertificates = studentCertificatesControllerUtility.getLeavingCertificates(selectedStudents);
			List<Object> students = studentCertificatesControllerUtility.getStudents(selectedStudents);
			for (Object object : students) {
				Student student = (Student)object;
				LeavingCertificate leavingCertificate = (LeavingCertificate)leavingCertificates.get(student.getGuid());
				if(leavingCertificate!=null)
					result.add(reportsControllerUtility.getLeavingCertificateReportObject(student,leavingCertificate));
			}
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    	JasperPrint jasperPrint = null;
	        try {
	        	Map<String, Object> parameterMap = new HashMap<String, Object>();
	        	jasperPrint = reportsControllerUtility.fillJasperReport(result, request, "LEAVING_CERTIFICATE" , parameterMap);
	            if (jasperPrint != null) {
	        	    	response.setContentType("application/pdf");
	                    response.setCharacterEncoding("UTF-8");
	                    response.setHeader("Content-Disposition","attachment;filename=report.pdf");
	                    reportsControllerUtility.getPdfExporter(jasperPrint,outputStream).exportReport();
	                    response.getOutputStream().write(outputStream.toByteArray());
	                    return null;
	            }
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
    	}
        return null;
    }

	
    @ModelAttribute(value="classes")
    private List<ClassInfo> setClassesInModel(HttpServletRequest request){
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
    	return ModelAttributesCommon.getClassInfoForModelAttributes(applicationSession);
    }
}