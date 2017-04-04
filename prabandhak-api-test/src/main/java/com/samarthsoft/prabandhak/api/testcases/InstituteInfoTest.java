package com.samarthsoft.prabandhak.api.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samarthsoft.prabandhak.api.testcases.common.Constants;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.InstituteInformation;

public class InstituteInfoTest {
	private InstituteInformation instituteInfo;

	@Before
	public void init() {
		instituteInfo = new InstituteInformation();
		instituteInfo.setAddress(Constants.CURRENT_ADDRESS);
		instituteInfo.setDateOfEstablishment(Constants.JOINING_DATE);
		instituteInfo.setName(Constants.INSTITUTE_NAME);
		instituteInfo.setOrganizationGuid(Constants.ORGANIZATION_GUID);
		instituteInfo
				.setParentOrganizationGuid(Constants.PARENT_ORGANIZATION_GUID);
		instituteInfo
				.setPermanentRegistrationNumber(Constants.PERMANENT_REGISTRATION_NUMBER);
		instituteInfo.setPrincipalName(Constants.PRINCIPAL_NAME);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.insert(instituteInfo);
		assertEquals("InstituteInformation creation was successful? ", true, result);
	}

	@After
	public void destroy() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(InstituteInformation.class, "name",
						Constants.INSTITUTE_NAME);
		assertNotNull(record);
		InstituteInformation resultRecord = (InstituteInformation) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(resultRecord);
		assertEquals("InstituteInformation object deletion was successful? ", true, result);

	}

	@Test
	public void testInstituteInformationCreate() {
		init();
	}

	@Test
	public void testDeleteInstituteInformation() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(InstituteInformation.class, "name",
						Constants.INSTITUTE_NAME);
		assertNotNull(record);
		InstituteInformation resultrecord = (InstituteInformation) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(resultrecord);
		assertEquals("InstituteInformation object deletion was successful? ", true, result);

	}

	@Test
	public void testGetInstituteInformationByName() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(InstituteInformation.class, "name",
						Constants.INSTITUTE_NAME);
		assertNotNull(record);
		assertEquals(1, record.size());
	}

	@Test
	public void testUpdateInstituteInformation() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(InstituteInformation.class, "name",
						Constants.INSTITUTE_NAME);
		assertNotNull(record);
		InstituteInformation resultRecord = (InstituteInformation) record.get(0);
		resultRecord.setPrincipalName("updatedpricipalName");
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.update(resultRecord);
		assertEquals("InstituteInformationn object deletion was successful? ", true, result);

	}

}
