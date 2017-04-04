package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.entities.Teacher;

public class TeacherForm extends EmployeeBaseForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private Teacher teacher;

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}