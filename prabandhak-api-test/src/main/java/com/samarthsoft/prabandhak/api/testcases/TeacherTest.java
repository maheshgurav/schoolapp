package com.samarthsoft.prabandhak.api.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samarthsoft.prabandhak.api.testcases.common.Constants;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Teacher;

public class TeacherTest {
	private Teacher teacher;

	@Before
	public void init() {
		teacher = new Teacher();
		teacher.setCurrentAddress(Constants.CURRENT_ADDRESS);
		teacher.setDesignation(Constants.DESIGNATION);
		teacher.setJoiningDate(Constants.JOINING_DATE);
		teacher.setName(Constants.STAFF_NAME);
		teacher.setOrganizationGuid(Constants.ORGANIZATION_GUID);
		teacher.setParentOrganizationGuid(Constants.PARENT_ORGANIZATION_GUID);
		teacher.setPermanentAddress(Constants.PERMANENT_ADDRESS);
		teacher.setQualification(Constants.QUALIFICATION);
		
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.insert(teacher);
		assertEquals("Teacher creation was successful? ", true, result);
	}

	@After
	public void destroy() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Teacher.class, "name",
						Constants.STAFF_NAME);
		assertNotNull(record);
		Teacher resultRecord = (Teacher) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(resultRecord);
		assertEquals("Teacher object deletion was successful? ", true, result);

	}

	@Test
	public void testTeacherCreate() {
			init();
	}

	@Test
	public void testDeleteTeacher() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Teacher.class, "name",
						Constants.STAFF_NAME);
		assertNotNull(record);
		Teacher teacher = (Teacher) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(teacher);
		assertEquals("Teacher object deletion was successful? ", true, result);

	}

	@Test
	public void testGetTeacherByName() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Teacher.class, "name",
						Constants.STAFF_NAME);
		assertNotNull(record);
		assertEquals(1, record.size());
	}

	@Test
	public void testUpdateTeacher() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(Teacher.class, "name",
						Constants.STAFF_NAME);
		assertNotNull(record);
		Teacher teacher = (Teacher) record.get(0);
		teacher.setDesignation("updatedDesignation");
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.update(teacher);
		assertEquals("Teachern object deletion was successful? ", true, result);

	}

}
