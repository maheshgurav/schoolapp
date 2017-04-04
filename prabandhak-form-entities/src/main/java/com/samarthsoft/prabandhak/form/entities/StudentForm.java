package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.entities.Student;

public class StudentForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String admissionDate;
	private String birthDate;
	private Student student;
	private String editId;

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}
}