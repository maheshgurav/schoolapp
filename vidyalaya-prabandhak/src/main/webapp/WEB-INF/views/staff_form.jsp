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
	<title>School Management System - Supporting Staff Details</title>

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
    Supporting Staff Details
    </h1>
    <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
    <li><a href="#"><i class="fa fa-dashboard"></i> Staff</a></li>
    <li class="active">Supporting Staff Details</li>
    </ol>
    </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

<section class="content">


<form:form commandName="staff" method="POST">
<form:hidden path="supportStaff.guid"/>
<form:hidden path="supportStaff.loginInformation.userRole"/>


	<div class="row">
		<div class="col-md-12">
	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Name Of Staff Member</h3>
            </div>
            <div class="box-body">
	            <div class="row">
					
			<div class="col-md-4">
     		<c:choose>
			  	<c:when test="${not empty error_name_firstName}">
			  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.First.Name"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Teacher.First.Name"/>">
			  	</c:otherwise>
			</c:choose>
			<label class="control-label" for="focusedInput"><spring:message code="Label.First.Name"/></label>
			<div class="controls">
				<form:input class="form-control" id="name_firstName" path="name.firstName"/>
			</div>
			</div>	  
			</div>

			<div class="col-md-4">
     		<c:choose>
			  	<c:when test="${not empty error_name_middleName}">
			  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.Middle.Name"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Teacher.Middle.Name"/>">
			  	</c:otherwise>
			  </c:choose>
			  <label class="control-label" for="focusedInput"><spring:message code="Label.Midddle.Name"/></label>
			<div class="controls">
				  <form:input class="form-control" id="name_midddleName" path="name.middleName"/>
			</div>
			  </div>
			</div>  
			
			<div class="col-md-4">
     		  
     		<c:choose>
			  	<c:when test="${not empty error_name_lastName}">
			  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.Last.Name"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Teacher.Last.Name"/>">
			  	</c:otherwise>
			</c:choose>
			  <label class="control-label" for="focusedInput"><spring:message code="Label.Last.Name"/></label>
				<div class="controls">
					  <form:input class="form-control" id="name_lastName" path="name.lastName"/>
				</div>
			  </div>
			  </div>
			</div>
		</div>			    
	</div>
	</div>
	</div>

<div class="row">
	
		<div class="col-md-6">

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Designation</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			<div class="col-md-4">
				  <div class="form-group">
					<label class="control-label" for="focusedInput"><spring:message code="Label.Designation"/></label>
					<div class="controls">
						<form:select path="supportStaff.designation" data-rel="chosen" style="width:75%;">
				  			<core:forEach var="designation" items="${designations}">
				  				<form:option value="${designation.name}">${designation.name}</form:option>
				  			</core:forEach>
						</form:select>
					</div>
				  </div>
				  </div>

			</div>
				  </div>
			</div>
				  </div>
	
		<div class="col-md-6">
	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message code="Label.Login.Information"/></h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    

				<div class="col-md-6">
					<c:choose>
						<c:when test="${not empty error_teacher_loginInformation_userName}">
							<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.First.Name"/>">
						</c:when>
						<c:otherwise>
							<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Teacher.First.Name"/>">
						</c:otherwise>
					</c:choose>
						<label class="control-label" for="focusedInput"><spring:message code="Label.Login.Name"/></label>
						<form:input class="form-control" id="teacher_loginInformation_userName" path="supportStaff.loginInformation.userName"/>
				</div>
				</div>
				  
				  <div class="col-md-6">
				<c:choose>
					<c:when test="${not empty error_teacher_loginInformation_password}">
						<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.First.Name"/>">
					</c:when>
					<c:otherwise>
						<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Teacher.First.Name"/>">
					</c:otherwise>
				</c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Password"/></label>
					<form:password class="form-control" id="teacher_loginInformation_password" path="supportStaff.loginInformation.password"/>
				</div>
				</div>

				</div>
				</div>
				</div>
				</div></div>
				<div class="row">
	
		<div class="col-md-12">
				
			
  <div class="box box-default">
    <div class="box-body">
	  		<div class="form-actions">
				<button type="submit" class="btn btn-default">Save changes</button>
				<button class="btn">Cancel</button>
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
	<script src='js/student.js'></script>
		<script>
	  $(function() {
	    $( "#birthDate , #joiningDate" ).datepicker();
	  });
  </script>
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>