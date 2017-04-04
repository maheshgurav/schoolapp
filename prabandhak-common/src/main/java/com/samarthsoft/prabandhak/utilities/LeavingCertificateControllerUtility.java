package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.LeavingCertificate;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.form.entities.LeavingCertificateForm;
import com.samarthsoft.prabandhak.reports.LeavingCertificateReportObject;

@Component
public class LeavingCertificateControllerUtility {

	@Autowired
	private CommonControllerUtility commonControllerUtility;
	
	public LeavingCertificate getLeavingCertificateObject(LeavingCertificateForm leavingCertificateFormObject) {
		LeavingCertificate leavingCertificate = leavingCertificateFormObject.getLeavingCertificate();
		leavingCertificate.setDateOfLeaving(DateUtility.convertStringToTime(leavingCertificateFormObject.getLeavingDate()));
		leavingCertificate.setStudentGuid(leavingCertificateFormObject.getStudentGuid());
		return leavingCertificate;
	}
	
	public LeavingCertificateForm getLeavingCertificateFormObject(LeavingCertificate leavingCertificate,Student student) {
		LeavingCertificateForm leavingCertificateFormObject = new LeavingCertificateForm();
		leavingCertificateFormObject.setLeavingCertificate(leavingCertificate);
		leavingCertificateFormObject.setStudentName(student.getName().toString());
		leavingCertificateFormObject.setStudentGuid(student.getGuid());
		leavingCertificateFormObject.setLeavingDate(DateUtility.convertTimeToString(Calendar.getInstance().getTimeInMillis()));
		return leavingCertificateFormObject;
	}
	
	public Boolean saveLeavingCertificateInformation(LeavingCertificateForm leavingCertificateFormObject,Student student){
		List<Object> records = new ArrayList<Object>();
		records.add(getLeavingCertificateObject(leavingCertificateFormObject));
		if(leavingCertificateFormObject.getLeavingCertificate().getStudentGuid()!=null && !leavingCertificateFormObject.getLeavingCertificate().getStudentGuid().isEmpty())
			return DBCommunicator.getApiServices().getGenericApi().bulkUpdate(records);
		return DBCommunicator.getApiServices().getGenericApi().bulkInsert(records);
	}
	
	public LeavingCertificateReportObject getLeavingCertificateObjectForReport(){
		LeavingCertificateReportObject reportObject = new LeavingCertificateReportObject();
		return reportObject;
	}
}