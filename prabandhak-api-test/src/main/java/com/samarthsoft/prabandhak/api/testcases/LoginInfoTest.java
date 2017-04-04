package com.samarthsoft.prabandhak.api.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.samarthsoft.prabandhak.api.testcases.common.Constants;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.enums.UserRole;

public class LoginInfoTest {

	private LoginInformation loginInfo;

	@Before
	public void init() {
		loginInfo = new LoginInformation();
		loginInfo.setUserRole(UserRole.USER);
		loginInfo.setPassword("password");
		loginInfo.setUserName(Constants.USER_NAME);
		loginInfo.setOrganizationGuid(Constants.ORGANIZATION_GUID);
		loginInfo.setParentOrganizationGuid(Constants.PARENT_ORGANIZATION_GUID);

		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.insert(loginInfo);
		assertEquals("LoginInformation creation was successful? ", true, result);
	}

	@After
	public void destroy() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(LoginInformation.class, "userName",
						Constants.USER_NAME);
		assertNotNull(record);
		LoginInformation resultRecord = (LoginInformation) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(resultRecord);
		assertEquals("LoginInformation object deletion was successful? ", true,
				result);

	}

	@Test
	public void testLoginInformationCreate() {
		init();
	}

	@Test
	public void testDeleteLoginInformation() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(LoginInformation.class, "userName",
						Constants.USER_NAME);
		assertNotNull(record);
		LoginInformation resultRecord = (LoginInformation) record.get(0);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.delete(resultRecord);
		assertEquals("LoginInformation object deletion was successful? ", true,
				result);

	}

	@Test
	public void testGetLoginInformationByName() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(LoginInformation.class, "userName",
						Constants.USER_NAME);
		assertNotNull(record);
		assertEquals(1, record.size());
	}

	@Test
	public void testUpdateLoginInformation() {
		List<Object> record = DBCommunicator
				.getApiServices()
				.getGenericApi()
				.getObjectByColumnName(LoginInformation.class, "userName",
						Constants.USER_NAME);
		assertNotNull(record);
		LoginInformation LoginInformation = (LoginInformation) record.get(0);
		LoginInformation.setUserRole(UserRole.ADMIN);
		Boolean result = DBCommunicator.getApiServices().getGenericApi()
				.update(LoginInformation);
		assertEquals("LoginInformationn object updation was successful? ",
				true, result);
	}
}
