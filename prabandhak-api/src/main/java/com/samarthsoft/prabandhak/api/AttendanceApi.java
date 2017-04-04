package com.samarthsoft.prabandhak.api;

import java.util.List;

import com.samarthsoft.prabandhak.entities.Attendance;

public interface AttendanceApi {
	Boolean fillAttendance(List<Attendance> recordsToDelete, List<Attendance> recordsToInsert);
}