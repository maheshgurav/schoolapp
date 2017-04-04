package com.samarthsoft.prabandhak.utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.ClassInfo;
import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.entities.Rights;
import com.samarthsoft.prabandhak.entities.SupportStaff;
import com.samarthsoft.prabandhak.entities.Teacher;
import com.samarthsoft.prabandhak.entities.TeacherDesignations;
import com.samarthsoft.prabandhak.enums.UserRole;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;

@Component
public class LoginControllerUtility {

	@Autowired
	private TeacherControllerUtility teacherControllerUtility;
	@Autowired
	private SupportingStaffControllerUtility staffControllerUtility;
	private LoginControllerUtility(){
		//Private constructor
	}

	public Rights getUserRights(UserRole userRole){
		Rights userRights = new Rights();
		userRights.setCreateStudent((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.TEACHER || userRole == UserRole.SUPPORTING_STAFF));
		userRights.setViewStudent((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.TEACHER || userRole == UserRole.SUPPORTING_STAFF));
		userRights.setCreateTeacher((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.SUPPORTING_STAFF));
		userRights.setViewTeacher((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.SUPPORTING_STAFF));
		userRights.setGenerateReports((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.TEACHER || userRole == UserRole.SUPPORTING_STAFF));
		userRights.setFillAttendance((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.TEACHER || userRole == UserRole.SUPPORTING_STAFF));
		userRights.setFillExamResults((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.TEACHER || userRole == UserRole.SUPPORTING_STAFF));
		userRights.setViewOrUpdatesettings((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.SUPPORTING_STAFF || userRole == UserRole.SYSTEM_OWNER));
		userRights.setIssueBonafideOrLcCertificate((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.SUPPORTING_STAFF));
		userRights.setGenerateGeneralRegister((userRole == UserRole.SCHOOL_ADMIN || userRole == UserRole.SUPPORTING_STAFF)); 
		return userRights;
	}
	
	public LoginInformation getLoginInformationByUserName(String userName){
		List<Object> result = DBCommunicator.getApiServices().getGenericApi().getObjectByColumnName(LoginInformation.class, "userName", userName);
		LoginInformation loginInformation = null;
		if(result!=null && result.size()>0){
			loginInformation = (LoginInformation)result.get(0);
		}
		return loginInformation;
	}
	
    public ApplicationSession getApplicationSession(LoginInformation loginInformation){
    	ApplicationSession session = new ApplicationSession();
    	session.setApplicationLocale(LocaleContextHolder.getLocale());
    	session.setCtx(SecurityContextHolder.getContext());
    	session.setUserRights(getUserRights(loginInformation.getUserRole()));
    	session.setSchoolGuid(loginInformation.getSchoolGuid());
    	session.setUserGuid(loginInformation.getGuid());
    	session.setUserRole(loginInformation.getUserRole());
    	if(loginInformation.getUserRole().getRoleId().equals(UserRole.TEACHER.getRoleId())){
    		Teacher teacher = teacherControllerUtility.getTeacher(loginInformation.getGuid());
    		session.setUserName(teacher.getName().toString());
    		if(teacher!=null){
    			ClassInfo classInfo = (ClassInfo) DBCommunicator.getApiServices().getGenericApi().getObjectByColumnName(ClassInfo.class, "classTeacherGuid", teacher.getGuid()).get(0);
    			session.setAssociatedClassGuid(classInfo.getGuid());
    		}
    	}
    	if(loginInformation.getUserRole().getRoleId().equals(UserRole.SUPPORTING_STAFF.getRoleId())){
    		SupportStaff staff = staffControllerUtility.getSupportingStaffForEditOrView(loginInformation.getGuid());
    		session.setUserName(staff.getName().toString());
    	}
    	return session;
    }
}