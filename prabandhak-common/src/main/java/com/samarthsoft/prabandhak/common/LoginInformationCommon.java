package com.samarthsoft.prabandhak.common;

import java.util.ArrayList;
import java.util.List;

import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.enums.RestrictionType;

public class LoginInformationCommon {
	private LoginInformationCommon() {
		// private constructor
	}

	public static LoginInformation injectLoginInformation(String loginGuid) {
		LoginInformation result = null;
		Filter filter = new Filter("guid", loginGuid, RestrictionType.EQ);
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(filter);
		List<Object> loginInformation = (List<Object>) DBCommunicator.getApiServices().getGenericApi().getFilteredList(LoginInformation.class, filters, null);
		if (!loginInformation.isEmpty())
			 result = (LoginInformation)loginInformation.get(0);
		else
			result =  new LoginInformation();
		return result;
	}
}