package com.samarthsoft.prabandhak.utilities;

import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.entities.IncomeDetails;
import com.samarthsoft.prabandhak.form.entities.IncomeFormObject;

@Component
public class IncomeControllerUtility {
	
	public IncomeFormObject getIncomeFormObject(IncomeDetails incomeDetails){
		IncomeFormObject incomeFormObject = new IncomeFormObject();
		incomeFormObject.setIncomeDetails(incomeDetails);
		incomeFormObject.setDateOfIncome(DateUtility.convertTimeToString(incomeDetails.getDate()));
		return incomeFormObject;
	}
	
	public IncomeDetails getIncomeDetailsObject(IncomeFormObject incomeFormObject){
		IncomeDetails incomeDetails = incomeFormObject.getIncomeDetails();
		incomeDetails.setDate(DateUtility.convertStringToTime(incomeFormObject.getDateOfIncome()));
		return incomeDetails;
	}
}