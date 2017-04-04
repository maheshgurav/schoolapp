package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Alumni;
import com.samarthsoft.prabandhak.entities.AlumniContactDetails;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.form.entities.AlumniFormObject;

@Component
public class AlumniControllerUtility {
	
	public AlumniFormObject getAlumniFormObject(Alumni alumni){
		AlumniFormObject alumniFormObject = new AlumniFormObject();
		try{
			alumniFormObject.setAlumni(alumni);
			alumniFormObject.setAdmissionDate(DateUtility.convertTimeToString(alumni.getDateOfAdmission()));
			alumniFormObject.setBirthDate(DateUtility.convertTimeToString(alumni.getDateOfBirth()));
			alumniFormObject.setEditId(alumni.getGuid());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return alumniFormObject;
	}
	
	public List<Filter> getFilteredList(String filter,String mapping,HttpServletRequest request){
		List<Filter> filters = new ArrayList<Filter>();
		try{
		String[] alumniFilters = filter.split(",");
		String[] alumniMappings = mapping.split(",");
		for(int count = 0; count<alumniFilters.length; count++){
			if (alumniMappings[count].contains("/")) {
				if(RequestCommon.getAttributeValueFromRequest(alumniFilters[count], request).toString()!=null && !RequestCommon.getAttributeValueFromRequest(alumniFilters[count], request).toString().isEmpty()){
					String[] mappings = alumniMappings[count].split("/");
					String standardAndDivision = RequestCommon.getAttributeValueFromRequest(alumniFilters[count], request).toString();
					if(standardAndDivision!=null && standardAndDivision.split("-")!=null){
						if(standardAndDivision.split("-")[0]!=null)
							filters.add(new Filter(mappings[0].toString(), standardAndDivision.split("-")[0].toString() , RestrictionType.EQ));
					}
					if(standardAndDivision!=null && standardAndDivision.split("-")!=null && standardAndDivision.split("-").length > 1){
						if(standardAndDivision.split("-")[1]!=null)
							filters.add(new Filter(mappings[1].toString(), standardAndDivision.split("-")[1].toString() , RestrictionType.EQ));
					}
				}
			}else{
				if(RequestCommon.getAttributeValueFromRequest(alumniFilters[count], request).toString()!=null && !RequestCommon.getAttributeValueFromRequest(alumniFilters[count], request).toString().isEmpty()){
					filters.add(new Filter(alumniMappings[count], RequestCommon.getAttributeValueFromRequest(alumniFilters[count], request).toString(), RestrictionType.LIKE));		
				}
			}
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return filters;
	}
	
	public Alumni getAlumniObject(AlumniFormObject alumniFormObject){
		Alumni alumni = alumniFormObject.getAlumni();
		alumni.setDateOfAdmission(DateUtility.convertStringToTime(alumniFormObject.getAdmissionDate()));
		alumni.setDateOfBirth(DateUtility.convertStringToTime(alumniFormObject.getBirthDate()));
		return alumni;
	}
	
	public Alumni getAlumni(String studentGuid){
    	Filter filter = new Filter("guid",studentGuid,RestrictionType.EQ);
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(filter);
    	return (Alumni)DBCommunicator.getApiServices().getGenericApi().getFilteredList(Alumni.class, filters, null).get(0);
	}

	public AlumniContactDetails getAlumniContactDetails(String alumniGuid){
    	Filter filter = new Filter("studentId", alumniGuid, RestrictionType.EQ);
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(filter);
    	List<Object> alumniContactDetails = DBCommunicator.getApiServices().getGenericApi().getObjectByColumnName(AlumniContactDetails.class, "studentId", alumniGuid);
    	if(alumniContactDetails!=null && alumniContactDetails.size() > 0){
    		return (AlumniContactDetails)alumniContactDetails.get(0);
    	}
    	return new AlumniContactDetails();
	}
}