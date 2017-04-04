package com.samarthsoft.prabandhak.api.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samarthsoft.prabandhak.api.CommonApi;
import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.entities.SupportStaff;
import com.samarthsoft.prabandhak.entities.Teacher;

public class CommonApiImpl extends ApiBaseImpl implements CommonApi{
	private final Logger LOG = LoggerFactory.getLogger(CommonApiImpl.class);

	public CommonApiImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Boolean insertObjectWithLoginInformation(
			Object parentObject, LoginInformation loginInformation) {
		Boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(parentObject);
			if(loginInformation!=null){
				injectGuidInLoginInformationObject(parentObject, loginInformation);
				session.save(loginInformation);
			}
			transaction.commit();
			result = true;
		} catch (Exception ex) {
			transaction.rollback();
			LOG.error("", ex);
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}
	
	public Boolean updateObjectWithLoginInformation(
			Object parentObject, LoginInformation loginInformation) {
		Boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(parentObject);
			if(loginInformation!=null){
				injectGuidInLoginInformationObject(parentObject, loginInformation);
				session.saveOrUpdate(loginInformation);
			}
			transaction.commit();
			result = true;
		} catch (Exception ex) {
			transaction.rollback();
			LOG.error("", ex);
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}
	
	private void injectGuidInLoginInformationObject(Object parentObject,LoginInformation loginInformation){
		if(parentObject instanceof Teacher)
			loginInformation.setGuid(((Teacher)parentObject).getGuid());
		if(parentObject instanceof SupportStaff)
			loginInformation.setGuid(((SupportStaff)parentObject).getGuid());
	}
}