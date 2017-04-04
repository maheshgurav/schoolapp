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
	<title>School Management System - My Profile</title>

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
					
			<div class="col-md-3">
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

			<div class="col-md-3">
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
			
			<div class="col-md-3">
     		  
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
			  
			<div class="col-md-3">
     			<div class="form-group">
					<label class="control-label"><spring:message code="Label.Gender"/></label>
					<div class="controls">
						<div class="radio">
							<label>	
								<form:radiobutton path="supportStaff.gender" value="Male"/>Male
							</label>
							<label>	
								<form:radiobutton path="supportStaff.gender" value="Female" />Female
							</label>
						  </div>
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
              <h3 class="box-title">Dates</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    
					
			<div class="col-md-4">
				  <div class="form-group">
				  <c:choose>
					  	<c:when test="${not empty error_birthDate}">
					  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_birthDate}"/>">
					  	</c:when>
					  	<c:otherwise>
					  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Birth.Date"/>">
					  	</c:otherwise>
				  </c:choose>
			
				<label class="control-label" for="focusedInput"><spring:message code="Label.Birth.Date"/></label>
				<div class="controls">
				  <form:input class="form-control"  id="birthDate" path="birthDate"/>
				</div>
			</div> 
			</div>
			</div>
			
			<div class="col-md-4">
				<c:choose>
					<c:when test="${not empty error_joiningDate}">
						<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_joiningDate}"/>">
					</c:when>
					<c:otherwise>
						<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Joining.Date"/>">
					</c:otherwise>
				</c:choose>
				<label class="control-label" for="focusedInput"><spring:message code="Label.Joining.Date"/></label>
				<div class="controls">
					<form:input class="form-control"  id="joiningDate" path="joiningDate"/>
				</div>
			</div> 
			</div>
			
			<div class="col-md-4">
				  <div class="form-group">
					<label class="control-label" for="focusedInput"><spring:message code="Label.Designation"/></label>
					<div class="controls">
						<form:select path="supportStaff.designation" data-rel="chosen" style="width:75%;" disabled="disabled">
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

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message code="Label.Banking.Details"/></h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    

			<div class="col-md-3">
				  <div class="form-group">
					<label class="control-label" for="focusedInput"><spring:message code="Label.AadharCard.Number"/></label>
					<div class="controls">
						  <form:input class="form-control" id="teacher_aadharCardNumber" path="supportStaff.aadharCardNumber"/>
					</div>
				  </div>
			</div>
					
			<div class="col-md-3">
				  	<div class="form-group">
						<label class="control-label" for="focusedInput"><spring:message code="Label.IFSC.Code"/></label>
						<div class="controls">
							<form:input class="form-control" id="bankAccountDetails_IFSCCode" path="bankAccountDetails.IFSCCode"/>
						</div>
				  	</div>
			</div>

			<div class="col-md-3">
					<div class="form-group">
						<label class="control-label" for="focusedInput"><spring:message code="Label.Account.Number"/></label>
						<div class="controls">
							<form:input class="form-control" id="bankAccountDetails_accountNumber" path="bankAccountDetails.accountNumber"/>
						</div>
					</div>
			</div>
			
			<div class="col-md-3">
					<div class="form-group">
						<label class="control-label" for="focusedInput"><spring:message code="Label.Bank.Name"/></label>
						<div class="controls">
							<form:input class="form-control" id="bankAccountDetails_nameOfBank" path="bankAccountDetails.nameOfBank"/>
						</div>
					</div>
			</div>
			</div>
			</div>
			</div>

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message code="Label.Contact.Qualification"/></h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    
				  
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label" for="focusedInput"><spring:message code="Label.EmailId"/></label>
						<div class="controls">
						  <form:input class="form-control" id="teacher_emailId" path="supportStaff.emailId"/>
						</div>
					</div>
				</div>
				  	  

				<div class="col-md-4">
				  <c:choose>
				  	<c:when test="${not empty error_teacher_contactNumber}">
				  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_teacher_contactNumber}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Contact.Number"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Contact.Number"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="teacher_contactNumber" path="supportStaff.contactNumber"/>
					</div>
				  </div>
				 </div> 
				 
				  
				<div class="col-md-4">
				  <div class="form-group">
					<label class="control-label" for="focusedInput"><spring:message code="Label.Qualification"/></label>
					<div class="controls">
					  <form:input class="form-control" id="teacher_qualification" path="supportStaff.qualification"/>
					</div>
				  </div>
				</div>
			</div>
	</div>
</div>

<%-- 	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message code="Label.Login.Information"/></h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    

				<div class="col-md-3">
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
				  
				  <div class="col-md-3">
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
 --%>
					 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Permanent address</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    

				<div class="col-md-4">
				  <div class="form-group">
					<label class="control-label" for="focusedInput">Permanent Address</label>
					<div class="controls">
					  	<form:input class="form-control" id="teacher_permanentAddress_address" path="supportStaff.permanentAddress.address"/>
					</div>
				  </div>
			  	</div>

				<div class="col-md-4">
				  <div class="form-group">
					<label class="control-label" for="focusedInput">State</label>
					<div class="controls">
					  	<form:select path="supportStaff.permanentAddress.state" data-rel="chosen">
							<core:forEach var="stateInfo" items="${states}">
								<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
							</core:forEach>
						</form:select>
					</div>
			  	</div>
			  	</div>
			  
			  <div class="col-md-4">
				<div class="control-group">
					<label class="control-label" for="focusedInput">Pin code</label>
					<div class="controls">
					  <form:input class="form-control" id="teacher_permanentAddress_pinCodeNumber" path="supportStaff.permanentAddress.pinCodeNumber"/>
					</div>
			  	</div>
			  </div>
			  </div></div></div>

		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Current Address</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    

				<div class="col-md-4">
					<div class="form-group">
					<label class="control-label" for="focusedInput">Current Address</label>
					<div class="controls">
						<form:input class="form-control" id="teacher_currentAddress_address" path="supportStaff.currentAddress.address"></form:input>
					</div>
					</div>
				</div>

					<div class="col-md-4">
						<div class="form-group">
				<label class="control-label" for="focusedInput">State</label>
				<div class="controls">
				  	<form:select path="supportStaff.currentAddress.state" data-rel="chosen">
						<core:forEach var="stateInfo" items="${states}">
							<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
						</core:forEach>
					</form:select>
				</div>
			  </div>
			  </div>
					<div class="col-md-4">
						<div class="form-group">
			<label class="control-label" for="focusedInput">Pin code</label>
			<div class="controls">
			  <form:input class="form-control" id="teacher_currentAddress_pinCodeNumber" path="supportStaff.currentAddress.pinCodeNumber"/>
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