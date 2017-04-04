package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.entities.SupportStaff;
import com.samarthsoft.prabandhak.form.entities.SupportingStaffForm;
import com.samarthsoft.prabandhak.utilities.CommonControllerUtility;
import com.samarthsoft.prabandhak.utilities.SupportingStaffControllerUtility;

@Controller
public class SupportingStaffListController {
	
	@Autowired
	private SupportingStaffControllerUtility supportingStaffControllerUtility;
	@Autowired
	private CommonControllerUtility commonControllerUtility;

    @RequestMapping(value = "/supportingstaffmembers", method = RequestMethod.GET)
    public ModelAndView listSupportingStaffMembers(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	int pageNumber = 1;
    	if(RequestCommon.getAttributeValueFromRequest("page", request)!=null && !RequestCommon.getAttributeValueFromRequest("page", request).isEmpty())
    		pageNumber = Integer.parseInt(RequestCommon.getAttributeValueFromRequest("page", request));
    	PaginationObject staffs = DBCommunicator.getApiServices().getGenericApi().getPaginatedList(SupportStaff.class, null,null,pageNumber);
    	
    	List<SupportingStaffForm> staffFormObjects = new ArrayList<SupportingStaffForm>();
		if(staffs!=null){
			for (Object object : staffs.getRecords()) {
				SupportStaff staff = (SupportStaff)object;
				staffFormObjects.add(supportingStaffControllerUtility.convertOriginalObjectToFromObject(staff));
			}
		}
		model.put("staffs", staffFormObjects);
		commonControllerUtility.setCommonAttributesOfPagination(staffs, model, pageNumber);
    	return new ModelAndView("staff_list",model);
    }
}