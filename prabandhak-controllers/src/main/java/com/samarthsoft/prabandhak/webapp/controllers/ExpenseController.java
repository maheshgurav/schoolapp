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
import com.samarthsoft.prabandhak.entities.ExpenseDetails;
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.form.entities.ExpenseFormObject;
import com.samarthsoft.prabandhak.utilities.CommonControllerUtility;
import com.samarthsoft.prabandhak.utilities.ExpenseControllerUtility;
import com.samarthsoft.prabandhak.webapp.validators.ExpenseValidator;

@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseControllerUtility expenseControllerUtility;
	@Autowired
	private ExpenseValidator expenseValidator;
	@Autowired
	private CommonControllerUtility commonControllerUtility;

	@RequestMapping(value = "/expense", method = RequestMethod.GET)
	public ModelAndView renderExpenseList(HttpServletRequest request,
	        HttpServletResponse response,ModelMap model) {
		int pageNumber = commonControllerUtility.getPageNumber(request);
		PaginationObject expenses = DBCommunicator.getApiServices().getGenericApi().getPaginatedList(ExpenseDetails.class, null, null, pageNumber);
		List<Object> expenseObjects = expenses.getRecords(); 
		List<ExpenseFormObject> result = new ArrayList<ExpenseFormObject>();
		for (Object object : expenseObjects) {
			ExpenseDetails expenseDetails = (ExpenseDetails) object;
			result.add(expenseControllerUtility.getExpenseFormObject(expenseDetails));
		}
		commonControllerUtility.setCommonAttributesOfPagination(expenses, model, pageNumber);
		model.put("expenses", result);
		return new ModelAndView("expense_list", model);
	}

	@RequestMapping(value = "/addexpense", method = RequestMethod.GET)
    public ModelAndView ShowAddExpenseForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
		ExpenseFormObject expenseFormObject = new ExpenseFormObject();
		model.put("expense", expenseFormObject);
		return new ModelAndView("expense_form",model);
    }
	
	@RequestMapping(value = "/addexpense", method = RequestMethod.POST)
    public ModelAndView SubmitAddExpenseForm(@Valid ExpenseFormObject expenseFormObject, BindingResult bindingResult, 
    		HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		if(!bindingResult.hasErrors()){
			boolean result = DBCommunicator.getApiServices().getGenericApi().insert(expenseControllerUtility.getExpenseDetailsObject(expenseFormObject));
			if(result){
				return new ModelAndView("expense_list", model);
			}
		}
		ValidatorCommon.checkAndAddFieldErrors(bindingResult, model);
		model.put("expense", expenseFormObject);
		return new ModelAndView("expense_form", model);
    }

	@RequestMapping(value = "/editexpense", method = RequestMethod.GET)
    public ModelAndView ShowEditExpenseForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
		String id = RequestCommon.getAttributeValueFromRequest("id", request);
		if(id!=null && !id.equals("")){
			ExpenseFormObject expenseFormObject = expenseControllerUtility.getExpenseFormObject((ExpenseDetails)DBCommunicator.getApiServices().getGenericApi().fetchObjectbyID(ExpenseDetails.class, id));
			model.put("expense", expenseFormObject);
		}
		return new ModelAndView("expense_form",model);
    }
	
	@RequestMapping(value = "/editexpense", method = RequestMethod.POST)
    public ModelAndView SubmitEditExpenseForm(@Valid ExpenseFormObject expenseFormObject , BindingResult result,HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
		if(!result.hasErrors()){
			Boolean resultFromDB = false;
			resultFromDB= DBCommunicator.getApiServices().getGenericApi().update(expenseControllerUtility.getExpenseDetailsObject(expenseFormObject));
			if(resultFromDB){
				return new ModelAndView("redirect:/expense.htm", model);
			}
		}
		ValidatorCommon.checkAndAddFieldErrors(result, model);
		model.put("expense", expenseFormObject);
		return new ModelAndView("expense_form", model);
    }
	
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    	binder.setValidator(expenseValidator);
    }
}