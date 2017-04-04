package com.samarthsoft.prabandhak.api;

public interface ApiServices {

	GenericApi getGenericApi();
	
	CommonApi getCommonApi();
	
	AttendanceApi getAttendanceApi();
	
	StudentsApi getStudentsApi(); 
	
	DataBaseHeartBeatApi getDataBaseHeartBeatApi();
}