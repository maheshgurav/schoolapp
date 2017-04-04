package com.samarthsoft.prabandhak.webapp.controllers;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONArray;
import org.json.JSONObject;

import com.samarthsoft.prabandhak.common.JsonConverter;
import com.samarthsoft.prabandhak.common.ModelAttributesCommon;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.Exam;
import com.samarthsoft.prabandhak.entities.ExamResult;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.Grades;
import com.samarthsoft.prabandhak.entities.Name;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.entities.StudentAndMark;
import com.samarthsoft.prabandhak.entities.Subject;
import com.samarthsoft.prabandhak.entities.SubjectAndMarks;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.form.entities.ExamFormObject;
import com.samarthsoft.prabandhak.reports.ReportCard;
import com.samarthsoft.prabandhak.utilities.ExaminationControllerUtility;
import com.samarthsoft.prabandhak.utilities.ReportsControllerUtility;
import com.samarthsoft.prabandhak.utilities.StudentControllerUtility;

@Controller
public class ExaminationController {

	@Autowired
	private ExaminationControllerUtility examinationControllerUtility;
	@Autowired
	private StudentControllerUtility studentControllerUtility;
	@Autowired
	private ReportsControllerUtility reportsControllerUtility;
	
	@RequestMapping(value = "/fillmarks", method = RequestMethod.GET)
	public ModelAndView renderFillMarksOfExamForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ExamFormObject examFormObject = new ExamFormObject();
		examFormObject.setClassGuid(RequestCommon
				.getApplicationSession(request).getAssociatedClassGuid());
		model.put("fillmarks", examFormObject);
		return new ModelAndView("fillexammarks", model);
	}

	@RequestMapping(value = "/fillindividualstudentmarks", method = RequestMethod.GET)
	public ModelAndView renderIndividualStudentFillMarks(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String studentGuid = RequestCommon.getAttributeValueFromRequest("id",
				request);
		model.put("student_guid", studentGuid);
		Student student = studentControllerUtility.getStudent(studentGuid);
		List<Object> exams = examinationControllerUtility.getExams(student
				.getStandard());
		model.put("exams", exams);
		model.put("class_guid",
				student.getStandard() + "-" + student.getDivision());
		return new ModelAndView("fillexammarks_individual", model);
	}
	
	@RequestMapping(value = "/savesubjectandmarks", method = RequestMethod.GET)
	@ResponseBody
	public String submitIndividualStudentFillMarks(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String examGuid = request.getParameter("exam");
		String studentGuid = request.getParameter("studentGuid");
		String classAndDivision = request.getParameter("classguid");
		String marks = request.getParameter("marks");
		System.out.println(examGuid);
		System.out.println(studentGuid);
		System.out.println(classAndDivision);
		System.out.println(marks);
		if(examinationControllerUtility.buildAndSaveExamResultObjects(examGuid, classAndDivision, studentGuid, marks))
			return "fail";
		return "success";
	}

	@RequestMapping(value = "/fillmarks", method = RequestMethod.POST)
	public ModelAndView saveMarks(@Valid ExamFormObject examFormObject,
			BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		if (!ValidatorCommon.checkFieldNullOrEmpty(examFormObject.getMarks())) {
			String marks = examFormObject.getMarks();
			String examMarks = "["
					+ marks.substring(0, examFormObject.getMarks().length() - 1)
					+ "]";
			examFormObject.setMarks(examMarks);
		}
		model.put("fillmarks", examFormObject);
		if (!examinationControllerUtility.saveExamMarks(examFormObject))
			model.put("error", "Error while saving marks");
		return new ModelAndView("fillexammarks", model);
	}

	@RequestMapping(value = "/loadexamwisesubjects", method = RequestMethod.GET)
	@ResponseBody
	public String loadSubjects(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String examGuid = request.getParameter("exam");
		List<SubjectAndMarks> result = examinationControllerUtility
				.getSubjectAndMarks(examGuid);
		JSONArray array = new JSONArray();
		try {
			Iterator<SubjectAndMarks> st = result.iterator();
			Map<String, String> subjectNameMap = getSubjectNameWithCode(ModelAttributesCommon
					.getSubjectsForModelAttributes());
			while (st.hasNext()) {
				Map<?, ?> map = (Map<?, ?>) st.next();
				JSONObject obj = new JSONObject();
				obj.put("subject", subjectNameMap.get(map.get("subject")));
				obj.put("subject_code", map.get("subject"));
				obj.put("marks", map.get("marks"));
				obj.put("marksRequiredForPassing",
						map.get("marksRequiredForPassing"));
				array.put(obj);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return array.toString();
	}

	@RequestMapping(value = "/loadsubjectandmarks", method = RequestMethod.GET)
	@ResponseBody
	public String loadSubjectAndMarks(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String examGuid = request.getParameter("exam");
		String studentGuid = request.getParameter("studentGuid");
		String classAndDivision = request.getParameter("classguid");
		List<SubjectAndMarks> result = examinationControllerUtility
				.getSubjectAndMarks(examGuid);
		JSONArray array = new JSONArray();
		try {
			Iterator<SubjectAndMarks> st = result.iterator();
			Map<String, String> subjectNameMap = getSubjectNameWithCode(ModelAttributesCommon
					.getSubjectsForModelAttributes());
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(new Filter("examResultKey.examGuid", examGuid,
					RestrictionType.EQ));
			filters.add(new Filter("examResultKey.studentGuid", studentGuid,
					RestrictionType.EQ));
			filters.add(new Filter("examResultKey.standardAndDivision",
					classAndDivision, RestrictionType.EQ));
			List<String> subjects = new ArrayList<String>();
			Map<String, String> subjectCodeAndName = new HashMap<String, String>();
			List<String> subjectDataPresent = new ArrayList<String>();
			while (st.hasNext()) {
				Map<?, ?> map = (Map<?, ?>) st.next();
				subjects.add(map.get("subject").toString());
				subjectCodeAndName.put(map.get("subject").toString(),
						subjectNameMap.get(map.get("subject")));
			}
			filters.add(new Filter("examResultKey.subjectGuid", subjects,
					RestrictionType.IN));
			List<Object> examResult = DBCommunicator.getApiServices()
					.getGenericApi()
					.getFilteredList(ExamResult.class, filters, null);
			for (Object object : examResult) {
				ExamResult examResultObj = (ExamResult) object;
				JSONObject obj = new JSONObject();
				obj.put("subject_name", subjectCodeAndName.get(examResultObj
						.getExamResultKey().getSubjectGuid()));
				obj.put("subject_code", examResultObj.getExamResultKey()
						.getSubjectGuid());
				obj.put("marks", examResultObj.getMarks());
				array.put(obj);
				subjectDataPresent.add(examResultObj.getExamResultKey()
						.getSubjectGuid());
			}
			for (String subjectPresent : subjects) {
				if (!subjectDataPresent.contains(subjectPresent)) {
					JSONObject obj = new JSONObject();
					obj.put("subject_name",
							subjectCodeAndName.get(subjectPresent));
					obj.put("subject_code", subjectPresent);
					obj.put("marks", "0");
					array.put(obj);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return array.toString();
	}

	private Map<String, String> getSubjectNameWithCode(List<Subject> subjects) {
		Map<String, String> result = new HashMap<String, String>();
		for (Subject subject : subjects) {
			result.put(subject.getCode(), subject.getName());
		}
		return result;
	}

	@RequestMapping(value = "/loadsubjectwisestudents", method = RequestMethod.GET)
	@ResponseBody
	public String loadStudents(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String subjectGuid = request.getParameter("subjectguid");
		String classGuid = request.getParameter("class");
		String examGuid = request.getParameter("examguid");
		List<Object> students = examinationControllerUtility.getStudents(
				classGuid, RequestCommon.getApplicationSession(request)
						.getOrganizationGuid(), subjectGuid);

		List<String> studentGuids = new ArrayList<String>();
		for (Object object : students) {
			Student student = (Student) object;
			studentGuids.add(student.getGuid());
		}
		Map<String, String> examResult = examinationControllerUtility
				.getExamResult(studentGuids, subjectGuid, examGuid);

		JSONArray array = new JSONArray();
		try {
			for (Object object : students) {
				Student student = (Student) object;
				JSONObject obj = new JSONObject();
				obj.put("roll", student.getRollNumber());
				Name studentName = (Name) JsonConverter.fromJson(
						student.getName(), Name.class);
				obj.put("name", studentName.toString());
				obj.put("id", student.getGuid());
				obj.put("marks", examResult.get(student.getGuid()));
				array.put(obj);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return array.toString();
	}

	@RequestMapping(value = "/loadstudentexams", method = RequestMethod.GET)
	@ResponseBody
	public String loadExams(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String classGuid = request.getParameter("class");
		if (classGuid != null) {
			String standard = classGuid.split("-")[0];
			List<Object> exams = examinationControllerUtility
					.getExams(standard);
			JSONArray array = new JSONArray();
			try {
				for (Object exam : exams) {
					Exam examObj = (Exam) exam;
					JSONObject obj = new JSONObject();
					obj.put("id", examObj.getGuid());
					obj.put("name", examObj.getName());
					array.put(obj);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return array.toString();
		}
		return "";
	}

	@RequestMapping(value = "/saveorupdatemarks", method = RequestMethod.GET)
	@ResponseBody
	public String saveOrUpdateMarks(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String examGuid = request.getParameter("examguid");
		String subjectGuid = request.getParameter("subjectguid");
		String studentAndMarks = request.getParameter("studentandmarks");
		List<StudentAndMark> studentAndMarksList = examinationControllerUtility
				.getSubjectAndMarksObjectList(studentAndMarks);
		List<Object> examResults = new ArrayList<Object>();
		for (StudentAndMark studentAndMark : studentAndMarksList) {
			examResults.add(examinationControllerUtility.getExamResultObject(
					examGuid, subjectGuid, studentAndMark));
		}
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.bulkUpdate(examResults);
		if (result)
			return "success";
		return "fail";
	}
	
	@RequestMapping(value = "/getstudentreportcard", method = RequestMethod.GET)
	@ResponseBody
	public String getStudentReportCard(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
		String examGuid = request.getParameter("exam");
		String studentGuid = request.getParameter("studentGuid");
		JasperPrint jasperPrint = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("message", "This is student and class wise report of school");
        jasperPrint = reportsControllerUtility.fillJasperReport(examinationControllerUtility.getReportCard(examGuid, studentGuid), request, "STUDENT_REPORT_CARD" , parameterMap);
		if (jasperPrint != null) {
    	    reportsControllerUtility.getHtmlExporter(jasperPrint,outputStream).exportReport();
    	    return outputStream.toString();
			}
		}
		catch(Exception e) {
            e.printStackTrace();
        }
		return "error";
	}
	
	@RequestMapping(value = "/getstudentreportcardpdf", method = RequestMethod.GET)
	@ResponseBody
	public String getStudentReportCardPdf(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
        JasperPrint jasperPrint = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
    		String examGuid = request.getParameter("exam");
    		String studentGuid = request.getParameter("studentGuid");
    		Map<String, Object> parameterMap = new HashMap<String, Object>();
    		parameterMap.put("message", "This is student and class wise report of school");
    		jasperPrint = reportsControllerUtility.fillJasperReport(examinationControllerUtility.getReportCard(examGuid, studentGuid), request, "STUDENT_REPORT_CARD" , parameterMap);
    		if (jasperPrint != null) {
    	    	response.setContentType("application/pdf");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition","attachment;filename=result.pdf");
                reportsControllerUtility.getPdfExporter(jasperPrint,outputStream).exportReport();
                response.getOutputStream().write(outputStream.toByteArray());
        	}
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return outputStream.toString();
	}
	
	@ModelAttribute(value = "classes")
	private List<ClassInfo> setClassesInModel(HttpServletRequest request) {
		ApplicationSession applicationSession = RequestCommon
				.getApplicationSession(request);
		return ModelAttributesCommon
				.getClassInfoForModelAttributes(applicationSession);
	}

	@ModelAttribute(value = "exams")
	private List<Object> setExamsInModel(HttpServletRequest request) {
		ApplicationSession applicationSession = RequestCommon
				.getApplicationSession(request);
		return ModelAttributesCommon
				.getExamsForModelAttributes(applicationSession);
	}
}