package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.samarthsoft.prabandhak.common.CachedDataStore;
import com.samarthsoft.prabandhak.common.JsonConverter;
import com.samarthsoft.prabandhak.common.ModelAttributesCommon;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.Exam;
import com.samarthsoft.prabandhak.entities.ExamResult;
import com.samarthsoft.prabandhak.entities.ExamResultKey;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.entities.StudentAndMark;
import com.samarthsoft.prabandhak.entities.StudentSubjectDetails;
import com.samarthsoft.prabandhak.entities.Subject;
import com.samarthsoft.prabandhak.entities.SubjectAndMarks;
import com.samarthsoft.prabandhak.entities.SubjectsForExam;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.form.entities.ExamFormObject;
import com.samarthsoft.prabandhak.reports.ReportCard;

@Component
public class ExaminationControllerUtility {
	@Autowired
	private StudentControllerUtility studentControllerUtility;
	
	public List<Object> getSubjectDetails(String examGuid) {
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("examGuid", examGuid, RestrictionType.EQ));
		return DBCommunicator.getApiServices().getGenericApi()
				.getFilteredList(SubjectsForExam.class, filters, null);
	}

	public List<Object> getStudents(String classGuid, String organizationGuid,
			String subjectGuid) {
		List<Filter> filters = new ArrayList<Filter>();
		if (!ValidatorCommon.checkFieldNullOrEmpty(classGuid)) {
			filters.add(new Filter("standard", classGuid.split("-")[0].trim(),
					RestrictionType.EQ));
			filters.add(new Filter("division", classGuid.split("-")[1].trim(),
					RestrictionType.EQ));
		}
		// TODO:Enable organization filter
		// filters.add(new Filter("organizationGuid", organizationGuid, RestrictionType.EQ));
		 //filters.add(new Filter("schoolGuid", organizationGuid, RestrictionType.EQ));
		/*
		 * List<String> subjectsList = new ArrayList<String>();
		 * subjectsList.add(subjectGuid);
		 */
		filters.add(new Filter("subjects", subjectGuid, RestrictionType.LIKE));
		//filters.add(new Filter("isAlumni", false , RestrictionType.EQ));
		return DBCommunicator.getApiServices().getGenericApi()
				.getFilteredList(StudentSubjectDetails.class, filters, null);
	}

	public String getSubjectName(String subjectGuid) {
		for (Object object : CachedDataStore.getSubjects()) {
			Subject subject = (Subject) object;
			if (subject.getGuid().equals(subjectGuid))
				return subject.getName();
		}
		return "";
	}

	public List<StudentAndMark> getSubjectAndMarksObjectList(
			String subjectAndMarkJson) {
		Gson gson = new Gson();
		List<StudentAndMark> result = new ArrayList<StudentAndMark>();
		JsonParser parser = new JsonParser();
		JsonArray jsonArray = parser.parse(subjectAndMarkJson).getAsJsonArray();
		for (JsonElement object : jsonArray) {
			StudentAndMark studentAndMark = gson.fromJson(object,
					StudentAndMark.class);
			result.add(studentAndMark);
		}
		return result;
	}

	public ExamResult getExamResultObject(String examGuid, String subjectGuid,
			StudentAndMark studentAndMark) {
		ExamResult examResult = new ExamResult();
		ExamResultKey examResultKey = new ExamResultKey();
		examResultKey.setExamGuid(examGuid);
		examResultKey.setStudentGuid(studentAndMark.getGuid());
		examResultKey.setSubjectGuid(subjectGuid);
		examResult.setExamResultKey(examResultKey);
		examResult.setMarks(studentAndMark.getMarks());
		return examResult;
	}

	public List<Object> getExams(String standard) {
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("standards", standard, RestrictionType.LIKE));
		return DBCommunicator.getApiServices().getGenericApi()
				.getFilteredList(Exam.class, filters, null);
	}
	
	public Exam getExam(String examGuid) {
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("guid", examGuid, RestrictionType.EQ));
		List<Object> exams = DBCommunicator.getApiServices().getGenericApi()
				.getFilteredList(Exam.class, filters, null);
		if(exams!=null && !exams.isEmpty())
			return (Exam)exams.get(0);
		return null;
	}


	public List<SubjectAndMarks> getSubjectAndMarks(String examGuid) {
		List<SubjectAndMarks> subjects = new ArrayList<SubjectAndMarks>();
		try {
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(new Filter("guid", examGuid, RestrictionType.EQ));
			List<Object> exams = DBCommunicator.getApiServices()
					.getGenericApi().getFilteredList(Exam.class, filters, null);
			for (Object object : exams) {
				Exam exam = (Exam) object;
				subjects = (List<SubjectAndMarks>) JsonConverter.fromJson(
						exam.getSubjectAndMarks(), List.class);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return subjects;
	}

	private List<Object> getExamResultObject(ExamFormObject examFormObject) {
		List<Object> result = new ArrayList<Object>();
		try {
			examFormObject.getClassGuid();
			examFormObject.getSubjectGuid();
			JSONArray array = new JSONArray(examFormObject.getMarks());
			for (int count = 0; count < array.length(); count++) {
				JSONObject object = array.getJSONObject(count);
				ExamResult examResult = new ExamResult();
				ExamResultKey examResultKey = new ExamResultKey();
				examResultKey.setExamGuid(examFormObject.getExamGuid());
				examResultKey.setStudentGuid(object.get("id").toString());
				examResultKey.setSubjectGuid(examFormObject.getSubjectGuid());
				examResultKey.setStandardAndDivision(examFormObject
						.getClassGuid());
				examResult.setMarks(object.get("marks").toString());
				examResult.setExamResultKey(examResultKey);
				result.add(examResult);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Map<String,String> getExamResult(List<String> studentGuids , String subjectGuid , String examGuid) {
		Map<String,String> result = new HashMap<String, String>();
		try {
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(new Filter("examResultKey.studentGuid", studentGuids, RestrictionType.IN));
			filters.add(new Filter("examResultKey.subjectGuid", subjectGuid, RestrictionType.EQ));
			filters.add(new Filter("examResultKey.examGuid", examGuid, RestrictionType.EQ));
			List<Object> studentsAndMarks = DBCommunicator.getApiServices().getGenericApi().getFilteredList(ExamResult.class, filters, null);
			for (Object object : studentsAndMarks) {
				ExamResult examResult = (ExamResult) object;
				result.put(examResult.getExamResultKey().getStudentGuid(), examResult.getMarks());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public List<Object> getExamResult(Student student , String examGuid) {
		List<Object> result = new ArrayList<Object>();
		try {
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(new Filter("examResultKey.studentGuid", student.getGuid(), RestrictionType.EQ));
			filters.add(new Filter("examResultKey.examGuid", examGuid, RestrictionType.EQ));
			filters.add(new Filter("examResultKey.standardAndDivision", student.getStandard() + "-" + student.getDivision(), RestrictionType.LIKE));
			result = DBCommunicator.getApiServices().getGenericApi().getFilteredList(ExamResult.class, filters, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Boolean saveExamMarks(ExamFormObject examFormObject){
		return DBCommunicator.getApiServices().getGenericApi().bulkUpdate(getExamResultObject(examFormObject));
	}
	
	public Boolean buildAndSaveExamResultObjects(String examGuid,String classAndDivision,String studentGuid,String marksJson){
		Boolean result = false;
		List<Object> results = new ArrayList<Object>();
		try { 
			String examMarks = "[" + marksJson + "]";
			JSONArray arr = new JSONArray(examMarks);
			List<Subject> subjects = ModelAttributesCommon.getSubjectsForModelAttributes();
			Map<String, String> subjectDetailsMap = new HashMap<String, String>();
			for (Subject subject : subjects){
				subjectDetailsMap.put(subject.getCode(), subject.getGuid());
			}
			
			for(int count = 0 ;count < arr.length() ; count++){
				ExamResult examResult = new ExamResult();
				ExamResultKey key = new ExamResultKey();
				key.setExamGuid(examGuid);
				key.setStandardAndDivision(classAndDivision);
				key.setStudentGuid(studentGuid);
				JSONObject obj = arr.getJSONObject(count);
				key.setSubjectGuid(subjectDetailsMap.get(obj.get("id").toString()));
				examResult.setMarks(obj.get("marks").toString());
				examResult.setExamResultKey(key);
				results.add(examResult);
			}
			if(results!=null && !results.isEmpty()){
				DBCommunicator.getApiServices().getGenericApi().bulkUpdate(results);
				result = true;
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public List<Object> getReportCard(String examGuid,String studentGuid){
		List<Object> result = new ArrayList<Object>();
		Exam exam = getExam(examGuid);
		Student student = studentControllerUtility.getStudent(studentGuid);
		if(exam!=null){
		try {
    		ReportCard reportCard = new ReportCard();
    		reportCard.setExamName(exam.getName());
    		reportCard.setRollNumber(student.getRollNumber());
    		reportCard.setStudentName(student.getName().toString());
    		Map<String,String> subjectCodeAndName = new HashMap<String, String>();
    		Map<String,Subject> subjectNameAndDetailsMap = new HashMap<String, Subject>();
    		List<String> subjectsAddedInResult = new ArrayList<String>();
    		List<Subject> subjects = ModelAttributesCommon.getSubjectsForModelAttributes();
    		List<Filter> filters = new ArrayList<Filter>();
    		filters.add(new Filter("studentId", student.getGuid() , RestrictionType.EQ));
    		filters.add(new Filter("standard", student.getStandard(), RestrictionType.EQ));
    		filters.add(new Filter("division", student.getDivision() , RestrictionType.EQ));
    		
    		StudentSubjectDetails studentSubjectDetails = (StudentSubjectDetails) DBCommunicator.getApiServices().getGenericApi().getFilteredList(StudentSubjectDetails.class, filters, null).get(0);
    		
    		for (Subject subject : subjects) {
    			subjectCodeAndName.put(subject.getGuid(), subject.getName());
    			subjectNameAndDetailsMap.put(subject.getGuid(), subject);
			}
    		List<Object> examResult = getExamResult(student, examGuid);
    		List<com.samarthsoft.prabandhak.reports.SubjectAndMarks> subjectAndMarksList = new ArrayList<com.samarthsoft.prabandhak.reports.SubjectAndMarks>();
    		Map<String, SubjectAndMarks> subjectAndMarksDetailsMap = new HashMap<String, SubjectAndMarks>();
    		JSONArray arr = new JSONArray(exam.getSubjectAndMarks());
    		for(int count = 0 ; count < arr.length() ; count++){
    			JSONObject obj = (JSONObject) arr.get(count);
    			SubjectAndMarks subjectAndMarksObject = new SubjectAndMarks();
    			//TODO:Make it proper marks and passing marks
    			//subjectAndMarksObject.setMarks(obj.get("marks").toString());
    			subjectAndMarksObject.setMarksRequiredForPassing(obj.get("marksRequiredForPassing").toString());
    			//subjectAndMarksObject.setMarksRequiredForPassing(obj.get("marksRequiredForPassing").toString());
    			subjectAndMarksObject.setMarks(obj.get("marks").toString());
    			subjectAndMarksObject.setSubject(obj.get("subject").toString());
    			subjectAndMarksDetailsMap.put(obj.get("subject").toString(), subjectAndMarksObject);
    		}
    		for (Object object : examResult) {
				ExamResult examResultObj = (ExamResult) object;
	    		com.samarthsoft.prabandhak.reports.SubjectAndMarks subjectAndMarks = new com.samarthsoft.prabandhak.reports.SubjectAndMarks();
	    		subjectAndMarks.setMarks(examResultObj.getMarks());
	    		subjectAndMarks.setOutOfMarks(subjectAndMarksDetailsMap.get(examResultObj.getExamResultKey().getSubjectGuid()).getMarks());
	    		if(!checkIsPassed(examResultObj.getMarks(), subjectAndMarksDetailsMap.get(examResultObj.getExamResultKey().getSubjectGuid()).getMarksRequiredForPassing())){
	    			subjectAndMarks.setPassed(false);
	    		}else{
	    			subjectAndMarks.setPassed(true);
	    		}
	    		subjectAndMarks.setSubjectName(subjectCodeAndName.get(examResultObj.getExamResultKey().getSubjectGuid()));
	    		subjectAndMarksList.add(subjectAndMarks);
	    		subjectsAddedInResult.add(examResultObj.getExamResultKey().getSubjectGuid());
    		}
    		for(String subjectCode : studentSubjectDetails.getSubjects().split(",")){
    			Subject subject = subjectNameAndDetailsMap.get(subjectCode);
    			if(!subjectsAddedInResult.contains(subject.getGuid())){
		    		com.samarthsoft.prabandhak.reports.SubjectAndMarks subjectAndMarks = new com.samarthsoft.prabandhak.reports.SubjectAndMarks();
		    		subjectAndMarks.setMarks("0");
		    		subjectAndMarks.setOutOfMarks("0");
		    		subjectAndMarks.setPassed(false);
		    		subjectAndMarks.setSubjectName(subject.getName());
		    		subjectAndMarksList.add(subjectAndMarks);
    			}
    		}
    		reportCard.setSubjectAndMarks(subjectAndMarksList);
    		reportCard.setPercentageMarks(calculatePercentageMarks(reportCard));
    		reportCard.setPassingGrade(calculatePassingGrade(reportCard));
    		result.add(reportCard);
    	}
		catch(Exception ex){
			ex.printStackTrace();
		}
		}
		return result;
	}
	
	private Float calculatePercentageMarks(ReportCard reportCard){
		Float result = 0f;
		Float totalMarks = 0f;
		Float totalMarksForPassing = 0f;
		List<com.samarthsoft.prabandhak.reports.SubjectAndMarks> subjectAndMarks = reportCard.getSubjectAndMarks();
		for (com.samarthsoft.prabandhak.reports.SubjectAndMarks subjectAndMarksReport : subjectAndMarks) {
			try{
				totalMarks += Float.parseFloat(subjectAndMarksReport.getMarks());
				totalMarksForPassing += Float.parseFloat(subjectAndMarksReport.getOutOfMarks());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		result = (float) ((totalMarks / totalMarksForPassing) * 100.0);
		return result;
	}

	private String calculatePassingGrade(ReportCard reportCard){
		String result = "";
		List<com.samarthsoft.prabandhak.reports.SubjectAndMarks> subjectAndMarks = reportCard.getSubjectAndMarks();
		for (com.samarthsoft.prabandhak.reports.SubjectAndMarks subjectAndMarksReport : subjectAndMarks) {
			try{
				if(!subjectAndMarksReport.isPassed())
					return com.samarthsoft.prabandhak.entities.Grades.FAIL.toString();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		if(reportCard.getPercentageMarks() >= 75){
			return com.samarthsoft.prabandhak.entities.Grades.DISTINCTION.toString();
		}
		if(reportCard.getPercentageMarks() >= 60 && reportCard.getPercentageMarks() < 75){
			return com.samarthsoft.prabandhak.entities.Grades.FIRST_CLASS.toString();
		}
		if(reportCard.getPercentageMarks() >= 55 && reportCard.getPercentageMarks() < 60){
			return com.samarthsoft.prabandhak.entities.Grades.HIGHER_SECOND_CLASS.toString();
		}
		if(reportCard.getPercentageMarks() >= 40 && reportCard.getPercentageMarks() < 55){
			return com.samarthsoft.prabandhak.entities.Grades.SECOND_CLASS.toString();
		}
		if(reportCard.getPercentageMarks() >= 40){
			return com.samarthsoft.prabandhak.entities.Grades.PASS_CLASS.toString();
		}
		return result;
	}

	public boolean checkIsMarkOrGrade(String marks){
		try{
			Integer.parseInt(marks);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	public boolean checkIsPassed(String marks,String marksForPassing){
		if(checkIsMarkOrGrade(marks)){
			int finalMarks = Integer.parseInt(marks);
			int finalMarksForPassing = Integer.parseInt(marksForPassing);
			return finalMarks >= finalMarksForPassing;
		}
		return marksForPassing.contains(marks);
	}

}