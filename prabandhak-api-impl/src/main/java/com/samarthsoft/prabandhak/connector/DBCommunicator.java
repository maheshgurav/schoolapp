package com.samarthsoft.prabandhak.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samarthsoft.prabandhak.api.ApiServices;
import com.samarthsoft.prabandhak.api.impl.ApiServicesImpl;

public class DBCommunicator {
	
	private static final Logger logger = LoggerFactory.getLogger(DBCommunicator.class);
	private static ApiServices apiServices = null;

	public static ApiServices getApiServices() {
		try {
			if (apiServices == null)
				apiServices = new ApiServicesImpl(
						HibernateUtil.getSessionFactory());
		} catch (Exception e) {
			logger.error("",e);
		}
		return apiServices;
	}
	

}
