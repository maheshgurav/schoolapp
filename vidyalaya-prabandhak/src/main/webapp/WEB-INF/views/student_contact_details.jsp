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
	<title>School Management System - Student Contact Details</title>

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
            Student Contact Details
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="#"><i class="fa fa-dashboard"></i> Students</a></li>
            <li class="active">Add/Edit Students Contact Details</li>
          </ol>
        </section>

<section class="content">


<form:form commandName="student" method="POST">
	<form:hidden path="studentId"/>
			
	<div class="row">
		<div class="col-md-12">

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Permanent Address</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    
				<div class="col-md-4">								  
				  	<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Permanent.Address"/>">
					<label class="control-label" for="focusedInput"><spring:message code="Label.Permanent.Address"/></label>
						<div class="controls">
						  <form:textarea class="form-control" id="permanentAddress_address" path="permanentAddress.address"/>
						</div>
				  	</div>
				 </div>
				
				<div class="col-md-4">
				  <div class="form-group">
					<label class="control-label" for="focusedInput"><spring:message code="Label.State"/></label>
					<div class="controls">
					  <form:select path="permanentAddress.state" data-rel="chosen" style="width:100%">
						<core:forEach var="stateInfo" items="${states}">
							<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
						</core:forEach>
					  </form:select>
					</div>
				  </div>
				  </div>

				<div class="col-md-4">
				  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Pin.Code"/>">
					<label class="control-label" for="focusedInput"><spring:message code="Label.PinCode"/></label>
					<div class="controls">
					  <form:input class="form-control" id="permanentAddress_pinCodeNumber" path="permanentAddress.pinCodeNumber"/>
					</div>
				  </div>
				  </div>
				  
				</div>			    
			</div>
		</div>
	</div>
	</div>

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Current Address</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    
				<div class="col-md-4">								  
				  	<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Current.Address.Generic"/>">
					<label class="control-label" for="focusedInput"><spring:message code="Label.Current.Address"/></label>
						<div class="controls">
						  <form:textarea class="form-control" id="currentAddress_address" path="currentAddress.address"/>
						</div>
				  	</div>
				 </div>
				
				<div class="col-md-4">
				  <div class="form-group">
					<label class="control-label" for="focusedInput"><spring:message code="Label.State"/></label>
					<div class="controls">
					  <form:select path="currentAddress.state" data-rel="chosen" style="width:100%">
						<core:forEach var="stateInfo" items="${states}">
							<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
						</core:forEach>
					  </form:select>
					</div>
				  </div>
				  </div>

				<div class="col-md-4">
				  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Current.Address.Pin.Code.Generic"/>">
					<label class="control-label" for="focusedInput"><spring:message code="Label.PinCode"/></label>
					<div class="controls">
					  <form:input class="form-control" id="currentAddress_pinCodeNumber" path="currentAddress.pinCodeNumber"/>
					</div>
				  </div>
				  </div>
				  
				</div>			    
			</div>
		</div>
	
	<div class="row">
		<div class="col-md-12">

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Other Information</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
				    
				    <div class="col-md-6">
					  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Email.Id"/>">
						<label class="control-label" for="focusedInput"><spring:message code="Label.EmailId"/></label>
						<div class="controls">
						  <form:input class="form-control" id="emailId" path="emailId"/>
						</div>
					  </div>
				  	</div>

				    <div class="col-md-6">
					  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Phone.Number"/>">
						<label class="control-label" for="focusedInput"><spring:message code="Label.Phone.Number"/></label>
						<div class="controls">
						  <form:input class="form-control" id="phoneNumber" path="phoneNumber"/>
						</div>
					  </div>
				  	</div>
					
					</div>			    
				</div>
			</div>
		</div>
	</div>

    <div class="box box-default">
            <div class="box-body">
	    		<div class="form-actions">
					<button type="submit" class="btn btn-default">Save changes</button>
					<a class="btn" href="students.htm">Cancel</a>
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
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>