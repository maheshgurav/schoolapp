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
import com.samarthsoft.prabandhak.entities.Alumni;

public class AlumniTest {
	private Alumni alumni;

	@Before
	public void init() {
		alumni = new Alumni();
		alumni = (Alumni) StudentBasic.setBasicInfo(alumni);
		alumni.setCurrentClass(Constants.CURRENT_CLASS);
		alumni.setAddmissionTakenInclass(Constants.ADMISSION_CLASS);
		alumni.setLastAttendedSchoolName(Constants.LAST_SCHOOL_ATTENDED);
		alumni.setPRN(Constants.PRN);
		alumni.setNationality(Constants.NATIONALITY);
		alumni.setAadharCardNumber(Constants.AADHAR_CARD_NUMBER);
		alumni.setBloodGroup(Constants.BLOOD_GROUP);
		alumni.setRollNumber(Constants.ROLL_NUMBER);
		alumni.setDateOfAdmission(new Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.insert(alumni);
		assertEquals("Alumni creation was successful? ", true, result);
	}

	@After
	public void destroy() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Alumni.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		Alumni alumni = (Alumni) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(alumni);
		assertEquals("Alumni deletion was successful? ", true, result);

	}

	@Test
	public void testAlumniCreate() {
			init();
	}

	@Test
	public void testDeleteAlumni() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Alumni.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		Alumni alumni = (Alumni) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(alumni);
		assertEquals("Alumni deletion was successful? ", true, result);

	}

	@Test
	public void testGetAlumniByName() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Alumni.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		assertEquals(1, record.size());
	}

	@Test
	public void testUpdateAlumni() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Alumni.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		Alumni alumni = (Alumni) record.get(0);
		alumni.setFatherName("updateFatherName");
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.update(alumni);
		assertEquals("Student deletion was successful? ", true, result);
	}
}
