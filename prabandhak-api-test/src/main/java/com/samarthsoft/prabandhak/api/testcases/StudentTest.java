package com.samarthsoft.prabandhak.api.testcases;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samarthsoft.prabandhak.api.testcases.common.Constants;
import com.samarthsoft.prabandhak.api.testcases.common.StudentBasic;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Student;

public class StudentTest {
	private Student student;

	@Before
	public void init() {
		student = new Student();
		student = (Student) StudentBasic.setBasicInfo(student);
		student.setCurrentClass(Constants.CURRENT_CLASS);
		student.setAddmissionTakenInclass(Constants.ADMISSION_CLASS);
		student.setLastAttendedSchoolName(Constants.LAST_SCHOOL_ATTENDED);
		student.setPRN(Constants.PRN);
		student.setNationality(Constants.NATIONALITY);
		student.setAadharCardNumber(Constants.AADHAR_CARD_NUMBER);
		student.setBloodGroup(Constants.BLOOD_GROUP);
		student.setRollNumber(Constants.ROLL_NUMBER);
		student.setDateOfAdmission(new Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.insert(student);
		assertEquals("Student creation was successful? ", true, result);
	}

	@After
	public void destroy() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Student.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		Student student = (Student) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(student);
		assertEquals("Student deletion was successful? ", true, result);

	}

	@Test
	public void testStudentCreate() {
			init();
	}

	@Test
	public void testDeleteStudent() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Student.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		Student student = (Student) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(student);
		assertEquals("Student deletion was successful? ", true, result);

	}

	@Test
	public void testGetStudentByName() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Student.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		assertEquals(1, record.size());
	}

	@Test
	public void testUpdateStudent() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Student.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		Student student = (Student) record.get(0);
		student.setFatherName("updateFatherName");
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.update(student);
		assertEquals("Student deletion was successful? ", true, result);
	}
}
