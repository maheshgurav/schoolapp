package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.entities.SupportStaff;
import com.samarthsoft.prabandhak.entities.Teacher;
import com.samarthsoft.prabandhak.form.entities.ApplicationSession;
import com.samarthsoft.prabandhak.form.entities.Dashboard;

@Controller
public class DashboardController {

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView renderDashboard(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	ApplicationSession applicationSession = RequestCommon.getApplicationSession(request);
    	Dashboard dashboard = new Dashboard();
    	dashboard.setUserName(applicationSession.getUserName());
    	
    	List<Object> result = DBCommunicator.getApiServices().getGenericApi().getAggregatedCount(Student.class, "gender");
    	Map<String, Integer> studentCountMap = new HashMap<String, Integer>();
    	studentCountMap.put("Male",0);
    	studentCountMap.put("Female",0);
    	for (Object object : result) {
    		Object[] tuple =  (Object[])object;
    		studentCountMap.put(tuple[0].toString(),Integer.parseInt(tuple[1].toString()));
		}
    	
    	model.put("dashboard", dashboard);
        model.put("total_students", studentCountMap.get("Female") + studentCountMap.get("Male"));
        model.put("total_boys", studentCountMap.get("Male"));
        model.put("total_girls", studentCountMap.get("Female"));

        List<Object> result1 = DBCommunicator.getApiServices().getGenericApi().getAggregatedCount(Teacher.class, "gender");
    	Map<String, Integer> teacherCountMap = new HashMap<String, Integer>();
    	teacherCountMap.put("Male",0);
    	teacherCountMap.put("Female",0);
    	for (Object object : result1) {
    		Object[] tuple =  (Object[])object;
    		if(tuple[0]!=null && tuple[1]!=null)
    			teacherCountMap.put(tuple[0].toString(),Integer.parseInt(tuple[1].toString()));
		}
        model.put("total_teachers",teacherCountMap.get("Male") + teacherCountMap.get("Female"));
        model.put("total_male_teachers",teacherCountMap.get("Male"));
        model.put("total_female_teachers",teacherCountMap.get("Female"));

    	List<Object> result2 = DBCommunicator.getApiServices().getGenericApi().getAggregatedCount(SupportStaff.class, "gender");
    	Map<String, Integer> staffCountMap = new HashMap<String, Integer>();
    	staffCountMap.put("Male",0);
    	staffCountMap.put("Female",0);
    	for (Object object : result2) {
    		Object[] tuple =  (Object[])object;
    		if(tuple[0]!=null && tuple[1]!=null)
    			staffCountMap.put(tuple[0].toString(),Integer.parseInt(tuple[1].toString()));
		}
        model.put("total_staff",staffCountMap.get("Male") + staffCountMap.get("Female"));
        model.put("total_male_staff",staffCountMap.get("Male"));
        model.put("total_female_staff",staffCountMap.get("Female"));
        try{
	    	List<Object> result3 = DBCommunicator.getApiServices().getGenericApi().getAggregatedCount(Student.class, "category");
	    	JSONArray array = new JSONArray();
	    	for (Object object : result3) {
	    		Object[] tuple =  (Object[])object;
	        	JSONObject jsonObject = new JSONObject();
	        	jsonObject.put("name",tuple[0].toString());
	        	JSONArray arr = new JSONArray();
	        	arr.put(Integer.parseInt(tuple[1].toString()));
	        	jsonObject.put("data",arr);
	        	array.put(jsonObject);
	    	}
	    	model.put("category_data",array.toString());
        }catch(Exception ex){
        	ex.printStackTrace();
        }
    	
    	
    	return new ModelAndView("dashboard");
    }
}