package com.samarthsoft.prabandhak.api.impl;

import org.hibernate.SessionFactory;

public class ApiBaseImpl {
	protected SessionFactory sessionFactory=null;
	
	public ApiBaseImpl( SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
}
