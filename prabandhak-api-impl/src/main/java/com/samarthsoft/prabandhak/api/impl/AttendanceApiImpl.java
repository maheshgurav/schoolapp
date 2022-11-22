package com.samarthsoft.prabandhak.api.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samarthsoft.prabandhak.api.AttendanceApi;
import com.samarthsoft.prabandhak.entities.Attendance;

public class AttendanceApiImpl extends ApiBaseImpl implements AttendanceApi {
	private final Logger LOG = LoggerFactory.getLogger(AttendanceApiImpl.class);

	public AttendanceApiImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Boolean fillAttendance(List<Attendance> recordsToDelete, List<Attendance> recordsToInsert) {
		Boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			for(Attendance attendance : recordsToDelete){
				session.delete(attendance);
			}
			for(Attendance attendance : recordsToInsert){
				session.persist(attendance);
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
}