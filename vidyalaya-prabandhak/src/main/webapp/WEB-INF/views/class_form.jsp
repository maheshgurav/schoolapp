<!DOCTYPE html>
<%@ page session="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>School Management System - Class</title>

		<!-- topbar starts -->
			<%@ include file="/WEB-INF/common_views/header.jsp" %>
		<!-- topbar ends -->
			<!-- left menu starts -->
			<%@ include file="/WEB-INF/common_views/menu.jsp" %>			
			<!-- left menu ends -->


<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
    <h1>
    Class Details
    </h1>
    <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
    <li><a href="#"><i class="fa fa-dashboard"></i> Settings</a></li>
    <li class="active">Add/Edit Class</li>
    </ol>
    </section>

<section class="content">
	<input type="hidden" id="subjects" value='${subjects}'/>
	<input type="hidden" id="teachers" value='${teachers}'/>
	<form:form commandName="classinfo" method="POST" name="classinfo" id="classinfo_form">
	<form:hidden path="classInfo.guid"/>
	<form:hidden id="subjectsAndTeachers" path="classInfo.subjectsAndTeachers"/>
	
    	<div class="row">
		<div class="col-md-12">

		 	<div class="box box-primary">
	            <div class="box-body">
					<div class="form-group">
						<div class="col-md-3">
							<c:choose>
							  	<c:when test="${not empty error_classinfo_standardOrClass}">
							  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="${error_classinfo_standardOrClass}"> 
							  	</c:when>
							  	<c:otherwise>
							  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="${error_classinfo_standardOrClass}">
							  	</c:otherwise>
						  	</c:choose>
							
								<label class="control-label" for="focusedInput"><spring:message code="Label.Standard"/></label>
						  		<div class="control">
						  			<form:input class="form-control" id="classinfo_standardOrClass" path="classInfo.standardOrClass"/>
						  		</div>	
				  			</div>
						</div>

						<div class="col-md-3">
							<c:choose>
							  	<c:when test="${not empty error_classinfo_division}">
							  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="${error_classinfo_division}"> 
							  	</c:when>
							  	<c:otherwise>
							  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="${error_classinfo_division}">
							  	</c:otherwise>
						  	</c:choose>
								<label class="control-label" for="focusedInput"><spring:message code="Label.Division"/></label>
						  		<div class="control">
						  			<form:input class="form-control" id="classinfo_division" path="classInfo.division"/>
						  		</div>	
				  			</div>
						</div>

						<div class="col-md-3">
						  	<div class="control-group">
								<label class="control-label" for="focusedInput"><spring:message code="Label.Type.Of.School"/></label>
						  		<form:select style="width:150px;" id="classinfo_schoolTypeGuid" path="classInfo.schoolTypeGuid" data-rel="chosen">
							  			<core:forEach var="schoolType" items="${schoolTypes}">
							  				<form:option value="${schoolType.name}" id="${schoolType.guid}">${schoolType.name}</form:option>
							  			</core:forEach>
								</form:select>
				  			</div>
				  		</div>

						<div class="col-md-3">
						  	<div class="control-group">
								<label class="control-label" for="focusedInput">Class Teacher</label>
						  		<form:select style="width:150px;" id="classinfo_classTeacherGuid" path="classInfo.classTeacherGuid" data-rel="chosen">
							  			<form:option value="-" id="-">No Teacher Selected</form:option>
							  			<core:forEach var="teacher" items="${teachers_list}">
							  				<core:choose>
							  				<core:when test="${teacher.guid ==  classInfo.classTeacherGuid}">
							  					<form:option value="${teacher.guid}" id="${teacher.guid}" selected="selected">${teacher.name.toString()}</form:option>
							  				</core:when>
							  				<core:otherwise>
							  					<form:option value="${teacher.guid}" id="${teacher.guid}">${teacher.name.toString()}</form:option>
							  				</core:otherwise>	
							  				</core:choose>
							  			</core:forEach>
								</form:select>
				  			</div>
				  		</div>

						</div>
					</div></div>
					 	<div class="box box-primary">
	            <div class="box-body">
					<div class="form-group">
			    	<table class="table table-hover" id="tbl_subject_teachers_details">
				<thead>
				<tr>
					<th>Subject Name</th>
					<th>Subject Teacher Name</th>
				</tr>
				</thead>
				<tbody>
					<core:if test="${classinfo.subjectTeacherMapping.size() > 0}">
						<core:forEach var="subject_teacher" items="${classinfo.subjectTeacherMapping}" varStatus="count">
							<tr>
								<td class="center">
								<select id="subject_${count.index + 1}" data-rel="chosen">
							  			<option value="-" id="-">No Teacher Selected</option>
							  			<core:forEach var="subject" items="${subjects_list}">
							  				<core:choose>
							  				<core:when test="${subject.guid == subject_teacher.subject.guid}">
							  					<option value="${subject.guid}" id="${subject.guid}" selected="selected">${subject.name}</option>
							  				</core:when>
							  				<core:otherwise>
							  					<option value="${subject.guid}" id="${subject.guid}">${subject.name}</option>
							  				</core:otherwise>	
							  				</core:choose>
							  			</core:forEach>
								</select>
								
								</td>
								<td class="center">
								<select id="teacher_${count.index + 1}" data-rel="chosen">
							  			<option value="-" id="-">No Teacher Selected</option>
							  			<core:forEach var="teacher" items="${teachers_list}">
							  				<core:choose>
							  				<core:when test="${teacher.guid == subject_teacher.teacher.guid}">
							  					<option value="${teacher.guid}" id="${teacher.guid}" selected="selected">${teacher.name.toString()}</option>
							  				</core:when>
							  				<core:otherwise>
							  					<option value="${teacher.guid}" id="${teacher.guid}">${teacher.name.toString()}</option>
							  				</core:otherwise>	
							  				</core:choose>
							  			</core:forEach>
								</select>
								</td>
							</tr>
						</core:forEach>
					</core:if>
				</tbody>
			</table>
			
			<a href="javascript:addSubject();">Add New Subject</a>
			    		
			</div>
			</div>
			</div>
				</div>
			
						    
		</div>
    
  <div class="box box-default">
    <div class="box-body">
	  		<div class="form-actions">
					<button type="submit" class="btn btn-default" onclick="javascript:submitClassInfoSettingsFormObject()">Save changes</button>
					<a class="btn" href="#">Cancel</a>
				</div>
			</div>	
		</div>
	</div>	
</div><!--/span-->
<!-- content ends -->
</form:form>
</div><!--/#content.span10-->
</div><!--/fluid-row-->

<script src='js/common.js'></script>
<script src='js/settings.js'></script>
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>