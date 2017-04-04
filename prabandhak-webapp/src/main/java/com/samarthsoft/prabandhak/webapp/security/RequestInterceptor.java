package com.samarthsoft.prabandhak.webapp.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;

public class RequestInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object object) throws Exception {
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
        
        if (applicationSession == null && !checkWhetherGivenRequest(request, "/login.htm")){
            response.sendRedirect("login.htm");
            return false;
        }
        
    	return true;
    }
    
    private boolean checkWhetherGivenRequest(HttpServletRequest request,String urlToCheck){
    	return request.getRequestURL().toString().endsWith(urlToCheck);
    }
}