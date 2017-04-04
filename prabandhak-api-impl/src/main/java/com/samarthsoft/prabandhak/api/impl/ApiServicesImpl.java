package com.samarthsoft.prabandhak.api.impl;

import org.hibernate.SessionFactory;

import com.samarthsoft.prabandhak.api.ApiServices;
import com.samarthsoft.prabandhak.api.AttendanceApi;
import com.samarthsoft.prabandhak.api.CommonApi;
import com.samarthsoft.prabandhak.api.DataBaseHeartBeatApi;
import com.samarthsoft.prabandhak.api.GenericApi;
import com.samarthsoft.prabandhak.api.StudentsApi;

public class ApiServicesImpl extends ApiBaseImpl implements ApiServices {
	private GenericApi genericApi = null;
	private CommonApi commonApi = null;
	private AttendanceApi attendanceApi = null;
	private StudentsApi studentsApi = null;
	private DataBaseHeartBeatApi dataBaseHeartBeatApi = null;
	
	public ApiServicesImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public GenericApi getGenericApi() {
		if (genericApi == null) {
			genericApi = new GenericApiImpl(sessionFactory);
		}
		return genericApi;
	}
	
	public CommonApi getCommonApi() {
		if (commonApi == null) {
			commonApi = new CommonApiImpl(sessionFactory);
		}
		return commonApi;
	}

	public AttendanceApi getAttendanceApi() {
		if (attendanceApi == null) {
			attendanceApi = new AttendanceApiImpl(sessionFactory);
		}
		return attendanceApi;
	}
	
	public StudentsApi getStudentsApi() {
		if (studentsApi == null) {
			studentsApi = new StudentsApiImpl(sessionFactory);
		}
		return studentsApi;
	}

	public DataBaseHeartBeatApi getDataBaseHeartBeatApi() {
		if (dataBaseHeartBeatApi == null) {
			dataBaseHeartBeatApi = new DataBaseHeartBeatImpl(sessionFactory);
		}
		return dataBaseHeartBeatApi;
	}
}