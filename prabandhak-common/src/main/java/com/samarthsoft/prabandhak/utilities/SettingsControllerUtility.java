package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.Exam;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.Subject;
import com.samarthsoft.prabandhak.entities.SubjectAndMarks;
import com.samarthsoft.prabandhak.entities.SubjectAndTeacherDetails;
import com.samarthsoft.prabandhak.entities.Teacher;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.form.entities.ClassInfoFormObject;
import com.samarthsoft.prabandhak.form.entities.ExamSettingsForm;

@Component
public class SettingsControllerUtility {

	public ExamSettingsForm getExamSettingsFormObject(Exam exam) {
		ExamSettingsForm examSettingsForm = new ExamSettingsForm();
		examSettingsForm.setExam(exam);
		if (exam.getStandards() != null && !exam.getStandards().isEmpty())
			examSettingsForm.setExamForStandards(Arrays.asList(exam.getStandards().split(",")));
		if (exam.getSubjectAndMarks() != null && !exam.getSubjectAndMarks().isEmpty())
			examSettingsForm.setSubjectAndMarks(convertSubjectAndMarksStringToList(exam.getSubjectAndMarks()));
		if (!ValidatorCommon.checkFieldNullOrEmpty(exam.getExamEndDate()))
			examSettingsForm.setEndDateOfExam(DateUtility.convertTimeToString(exam.getExamEndDate()));
		if (!ValidatorCommon.checkFieldNullOrEmpty(exam.getExamStartDate()))
			examSettingsForm.setStartDateOfExam(DateUtility.convertTimeToString(exam.getExamStartDate()));
		return examSettingsForm;
	}

	/*
	 * public List<SubjectAndMarks> convertSubjectAndMarksStringToList(String
	 * subjectAndMark){ List<SubjectAndMarks> result = new
	 * ArrayList<SubjectAndMarks>(); try{ if(subjectAndMark!=null &&
	 * !subjectAndMark.isEmpty() && !subjectAndMark.equals("null")){ String[]
	 * subjectAndMarks = subjectAndMark.split(":"); for (String
	 * theSubjectAndMark : subjectAndMarks) { String[] theSubjectAndMarks =
	 * theSubjectAndMark.split("#"); if(theSubjectAndMarks.length == 3){
	 * SubjectAndMarks objSubjectAndMarks = new SubjectAndMarks();
	 * objSubjectAndMarks.setSubject(theSubjectAndMarks[0]);
	 * objSubjectAndMarks.setMarks(theSubjectAndMarks[1]);
	 * objSubjectAndMarks.setMarksRequiredForPassing(theSubjectAndMarks[2]);
	 * result.add(objSubjectAndMarks); } } } }catch(Exception ex){
	 * ex.printStackTrace(); } return result; }
	 */

