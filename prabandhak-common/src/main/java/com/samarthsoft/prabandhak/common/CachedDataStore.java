package com.samarthsoft.prabandhak.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Caste;
import com.samarthsoft.prabandhak.entities.Category;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.Nationality;
import com.samarthsoft.prabandhak.entities.ScholarshipType;
import com.samarthsoft.prabandhak.entities.SchoolType;
import com.samarthsoft.prabandhak.entities.State;
import com.samarthsoft.prabandhak.entities.Subject;
import com.samarthsoft.prabandhak.entities.SupportingStaffDesignations;
import com.samarthsoft.prabandhak.entities.TeacherDesignations;

public class CachedDataStore {
	private static Map<String,Caste> casts = new HashMap<String, Caste>();
	private static Map<String,ScholarshipType> scholarShipTypes = new HashMap<String, ScholarshipType>();
	private static Map<String,ClassInfo> classes = new HashMap<String, ClassInfo>();
	private static Map<String,Category> categories = new HashMap<String, Category>();
	private static Map<String,State> states = new HashMap<String, State>();
	private static List<Object> teacherDesignations = new ArrayList<Object>();
	private static List<Object> supportingStaffDesignations = new ArrayList<Object>();
	private static List<Object> nationalities =  new ArrayList<Object>();
	private static List<Object> subjects =  new ArrayList<Object>();
	private static List<Object> schoolTypes =  new ArrayList<Object>();
	
	private CachedDataStore(){
		//Utility class
	}
	
	public static Map<String,Caste> getCastes(){
		//if(casts.isEmpty()){
			List<Object> castList = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Caste.class, null,null);
			if(castList!=null && !castList.isEmpty()){
				for (Object object : castList) {
					casts.put(((Caste)object).getGuid(),((Caste)object));
				}
			}
		//}
		return casts;
	}
	
	public static Map<String,ScholarshipType> getScholashipTypes(){
		//if(scholarShipTypes.isEmpty()){
			List<Object> scholarShipTypesList = DBCommunicator.getApiServices().getGenericApi().getFilteredList(ScholarshipType.class, null,null);
			if(scholarShipTypesList!=null && !scholarShipTypesList.isEmpty()){
				for (Object object : scholarShipTypesList) {
					scholarShipTypes.put(((ScholarshipType)object).getGuid(),((ScholarshipType)object));
				}
			}
		//}
		return scholarShipTypes;
	}
	
	public static Map<String,ClassInfo> getClassInfo(){
		//if(classes.isEmpty()){
			List<Object> classList = DBCommunicator.getApiServices().getGenericApi().getFilteredList(ClassInfo.class, null,null);
			if(classList!=null && !classList.isEmpty()){
				for (Object object : classList) {
					classes.put(((ClassInfo)object).getGuid(),((ClassInfo)object));
				}
			}
		//}
		return classes;
	}
	
	public static Map<String,Category> getCategories(){
		//if(categories.isEmpty()){
			List<Object> categoriesList = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Category.class, null,null);
			if(categoriesList!=null && !categoriesList.isEmpty()){
				for (Object object : categoriesList) {
					categories.put(((Category)object).getGuid(),((Category)object));
				}
			}
		//}
		return categories;
	}
	
	public static Map<String,State> getStates(){
		//if(states.isEmpty()){
			List<Object> stateList = DBCommunicator.getApiServices().getGenericApi().getFilteredList(State.class, null,null);
			if(stateList!=null && !stateList.isEmpty()){
				for (Object object : stateList) {
					states.put(((State)object).getGuid(),((State)object));
				}
			}
		//}
		return states;
	}

	public static List<Object> getTeacherDesignations() {
		//if(teacherDesignations.isEmpty())
			teacherDesignations = DBCommunicator.getApiServices().getGenericApi().getFilteredList(TeacherDesignations.class, null,null);
		return teacherDesignations;
	}

	public static List<Object> getSupportingStaffDesignations() {
		if(supportingStaffDesignations.isEmpty())
			supportingStaffDesignations = DBCommunicator.getApiServices().getGenericApi().getFilteredList(SupportingStaffDesignations.class, null,null);
		return supportingStaffDesignations;
	}
	
	public static List<Object> getNationalities() {
		if(nationalities.isEmpty())
			nationalities = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Nationality.class, null,null);
		return nationalities;
	}
	
	public static List<Object> getSubjects() {
		if(subjects.isEmpty())
			subjects = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Subject.class, null,null);
		return subjects;
	}
	
	public static List<Object> getSchoolTypes() {
		if(schoolTypes.isEmpty())
			schoolTypes = DBCommunicator.getApiServices().getGenericApi().getFilteredList(SchoolType.class, null,null);
		return schoolTypes;
	}
}