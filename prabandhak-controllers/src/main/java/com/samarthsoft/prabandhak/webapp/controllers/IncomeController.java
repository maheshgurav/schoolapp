package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.IncomeDetails;
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.form.entities.IncomeFormObject;
import com.samarthsoft.prabandhak.utilities.CommonControllerUtility;
import com.samarthsoft.prabandhak.utilities.IncomeControllerUtility;
import com.samarthsoft.prabandhak.webapp.validators.IncomeValidator;

@Controller
public class IncomeController {
	
	@Autowired
	private IncomeControllerUtility incomeControllerUtility; 
	@Autowired
	private IncomeValidator incomeValidator;
	@Autowired
	private CommonControllerUtility commonControllerUtility;
	
	@RequestMapping(value = "/income", method = RequestMethod.GET)
	public ModelAndView renderIncomeList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int pageNumber = commonControllerUtility.getPageNumber(request);
		PaginationObject incomes = DBCommunicator.getApiServices().getGenericApi().getPaginatedList(IncomeDetails.class, null, null, pageNumber);
		List<Object> incomeObjects = incomes.getRecords(); 
		List<IncomeFormObject> result = new ArrayList<IncomeFormObject>();
		for (Object object : incomeObjects) {
			IncomeDetails incomeDetails = (IncomeDetails) object;
			result.add(incomeControllerUtility.getIncomeFormObject(incomeDetails));
		}
		commonControllerUtility.setCommonAttributesOfPagination(incomes, model, pageNumber);
		model.put("incomes", result);
		return new ModelAndView("income_list", model);
	}

	@RequestMapping(value = "/addincome", method = RequestMethod.GET)
	public ModelAndView ShowAddIncomeForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		IncomeFormObject incomeFormObject = new IncomeFormObject();
		model.put("income", incomeFormObject);
		return new ModelAndView("income_form", model);
	}

	@RequestMapping(value = "/addincome", method = RequestMethod.POST)
	public ModelAndView SubmitAddIncomeForm(@Valid IncomeFormObject incomeFormObject, BindingResult bindingresult,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if(!bindingresult.hasErrors()){
			boolean result = DBCommunicator.getApiServices().getGenericApi().insert(incomeControllerUtility.getIncomeDetailsObject(incomeFormObject));
			if(result){
				return new ModelAndView("redirect:/income.htm", model);
			}
		}
		ValidatorCommon.checkAndAddFieldErrors(bindingresult, model);
		model.put("income", incomeFormObject);
		return new ModelAndView("income_form", model);
	}

	@RequestMapping(value = "/editincome", method = RequestMethod.GET)
	public ModelAndView ShowEditIncomeForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String id = RequestCommon.getAttributeValueFromRequest("id", request);
		if(id!=null && !id.equals("")){
			IncomeFormObject incomeFormObject = incomeControllerUtility.getIncomeFormObject((IncomeDetails)DBCommunicator.getApiServices().getGenericApi().fetchObjectbyID(IncomeDetails.class, id));
			model.put("income", incomeFormObject);
		}
		return new ModelAndView("income_form", model);
	}

	@RequestMapping(value = "/editincome", method = RequestMethod.POST)
	public ModelAndView SubmitEditIncomeForm(
			@Valid IncomeFormObject incomeFormObject, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		if(!result.hasErrors()){
			Boolean resultFromDB = false;
			resultFromDB= DBCommunicator.getApiServices().getGenericApi().update(incomeControllerUtility.getIncomeDetailsObject(incomeFormObject));
			if(resultFromDB){
				return new ModelAndView("redirect:/income.htm", model);
			}
		}
		ValidatorCommon.checkAndAddFieldErrors(result, model);
		model.put("income", incomeFormObject);
		return new ModelAndView("income_form", model);
	}
	
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    	binder.setValidator(incomeValidator);
    }
}