package com.samarthsoft.prabandhak.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Caste;
import com.samarthsoft.prabandhak.entities.Category;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.Exam;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.ScholarshipType;
import com.samarthsoft.prabandhak.entities.State;
import com.samarthsoft.prabandhak.entities.Subject;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.enums.UserRole;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;

public class ModelAttributesCommon {

	public static List<State> getStatesForModelAttributes() {
		List<State> states = new ArrayList<State>();
		for (State state : CachedDataStore.getStates().values()) {
			states.add(state);
		}
		return states;
	}

	public static List<Caste> getCastesForModelAttributes() {
		List<Caste> castes = new ArrayList<Caste>();
		for (Caste caste : CachedDataStore.getCastes().values()) {
			castes.add(caste);
		}
		return castes;
	}

	public static List<Category> getCategoriesForModelAttributes() {
		List<Category> catgories = new ArrayList<Category>();
		for (Category category : CachedDataStore.getCategories().values()) {
			catgories.add(category);
		}
		return catgories;
	}

	public static List<ClassInfo> getClassInfoForModelAttributes(ApplicationSession applicationSession) {
		List<ClassInfo> classes = new ArrayList<ClassInfo>();
		for (ClassInfo classInfo : CachedDataStore.getClassInfo().values()) {
			if(applicationSession.getUserRole() == UserRole.TEACHER &&  applicationSession.getAssociatedClassGuid()!=null && classInfo.getGuid().equals(applicationSession.getAssociatedClassGuid()))
				classes.add(classInfo);
			if(applicationSession.getUserRole() != UserRole.TEACHER)
				classes.add(classInfo);
		}
		return classes;
	}

	public static Set<String> getClassesForModelAttributes(ApplicationSession applicationSession) {
		Set<String> classes = new HashSet<String>();
		for (ClassInfo classInfo : CachedDataStore.getClassInfo().values()) {
			if(applicationSession.getUserRole() == UserRole.TEACHER &&  applicationSession.getAssociatedClassGuid()!=null && classInfo.getGuid().equals(applicationSession.getAssociatedClassGuid()))
				classes.add(classInfo.getStandardOrClass());
			if(applicationSession.getUserRole() != UserRole.TEACHER)
				classes.add(classInfo.getStandardOrClass());
		}
		return classes;
	}
	
	public static List<Object> getExamsForModelAttributes(ApplicationSession applicationSession) {
		List<Filter> filters = new ArrayList<Filter>();
		List<Object> objectsToSelect = new ArrayList<Object>();
		//objectsToSelect.add(applicationSession.getAssociatedClassGuid());
		filters.add(new Filter("standards", applicationSession.getAssociatedClassGuid(), RestrictionType.LIKE));
		List<Object> result = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Exam.class, filters, null);
		return result;
	}

	public static List<ScholarshipType> getScholarshipTypesForModelAttributes() {
		List<ScholarshipType> scholarshipTypes = new ArrayList<ScholarshipType>();
		for (ScholarshipType scholarshipType : CachedDataStore
				.getScholashipTypes().values()) {
			scholarshipTypes.add(scholarshipType);
		}
		return scholarshipTypes;
	}
	
	public static List<Subject> getSubjectsForModelAttributes() {
		List<Subject> subjects = new ArrayList<Subject>();
		for (Object object : CachedDataStore.getSubjects()) {
			subjects.add((Subject)object);
		}
		return subjects;
	}
	
	public static List<Object> getSchoolTypesForModelAttributes() {
		return CachedDataStore.getSchoolTypes();
	}

}