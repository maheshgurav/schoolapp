package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.CachedDataStore;
import com.samarthsoft.prabandhak.common.ModelAttributesCommon;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.entities.State;
import com.samarthsoft.prabandhak.entities.SupportStaff;
import com.samarthsoft.prabandhak.form.entities.SupportingStaffForm;
import com.samarthsoft.prabandhak.utilities.SupportingStaffControllerUtility;
import com.samarthsoft.prabandhak.webapp.validators.SupportingStaffValidator;

@Controller
public class SupportingStaffFormController {
	
	@Autowired
	private SupportingStaffValidator supportingStaffValidator;

    @RequestMapping(value = "/addsupportingstaffmember", method = RequestMethod.GET)
    public ModelAndView renderAddSupportingStaffMemberForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	SupportingStaffForm supportingStaffFormObject = new SupportingStaffForm();
    	model.put("staff", supportingStaffFormObject);
    	return new ModelAndView("staff_form",model);
    }
    
    @RequestMapping(value = "/editsupportingstaffmember", method = RequestMethod.GET)
    public ModelAndView renderEditSupportingStaffMemberForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	String staffGuid = RequestCommon.getAttributeValueFromRequest("id",request);
    	SupportingStaffControllerUtility staffControllerUtility = new SupportingStaffControllerUtility();
    	SupportingStaffForm staffFormObject = staffControllerUtility.convertOriginalObjectToFromObject(staffControllerUtility.getSupportingStaffForEditOrView(staffGuid));
    	model.put("staff", staffFormObject);
    	return new ModelAndView("staff_form",model);
    }
    
    @RequestMapping(value = "/addsupportingstaffmember", method = RequestMethod.POST)
    public ModelAndView addNewSupportingStaffMember(@Valid SupportingStaffForm supportingStaffFormObject,BindingResult bindingResult,HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	SupportingStaffControllerUtility staffControllerUtility = new SupportingStaffControllerUtility();
	    	SupportStaff supportStaff = staffControllerUtility.convertFormObjectToOriginalObject(supportingStaffFormObject);
	    	supportStaff.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
	    	if(staffControllerUtility.checkHasAssociatedLoginInformation(supportStaff.getDesignation())){
	    		LoginInformation loginInformation = supportStaff.getLoginInformation();
		    	loginInformation.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
		    	if(DBCommunicator.getApiServices().getCommonApi().insertObjectWithLoginInformation(supportStaff, supportStaff.getLoginInformation()))
	    		return new ModelAndView("redirect:/" + "supportingstaffmembers.htm?saveSuccess=true");
	    	}
	    	if(DBCommunicator.getApiServices().getGenericApi().insert(supportStaff))
		    	return new ModelAndView("redirect:/" + "supportingstaffmembers.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("staff", supportingStaffFormObject);
		return new ModelAndView("staff_form",model);
    }
    
    @RequestMapping(value = "/editsupportingstaffmember", method = RequestMethod.POST)
    public ModelAndView updateSupportingStaffMember(@Valid SupportingStaffForm supportingStaffFormObject,BindingResult bindingResult,HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!bindingResult.hasErrors()){
	    	SupportingStaffControllerUtility staffControllerUtility = new SupportingStaffControllerUtility();
	    	SupportStaff supportStaff = staffControllerUtility.convertFormObjectToOriginalObject(supportingStaffFormObject);
	    	supportStaff.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
	    	if(staffControllerUtility.checkHasAssociatedLoginInformation(supportStaff.getDesignation())){
		    	LoginInformation loginInformation = supportStaff.getLoginInformation();
		    	loginInformation.setOrganizationGuid(RequestCommon.getApplicationSession(request).getOrganizationGuid());
		    	if(DBCommunicator.getApiServices().getCommonApi().updateObjectWithLoginInformation(supportStaff, loginInformation))
		    		return new ModelAndView("redirect:/" + "supportingstaffmembers.htm?saveSuccess=true");
	    	}
	    	if(DBCommunicator.getApiServices().getGenericApi().update(supportStaff))
		    	return new ModelAndView("redirect:/" + "supportingstaffmembers.htm?saveSuccess=true");
    	}
    	ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
    	model.put("staff", supportingStaffFormObject);
		return new ModelAndView("staff_form",model);
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(supportingStaffValidator);
    }
    
    @ModelAttribute(value="designations")
    private List<Object> setSupportingStaffDesignationsInModel(){
    	return CachedDataStore.getSupportingStaffDesignations();
    }
    
    @ModelAttribute(value="states")
    private List<State> setStatesInModel(){
    	return ModelAttributesCommon.getStatesForModelAttributes();
    }
}