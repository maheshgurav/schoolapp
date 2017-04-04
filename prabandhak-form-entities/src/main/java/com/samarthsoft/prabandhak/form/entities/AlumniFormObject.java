package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.entities.Alumni;
import com.samarthsoft.prabandhak.entities.Name;
import com.samarthsoft.prabandhak.entities.Student;

public class AlumniFormObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private Alumni alumni;
	private String admissionDate;
	private String birthDate;
	private String editId;

	public Alumni getAlumni() {
		return alumni;
	}

	public void setAlumni(Alumni alumni) {
		this.alumni = alumni;
	}

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

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}
}