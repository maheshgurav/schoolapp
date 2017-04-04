package com.samarthsoft.prabandhak.api.testcases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samarthsoft.prabandhak.api.testcases.common.Constants;
import com.samarthsoft.prabandhak.api.testcases.common.StudentBasic;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Admission;

public class AdmissionTest {
	private Admission admission;

	@Before
	public void init() {
		admission = new Admission();
		admission = (Admission) StudentBasic.setBasicInfo(admission);
		admission.setClassForAdmission(Constants.CLASS_FOR_ADDMISSION);
		admission.setMarksOrGradeOfLastClass(Constants.LAST_MARK_OR_GRADE);
		admission.setLastAttendedUniversity(Constants.LAST_UNIVERSITY_ATTENDED);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.insert(admission);
		assertEquals("Admission creation was successful? ", true, result);
	}

	@After
	public void destroy() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Admission.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		Admission admission = (Admission) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(admission);
		assertEquals("Admission object deletion was successful? ", true, result);

	}

	@Test
	public void testAdmissionCreate() {
			init();
	}

	@Test
	public void testDeleteAdmission() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Admission.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		Admission admission = (Admission) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(admission);
		assertEquals("Admission object deletion was successful? ", true, result);

	}

	@Test
	public void testGetAdmissionByName() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Admission.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		assertEquals(1, record.size());
	}

	@Test
	public void testUpdateAdmission() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Admission.class, "studentName",
						Constants.STUDENT_NAME);
		assertNotNull(record);
		Admission admission = (Admission) record.get(0);
		admission.setFatherName("updateFatherName");
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.update(admission);
		assertEquals("admissionn object deletion was successful? ", true, result);

	}
}