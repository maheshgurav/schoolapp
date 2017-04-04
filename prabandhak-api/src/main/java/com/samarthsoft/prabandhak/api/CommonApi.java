package com.samarthsoft.prabandhak.api;

import com.samarthsoft.prabandhak.entities.LoginInformation;

public interface CommonApi {
	Boolean insertObjectWithLoginInformation(Object parentObject,LoginInformation loginInformation);
	
	Boolean updateObjectWithLoginInformation(Object parentObject, LoginInformation loginInformation);
}