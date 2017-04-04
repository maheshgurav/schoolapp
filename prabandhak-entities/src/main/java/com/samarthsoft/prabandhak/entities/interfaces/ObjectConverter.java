package com.samarthsoft.prabandhak.entities.interfaces;

public interface ObjectConverter {
	Object convertOriginalObjectToFromObject(Object originalObject);
	
	Object convertFormObjectToOriginalObject(Object formObject);
}