	public List<SubjectAndMarks> convertSubjectAndMarksStringToList(String subjectAndMark) {
		List<SubjectAndMarks> result = new ArrayList<SubjectAndMarks>();
		try {
			if (subjectAndMark != null && !subjectAndMark.isEmpty() && !subjectAndMark.equals("null")) {
				JSONArray arr = new JSONArray(subjectAndMark);
				for (int count = 0; count < arr.length(); count++) {
					JSONObject obj = arr.getJSONObject(count);
					SubjectAndMarks subjectAndMarks = new SubjectAndMarks();
					subjectAndMarks.setMarks(obj.get("marks").toString());
					subjectAndMarks.setMarksRequiredForPassing(obj.get("marksRequiredForPassing").toString());
					subjectAndMarks.setSubject(obj.get("subject").toString());
					result.add(subjectAndMarks);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public Exam getExamObject(ExamSettingsForm examSettingsForm) {
		Exam exam = examSettingsForm.getExam();
		exam.setSubjectAndMarks(examSettingsForm.getEnteredSubjectAndMarks());
		exam.setExamStartDate(DateUtility.convertStringToTime(examSettingsForm.getStartDateOfExam()));
		exam.setExamEndDate(DateUtility.convertStringToTime(examSettingsForm.getEndDateOfExam()));
		return exam;
	}

	public ClassInfoFormObject getClassInfoFormObject(ClassInfo classInfo) {
		ClassInfoFormObject classInfoFormObject = new ClassInfoFormObject();
		classInfoFormObject.setClassInfo(classInfo);
		classInfoFormObject.setSubjectTeacherMapping(buildSubjectTeacherGuidList(classInfo.getSubjectsAndTeachers()));
		return classInfoFormObject;
	}

	public List<SubjectAndTeacherDetails> getSubjectAndTeacherDetailsList(List<SubjectTeacherId> subjectTeacherIds){
		List<SubjectAndTeacherDetails> result = new ArrayList<SubjectAndTeacherDetails>();
		try{
			List<String> subjectIds = new ArrayList<String>();
			List<String> teacherIds = new ArrayList<String>();
			for (SubjectTeacherId subjectTeacherId : subjectTeacherIds) {
				subjectIds.add(subjectTeacherId.getSubjectGuid());
				teacherIds.add(subjectTeacherId.getTeacherGuid());
			}
			List<Filter> teacherFilter = new ArrayList<Filter>();
			teacherFilter.add(new Filter("guid", teacherIds, RestrictionType.IN));
			List<Object> teachers = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Teacher.class, teacherFilter, null);
			Map<String, Teacher> teachersMap = new HashMap<String, Teacher>();
			for (Object object : teachers) {
				Teacher teacher = (Teacher) object;
				teachersMap.put(teacher.getGuid(), teacher);
			}
			
			List<Filter> subjectFilter = new ArrayList<Filter>();
			subjectFilter.add(new Filter("guid", subjectIds, RestrictionType.IN));
			List<Object> subjects = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Subject.class, subjectFilter, null);
			Map<String, Subject> subjectMap = new HashMap<String, Subject>();
			for (Object object : subjects) {
				Subject subject = (Subject) object;
				subjectMap.put(subject.getGuid(), subject);
			}
			
			for (SubjectTeacherId subjectTeacherId : subjectTeacherIds) {
				SubjectAndTeacherDetails subjectAndTeacherDetails = new SubjectAndTeacherDetails();
				subjectAndTeacherDetails.setSubject(subjectMap.get(subjectTeacherId.getSubjectGuid()));
				subjectAndTeacherDetails.setTeacher(teachersMap.get(subjectTeacherId.getTeacherGuid()));
				result.add(subjectAndTeacherDetails);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	private List<SubjectAndTeacherDetails> buildSubjectTeacherGuidList(String subjectTeacherJson) {
		List<SubjectAndTeacherDetails> result = new ArrayList<SubjectAndTeacherDetails>();
		List<SubjectTeacherId> subjectTeacherIds = new ArrayList<SettingsControllerUtility.SubjectTeacherId>();
		try {
			if (subjectTeacherJson != null && !subjectTeacherJson.isEmpty() && !subjectTeacherJson.equals("null")) {
				JSONArray arr = new JSONArray(subjectTeacherJson);
				for (int count = 0; count < arr.length(); count++) {
					JSONObject obj = arr.getJSONObject(count);
					SubjectTeacherId subjectAndTeacherDetails = new SubjectTeacherId();
					subjectAndTeacherDetails.setSubjectGuid(obj.get("subject_id").toString());
					subjectAndTeacherDetails.setTeacherGuid(obj.get("teacher_id").toString());
					subjectTeacherIds.add(subjectAndTeacherDetails);
				}
				result.addAll(getSubjectAndTeacherDetailsList(subjectTeacherIds));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	class SubjectTeacherId {
		private String subjectGuid;
		private String teacherGuid;

		public String getSubjectGuid() {
			return subjectGuid;
		}

		public void setSubjectGuid(String subjectGuid) {
			this.subjectGuid = subjectGuid;
		}

		public String getTeacherGuid() {
			return teacherGuid;
		}

		public void setTeacherGuid(String teacherGuid) {
			this.teacherGuid = teacherGuid;
		}
	}
}