package com.samarthsoft.prabandhak.api.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samarthsoft.prabandhak.api.GenericApi;
import com.samarthsoft.prabandhak.entities.ApplicationConstants;
import com.samarthsoft.prabandhak.entities.DBOperationEntity;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.entities.SelectValues;
import com.samarthsoft.prabandhak.enums.CrudOperations;
import com.samarthsoft.prabandhak.enums.RestrictionType;

public class GenericApiImpl extends ApiBaseImpl implements GenericApi {
	private Logger LOG = LoggerFactory.getLogger(GenericApiImpl.class);

	public GenericApiImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Object fetchObjectbyID(Class<?> clazz, Object record) {
		Session session = null;
		Transaction transaction = null;
		Object resultRecord = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			resultRecord = session.get(clazz, record.toString());
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			LOG.error("", ex);
		} finally {
			if (session != null)
				session.close();
		}
		return resultRecord;
	}

	public Boolean insert(Object record) {
		return doCRUDOperations(record, CrudOperations.INSERT);
	}

	public Boolean update(Object record) {
		return doCRUDOperations(record, CrudOperations.UPDATE);
	}

	public Boolean delete(Object record) {
		return doCRUDOperations(record, CrudOperations.DELETE);
	}

	public Boolean insertOrUpdate(Object record) {
		return doCRUDOperations(record, CrudOperations.INSERTORUPDATE);
	}

	public Boolean insertOrUpdate(Object record,String updateField) {
		Boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(updateField,record);
			transaction.commit();
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			transaction.rollback();
			LOG.error("", ex);
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}
	
	private Boolean doCRUDOperations(Object record, CrudOperations crudOperation) {
		Boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			performOperation(record, crudOperation, session);
			transaction.commit();
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			transaction.rollback();
			LOG.error("", ex);
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}

	private void performOperation(Object record, CrudOperations crudOperation,
			Session session) {
		switch (crudOperation) {
		case INSERT:
			session.save(record);
			break;
		case INSERTORUPDATE:
			session.saveOrUpdate(record);
			break;
		case UPDATE:
			session.update(record);
			break;
		case DELETE:
			session.delete(record);
			break;
		}
	}

	public List<Object> getObjectByColumnName(Class<?> clazz,
			String columnName, String columnValue) {
		Session session = null;
		Transaction transaction = null;
		List<Object> resultRecord = new ArrayList<Object>();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			criteria.add(Restrictions.eq(columnName, columnValue));
			resultRecord = casteList(clazz, criteria.list());
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			LOG.error("", ex);
		} finally {
			if (session != null)
				session.close();
		}
		return resultRecord;
	}

	public List<Object> getFilteredListWithOrder(Class<?> clazz, List<Filter> filters,
			List<SelectValues> selectValues,String orderBy,String order) {
		Session session = null;
		List<Object> resultRecords = new ArrayList<Object>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(clazz);
			if(filters!=null){
				for (Filter filter : filters) {
					if (filter.getFilterType() == RestrictionType.EQ)
						criteria.add(Restrictions.eq(filter.getFilterName(), filter.getFilterValue()));
					if (filter.getFilterType() == RestrictionType.IN) 
						criteria.add(Restrictions.in(filter.getFilterName(), (ArrayList<?>) filter.getFilterValue()));
					if (filter.getFilterType() == RestrictionType.LIKE) 
						criteria.add(Restrictions.like(filter.getFilterName(), filter.getFilterValue().toString(),MatchMode.ANYWHERE));
					if (filter.getFilterType() == RestrictionType.GE) 
						criteria.add(Restrictions.ge(filter.getFilterName(), filter.getFilterValue()));
					if (filter.getFilterType() == RestrictionType.LE) 
						criteria.add(Restrictions.le(filter.getFilterName(), filter.getFilterValue()));
				}
			}
			if(orderBy!=null && !orderBy.isEmpty() && (order==null || order.equals("asc"))){
				criteria.addOrder(Order.asc(orderBy));
			}
			if(orderBy!=null && !orderBy.isEmpty() && (order!=null && order.equals("desc"))){
				criteria.addOrder(Order.desc(orderBy));
			}
			if(selectValues!=null){
				ProjectionList projectionList = Projections.projectionList();
				for(SelectValues selectValue : selectValues){
					projectionList.add(Projections.property(selectValue.getProjectionPropertyName()),selectValue.getProjectionPropertyName());
				}
				criteria.setProjection(projectionList);
				resultRecords = (List<Object>) criteria.setResultTransformer(Transformers.aliasToBean(clazz)).list();
			}else resultRecords = casteList(clazz,criteria.list());
			//resultRecords = (List<Object>) criteria.setResultTransformer(Transformers.aliasToBean(clazz)).list();
			
		} catch (Exception ex) {
			LOG.error("", ex);
		} finally {
			if (session != null)
				session.close();
		}
		return resultRecords;
	}
	
	public List<Object> getFilteredList(Class<?> clazz, List<Filter> filters,
			List<SelectValues> selectValues) {
		Session session = null;
		List<Object> resultRecords = new ArrayList<Object>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(clazz);
			if(filters!=null){
				for (Filter filter : filters) {
					if (filter.getFilterType() == RestrictionType.EQ)
						criteria.add(Restrictions.eq(filter.getFilterName(), filter.getFilterValue()));
					if (filter.getFilterType() == RestrictionType.IN) 
						criteria.add(Restrictions.in(filter.getFilterName(), (ArrayList<?>) filter.getFilterValue()));
					if (filter.getFilterType() == RestrictionType.LIKE) 
						criteria.add(Restrictions.like(filter.getFilterName(), filter.getFilterValue().toString(),MatchMode.ANYWHERE));
					if (filter.getFilterType() == RestrictionType.GE) 
						criteria.add(Restrictions.ge(filter.getFilterName(), filter.getFilterValue()));
					if (filter.getFilterType() == RestrictionType.LE) 
						criteria.add(Restrictions.le(filter.getFilterName(), filter.getFilterValue()));
				}
			}
			if(selectValues!=null){
				ProjectionList projectionList = Projections.projectionList();
				for(SelectValues selectValue : selectValues){
					projectionList.add(Projections.property(selectValue.getProjectionPropertyName()),selectValue.getProjectionPropertyName());
				}
				criteria.setProjection(projectionList);
				resultRecords = (List<Object>) criteria.setResultTransformer(Transformers.aliasToBean(clazz)).list();
			}else resultRecords = casteList(clazz,criteria.list());
			//resultRecords = (List<Object>) criteria.setResultTransformer(Transformers.aliasToBean(clazz)).list();
			
		} catch (Exception ex) {
			LOG.error("", ex);
		} finally {
			if (session != null)
				session.close();
		}
		return resultRecords;
	}

	public Boolean bulkInsert(List<Object> records) {
		Boolean result = false;
		Session session = null;
		try{
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int count = 1;
		   for (Object object : records) {
			session.save(object);
		    if (count % 20 == 0) { 
		        session.flush();
		        session.clear();
		    }
		    count++;
		}
		   if (count < 20 ) { 
		        session.flush();
		        session.clear();
		    }
		tx.commit();
		result = true;
		}
		catch (Exception e) {
			LOG.error("", e);
		
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}

	public Boolean bulkUpdate(List<Object> records) {
		Boolean result = false;
		Session session = null;
		try{
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int count = 1;
		   for (Object object : records) {
			session.saveOrUpdate(object);
		    if (count % 20 == 0) { 
		        session.flush();
		        session.clear();
		    }
		    count++;
		}
	    if(count<20){
	        session.flush();
	        session.clear();
	    }
		tx.commit();
		result = true;
		}
		catch (Exception e) {
			LOG.error("", e);
		
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}
	
	public static <T> List<T> casteList(Class<? extends T> clazz,Collection<?> collection){
		List<T> result = new ArrayList<T>(collection.size());
		for (Object object : collection) {
			result.add(clazz.cast(object));
		}
		return result;
	}
	
	public PaginationObject getPaginatedList(Class<?> clazz,
			List<Filter> filters, List<SelectValues> selectValues,
			Integer pageNumber) {
		PaginationObject paginationObject = new PaginationObject();
		Session sessionForCountCriteria = null;
		Session sessionForListCriteria = null;
		try {
			sessionForCountCriteria = sessionFactory.openSession();
			sessionForListCriteria = sessionFactory.openSession();
			Criteria countCriteria = sessionForCountCriteria
					.createCriteria(clazz);
			Criteria listCriteria = sessionForListCriteria
					.createCriteria(clazz);
			countCriteria.setProjection(Projections.rowCount());
			setFilters(filters, countCriteria);
			Long totalRecordCount = 0l;
			if(countCriteria!=null && countCriteria.list()!=null)
				totalRecordCount = (Long) countCriteria.list().get(0);
			listCriteria.setFirstResult((pageNumber - 1) * ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE);
			listCriteria.setMaxResults(ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE);
			setFilters(filters, listCriteria);
			if (selectValues != null) {
				setProjections(selectValues, listCriteria);
				paginationObject.setRecords(casteList(clazz, listCriteria
						.setResultTransformer(Transformers.aliasToBean(clazz))
						.list()));
			} else {
				paginationObject.setRecords(casteList(clazz,
						listCriteria.list()));
			}
			paginationObject.setRecordCount(totalRecordCount);
		} catch (Exception e) {
			LOG.error("", e);

		} finally {
			if (sessionForCountCriteria != null){
				sessionForCountCriteria.clear();
				sessionForCountCriteria.close();
			}
			if (sessionForListCriteria != null){
				sessionForListCriteria.clear();
				sessionForListCriteria.close();
			}
		}
		return paginationObject;
	}
	
	private void setProjections(List<SelectValues> selectValues,
			Criteria criteria) {
		ProjectionList projectionList = Projections.projectionList();
		for (SelectValues selectValue : selectValues) {
			projectionList.add(Projections.property(selectValue
					.getProjectionPropertyName()), selectValue
					.getProjectionPropertyName());
		}
		criteria.setProjection(projectionList);
	}

	private void setFilters(List<Filter> filters, Criteria criteria) {
		if (filters != null) {
			for (Filter filter : filters) {
				if (filter.getFilterType() == RestrictionType.EQ)
					criteria.add(Restrictions.eq(filter.getFilterName(),
							filter.getFilterValue()));
				if (filter.getFilterType() == RestrictionType.IN)
					criteria.add(Restrictions.in(filter.getFilterName(),
							(ArrayList<?>) filter.getFilterValue()));
				if (filter.getFilterType() == RestrictionType.LIKE)
					criteria.add(Restrictions.like(filter.getFilterName(),
							filter.getFilterValue().toString(),
							MatchMode.ANYWHERE));
			}
		}
	}

	public Boolean bulkInserOrUpdate(List<DBOperationEntity> records) {
		Boolean result = false;
		Session session = null;
		try{
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int count = 1;
		   for (Object object : records) {
			DBOperationEntity dbOperationEntity = (DBOperationEntity) object;
			if(dbOperationEntity.getCrudOperations() == CrudOperations.INSERT)
				session.save(dbOperationEntity.getEntity());
			if(dbOperationEntity.getCrudOperations() == CrudOperations.UPDATE)
				session.update(dbOperationEntity.getUpdateOnField(),dbOperationEntity.getEntity());
		    if (count % 20 == 0) { 
		        session.flush();
		        session.clear();
		    }
		    count++;
		}
	    if(count<20){
	        session.flush();
	        session.clear();
	    }
		tx.commit();
		result = true;
		}
		catch (Exception e) {
			LOG.error("", e);
		
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}

	public List<Object> getAggregatedCount(Class<?> clazz,String propertyName) {
		List<Object> result = new ArrayList<Object>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(clazz);
			criteria.setProjection(Projections.projectionList().add(Projections.groupProperty(propertyName)).add(Projections.count(propertyName)));		
			result = criteria.list();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return result;
	}
}