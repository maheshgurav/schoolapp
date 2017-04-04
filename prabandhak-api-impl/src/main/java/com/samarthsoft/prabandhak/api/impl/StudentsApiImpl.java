package com.samarthsoft.prabandhak.api.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samarthsoft.prabandhak.api.StudentsApi;
import com.samarthsoft.prabandhak.entities.ApplicationConstants;
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.entities.Student;

public class StudentsApiImpl  extends ApiBaseImpl implements StudentsApi {
	private Logger LOG = LoggerFactory.getLogger(GenericApiImpl.class);

	public StudentsApiImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Boolean createAlumniAndMarkStudentAsAlumni(List<Object> students,
			List<Object> alumnis) {
		Boolean result = false;
		Session session = null;
		try{
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int count = 1;
		for (Object object : alumnis) {
			session.saveOrUpdate("studentGuid",object);
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

		for (Object object : students) {
			session.saveOrUpdate("guid",object);
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
		} 
		finally {
			if (session != null)
				session.close();
		}
		return result;
	}

	public PaginationObject getStudentsByFilter(Map<String, Object> filters) {
		return null;
	}
}