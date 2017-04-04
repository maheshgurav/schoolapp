package com.samarthsoft.prabandhak.api.impl;

import org.hibernate.SessionFactory;

import com.samarthsoft.prabandhak.api.TeachersApi;

public class TeacherApiImpl extends ApiBaseImpl implements TeachersApi{

	public TeacherApiImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}