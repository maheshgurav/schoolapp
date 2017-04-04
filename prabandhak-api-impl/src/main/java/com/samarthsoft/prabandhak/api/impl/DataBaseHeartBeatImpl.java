package com.samarthsoft.prabandhak.api.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samarthsoft.prabandhak.api.DataBaseHeartBeatApi;

public class DataBaseHeartBeatImpl extends ApiBaseImpl implements DataBaseHeartBeatApi{

	private Logger LOG = LoggerFactory.getLogger(GenericApiImpl.class);

	public DataBaseHeartBeatImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public void beatDatabase() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.createSQLQuery("SELECT 1").list();
		} catch (Exception ex) {
			LOG.error("", ex);
		} finally {
			if (session != null)
				session.close();
		}
	}
}