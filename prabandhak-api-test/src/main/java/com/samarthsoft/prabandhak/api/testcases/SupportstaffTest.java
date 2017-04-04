package com.samarthsoft.prabandhak.api.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samarthsoft.prabandhak.api.testcases.common.Constants;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.SupportStaff;

public class SupportstaffTest {
	private SupportStaff supportstaff;

	@Before
	public void init() {
		supportstaff = new SupportStaff();
		supportstaff.setCurrentAddress(Constants.CURRENT_ADDRESS);
		supportstaff.setDesignation(Constants.DESIGNATION);
		supportstaff.setJoiningDate(Constants.JOINING_DATE);
		supportstaff.setName(Constants.STAFF_NAME);
		supportstaff.setOrganizationGuid(Constants.ORGANIZATION_GUID);
		supportstaff.setParentOrganizationGuid(Constants.PARENT_ORGANIZATION_GUID);
		supportstaff.setPermanentAddress(Constants.PERMANENT_ADDRESS);
		supportstaff.setQualification(Constants.QUALIFICATION);
		
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.insert(supportstaff);
		assertEquals("SupportStaff creation was successful? ", true, result);
	}

	@After
	public void destroy() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(SupportStaff.class, "name",
						Constants.STAFF_NAME);
		assertNotNull(record);
		SupportStaff resultRecord = (SupportStaff) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(resultRecord);
		assertEquals("SupportStaff object deletion was successful? ", true, result);

	}

	@Test
	public void testSupportStaffCreate() {
			init();
	}

	@Test
	public void testDeleteSupportStaff() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(SupportStaff.class, "name",
						Constants.STAFF_NAME);
		assertNotNull(record);
		SupportStaff SupportStaff = (SupportStaff) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(SupportStaff);
		assertEquals("SupportStaff object deletion was successful? ", true, result);

	}

	@Test
	public void testGetSupportStaffByName() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(SupportStaff.class, "name",
						Constants.STAFF_NAME);
		assertNotNull(record);
		assertEquals(1, record.size());
	}

	@Test
	public void testUpdateSupportStaff() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(SupportStaff.class, "name",
						Constants.STAFF_NAME);
		assertNotNull(record);
		SupportStaff resultRecord = (SupportStaff) record.get(0);
		resultRecord.setDesignation("updatedDesignation");
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.update(resultRecord);
		assertEquals("SupportStaff object deletion was successful? ", true, result);

	}

}
