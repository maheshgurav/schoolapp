package com.samarthsoft.prabandhak.api;

import java.util.List;
import java.util.Map;

import com.samarthsoft.prabandhak.entities.PaginationObject;

public interface StudentsApi {
	Boolean createAlumniAndMarkStudentAsAlumni(List<Object> students,List<Object> alumnis);
	
	PaginationObject getStudentsByFilter(Map<String, Object> filters);
}
