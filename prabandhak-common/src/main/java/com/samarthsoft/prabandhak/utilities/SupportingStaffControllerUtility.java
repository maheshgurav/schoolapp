package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.CachedDataStore;
import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.JsonConverter;
import com.samarthsoft.prabandhak.common.LoginInformationCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.BankAccountDetails;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.entities.SupportStaff;
import com.samarthsoft.prabandhak.entities.SupportingStaffDesignations;
import com.samarthsoft.prabandhak.entities.interfaces.ObjectConverter;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.enums.UserRole;
import com.samarthsoft.prabandhak.form.entities.SupportingStaffForm;

@Component
public class SupportingStaffControllerUtility implements ObjectConverter{

	public SupportingStaffForm convertOriginalObjectToFromObject(Object supportingStaffObject) {
		SupportingStaffForm supportingStaffFormObject = new SupportingStaffForm();
		supportingStaffFormObject.setSupportStaff((SupportStaff)supportingStaffObject);
		supportingStaffFormObject.setCurrentAddressDetails(supportingStaffFormObject.getSupportStaff().getCurrentAddress());
		supportingStaffFormObject.setJoiningDate(DateUtility.convertTimeToString(supportingStaffFormObject.getSupportStaff().getJoiningDate()));
		supportingStaffFormObject.setName(supportingStaffFormObject.getSupportStaff().getName());
		supportingStaffFormObject.setPermanentAddressDetails(supportingStaffFormObject.getSupportStaff().getPermanentAddress());
		supportingStaffFormObject.setBankAccountDetails((BankAccountDetails)JsonConverter.fromJson(supportingStaffFormObject.getSupportStaff().getBankAccountDetails(), BankAccountDetails.class));
		supportingStaffFormObject.setBirthDate(DateUtility.convertTimeToString(supportingStaffFormObject.getSupportStaff().getDateOfBirth()));
		supportingStaffFormObject.setEditId(((SupportStaff)supportingStaffObject).getGuid());
		return supportingStaffFormObject;
	}

	public SupportStaff convertFormObjectToOriginalObject(Object supportStaffFormObject) {
		SupportStaff supportStaff = ((SupportingStaffForm)supportStaffFormObject).getSupportStaff();
		supportStaff.setCurrentAddress(((SupportingStaffForm)supportStaffFormObject).getCurrentAddressDetails());
		if(((SupportingStaffForm)supportStaffFormObject).getJoiningDate() != null)
			supportStaff.setJoiningDate(DateUtility.convertStringToTime(((SupportingStaffForm)supportStaffFormObject).getJoiningDate()));
		supportStaff.setName(((SupportingStaffForm)supportStaffFormObject).getName());
		supportStaff.setPermanentAddress(((SupportingStaffForm)supportStaffFormObject).getPermanentAddressDetails());
		supportStaff.setBankAccountDetails(JsonConverter.toJson(((SupportingStaffForm)supportStaffFormObject).getBankAccountDetails()));
		if(((SupportingStaffForm)supportStaffFormObject).getBirthDate() != null)
			supportStaff.setDateOfBirth(DateUtility.convertStringToTime(((SupportingStaffForm)supportStaffFormObject).getBirthDate()));
		return supportStaff;
	}
	
	public SupportStaff getSupportingStaffForEditOrView(String supportingStaffGuid){
    	Filter filter = new Filter("guid",supportingStaffGuid,RestrictionType.EQ);
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(filter);
    	SupportStaff supportStaff = (SupportStaff)DBCommunicator.getApiServices().getGenericApi().getFilteredList(SupportStaff.class, filters, null).get(0);
    	if(supportStaff!=null){
    		LoginInformation loginInformation = LoginInformationCommon.injectLoginInformation(supportingStaffGuid);
    		loginInformation.setUserRole(UserRole.SUPPORTING_STAFF);
    		supportStaff.setLoginInformation(loginInformation);
    	}
    	return supportStaff; 
	}
	
	public Boolean checkHasAssociatedLoginInformation(String designation){
		for(Object object : CachedDataStore.getSupportingStaffDesignations()){
			SupportingStaffDesignations supportingStaffDesignations = (SupportingStaffDesignations) object;
			if(supportingStaffDesignations.getName().equalsIgnoreCase(designation))
				return supportingStaffDesignations.getHasAssociatedLoginInformation();
		}
		return false;
	}
}