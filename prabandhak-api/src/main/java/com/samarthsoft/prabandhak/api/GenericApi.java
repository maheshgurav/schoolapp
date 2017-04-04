package com.samarthsoft.prabandhak.api;

import java.util.List;

import com.samarthsoft.prabandhak.entities.DBOperationEntity;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.entities.SelectValues;

public interface GenericApi {
	Object fetchObjectbyID(Class<?> clazz,Object recordId);

	Boolean insert(Object record);
	
	Boolean update(Object record);
	
	Boolean delete(Object record);
	
	Boolean insertOrUpdate(Object record);
	
	List<Object> getObjectByColumnName(Class<?> clazz,String columnName,String columnValue);
	
	List<Object> getFilteredList(Class<?> clazz, List<Filter> filters, List<SelectValues> selectValues);
	
	Boolean bulkInsert(List<Object> records);
	
	Boolean bulkUpdate(List<Object> records);
	
	Boolean bulkInserOrUpdate(List<DBOperationEntity> records);
	
	PaginationObject getPaginatedList(Class<?> clazz,
			List<Filter> filters, List<SelectValues> selectValues,
			Integer pageNumber);
	
	List<Object> getAggregatedCount(Class<?> clazz,String propertyName);
	
	Boolean insertOrUpdate(Object record,String updateField);
	
	List<Object> getFilteredListWithOrder(Class<?> clazz, List<Filter> filters, List<SelectValues> selectValues, String orderBy, String order);
}