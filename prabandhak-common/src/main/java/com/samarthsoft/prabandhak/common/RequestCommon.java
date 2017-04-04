package com.samarthsoft.prabandhak.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.samarthsoft.prabandhak.form.entities.ApplicationSession;

public class RequestCommon {
	private RequestCommon(){
		//Utility class constructor
	}
	
	public static ApplicationSession getApplicationSession(HttpServletRequest request){
		ApplicationSession applicationSession = (ApplicationSession) request.getSession().getAttribute(
                "session");
		return applicationSession;
	}
	
	public static String getAttributeValueFromRequest(String requestParameterName,HttpServletRequest request){
		Map<?, ?> requestParameterMap = request.getParameterMap();
		String[] requestParameterValues = (String[]) requestParameterMap.get(requestParameterName);
	        String resultValue = "";
	        if (requestParameterValues != null)
	            resultValue = requestParameterValues[0];
	        return resultValue;
	}
}