package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;
import java.util.List;

import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.SubjectAndTeacherDetails;

public class ClassInfoFormObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClassInfo classInfo;
	private List<SubjectAndTeacherDetails> subjectTeacherMapping;

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public List<SubjectAndTeacherDetails> getSubjectTeacherMapping() {
		return subjectTeacherMapping;
	}

	public void setSubjectTeacherMapping(List<SubjectAndTeacherDetails> subjectTeacherMapping) {
		this.subjectTeacherMapping = subjectTeacherMapping;
	}
}