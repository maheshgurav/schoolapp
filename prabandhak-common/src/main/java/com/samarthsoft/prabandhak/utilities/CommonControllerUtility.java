package com.samarthsoft.prabandhak.utilities;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.ApplicationConstants;
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.entities.Student;

@Component
public class CommonControllerUtility {
	
	public String getStandardAndDivision(Student student){
		if(student.getDivision()!=null && !student.getDivision().isEmpty())
			return student.getStandard() + "-" + student.getDivision();
		return student.getStandard();
	}
	
	public void setCommonAttributesOfPagination(
			PaginationObject paginationObject, Map<String, Object> myModel,
			int pageNumber) {
		Long totalNumberOfPages = 1l;
		if ((paginationObject.getRecordCount() % ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE) == 0)
			totalNumberOfPages = paginationObject.getRecordCount()
					/ ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE;
		else
			totalNumberOfPages = (paginationObject.getRecordCount() / ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE) + 1;
		myModel.put("is_first_page", (pageNumber == 1));
		myModel.put("is_last_page", (pageNumber == totalNumberOfPages));
		myModel.put("number_of_pages", totalNumberOfPages);
		myModel.put(
				"recordsFrom",
				((pageNumber - 1) * ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE) + 1);
		myModel.put(
				"recordsTo",
				(((pageNumber - 1) * ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE))
						+ paginationObject.getRecords().size());
		myModel.put("totalNumberOfRecords", paginationObject.getRecordCount());
		myModel.put("selected_student_page", pageNumber);
		myModel.put("records_per_page",
				ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE);
		processStartAndEndPageNumber(myModel, pageNumber, totalNumberOfPages);
	}

	public void processStartAndEndPageNumber(Map<String, Object> myModel,
			int pageNumber, Long totalNumberOfPages) {
		Long startPageNumber = 0l;
		if (pageNumber % 20 == 0l && (pageNumber / 20 > 1))
			startPageNumber = (long) ((20 * ((pageNumber / 20) - 1)) + 1);
		if (pageNumber % 20 == 0l && (pageNumber / 20 == 1))
			startPageNumber = 1l;
		if (pageNumber % 20 != 0l)
			startPageNumber = (long) (1 + (20 * (pageNumber / 20)));
		Long endPageNumber = 0l;
		if ((totalNumberOfPages - startPageNumber) > 20)
			endPageNumber = startPageNumber + 20 - 1;
		else
			endPageNumber = startPageNumber
					+ (totalNumberOfPages - startPageNumber);
		myModel.put("startPageNumber", startPageNumber);
		myModel.put("endPageNumber", endPageNumber);
		if ((startPageNumber - 20) < 0)
			myModel.put("showPreviousPage", false);
		else
			myModel.put("showPreviousPage", true);

		if ((totalNumberOfPages - endPageNumber) == 0)
			myModel.put("showNextPage", false);
		else
			myModel.put("showNextPage", true);
	}
	
	public Integer getPageNumber(HttpServletRequest request){
    	int pageNumber = 1;
    	if(RequestCommon.getAttributeValueFromRequest("page", request)!=null && !RequestCommon.getAttributeValueFromRequest("page", request).isEmpty())
    		pageNumber = Integer.parseInt(RequestCommon.getAttributeValueFromRequest("page", request));
    	return pageNumber;
	}
	
	public PaginationObject getObjectsByClass(Class<?> clazz,Integer pageNumber){
		return DBCommunicator.getApiServices().getGenericApi().getPaginatedList(clazz, null, null,pageNumber);
	}
	
	public void setFiltersInModel(String filter,ModelMap model,HttpServletRequest request){
		String[] studentFilters = filter.split(",");
		for(int count = 0; count<studentFilters.length; count++){
			model.put(studentFilters[count],RequestCommon.getAttributeValueFromRequest(studentFilters[count], request).toString());
		}
	}
}