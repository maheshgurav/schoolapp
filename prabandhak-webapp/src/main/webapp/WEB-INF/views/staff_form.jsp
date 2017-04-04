<!DOCTYPE html>
<%@ page session="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>School Management System - Teacher add/edit</title>
	<script>
	 $(function() {
	   $( "#birthDate,#admissionDate" ).datepicker({
	     	changeMonth: true,
	     	changeYear: true
	   });
	 });
	</script>
	
		<!-- topbar starts -->
			<%@ include file="/WEB-INF/common_views/header.jsp" %>
		<!-- topbar ends -->
		
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<%@ include file="/WEB-INF/common_views/menu.jsp" %>			
			<!-- left menu ends -->
			<div id="content" class="col-lg-10 col-sm-10">
			
			<!-- breadcrum starts -->
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="#">Home</a> <span class="divider"></span>
					</li>
					<li>
						<a href="#">Teacher add/edit</a>
					</li>
				</ul>
			</div>
			<!-- breadcrum ends -->			
<form:form commandName="staff" method="POST">
<form:hidden path="supportStaff.guid"/>
<form:hidden path="supportStaff.loginInformation.userRole"/>

<div class="row">
	<div class="box col-md-6">
		<div class="box-inner">
			<div class="box-content">
				<fieldset class="form-horizontal">
 <legend>Basic information</legend>
 
	<c:choose>
			  	<c:when test="${not empty error_name_firstName}">
			  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.First.Name"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Staff.First.Name"/>">
			  	</c:otherwise>
			  </c:choose>
			  <label class="control-label" for="focusedInput"><spring:message code="Label.First.Name"/></label>
				  <form:input class="form-control" id="name_firstName" path="name.firstName"/>
			  </div>
			
				  <c:choose>
			  	<c:when test="${not empty error_name_middleName}">
			  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.Middle.Name"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Staff.Middle.Name"/>">
			  	</c:otherwise>
			  </c:choose>
			  <label class="control-label" for="focusedInput"><spring:message code="Label.Midddle.Name"/></label>
				  <form:input class="form-control" id="name_midddleName" path="name.middleName"/>
			  </div>
			  
			  
     <c:choose>
			  	<c:when test="${not empty error_name_lastName}">
			  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.Last.Name"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Staff.Last.Name"/>">
			  	</c:otherwise>
			  </c:choose>
			  <label class="control-label" for="focusedInput"><spring:message code="Label.Last.Name"/></label>
				  <form:input class="form-control" id="name_lastName" path="name.lastName"/>
			  </div>

				  
				<div class="control-group">
				<label class="control-label">Gender</label>
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
				  
				  
				 <div class="control-group">
					<label class="control-label" for="focusedInput">Adhar card</label>
					<div class="controls">
						  <form:input class="form-control" id="teacher_aadharCardNumber" path="supportStaff.aadharCardNumber"/>
					</div>
				  </div>
				  
				  <c:choose>
				  	<c:when test="${not empty error_birthDate}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_birthDate}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Birth.Date"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Birth.Date"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="birthDate" path="birthDate"/>
					</div>
				  </div> 
				  
				  <c:choose>
				  	<c:when test="${not empty error_joiningDate}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_joiningDate}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Joining.Date"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Joining.Date"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="joiningDate" path="joiningDate"/>
					</div>
				  </div>
				 
				 	<legend>Banking details</legend>
				 	<div class="control-group">
						<label class="control-label" for="focusedInput">IFSC code</label>
						<div class="controls">
							<form:input class="form-control" id="bankAccountDetails_IFSCCode" path="bankAccountDetails.IFSCCode"/>
						</div>
				  	</div>
				  
					<div class="control-group">
						<label class="control-label" for="focusedInput">Account number</label>
						<div class="controls">
							<form:input class="form-control" id="bankAccountDetails_accountNumber" path="bankAccountDetails.accountNumber"/>
						</div>
					</div>
				  
					<div class="control-group">
						<label class="control-label" for="focusedInput">Bank name</label>
						<div class="controls">
							<form:input class="form-control" id="bankAccountDetails_nameOfBank" path="bankAccountDetails.nameOfBank"/>
						</div>
					</div>
				  
				  <legend>Contact and qualification</legend>
				  
				  <c:choose>
				  	<c:when test="${not empty error_supportStaff_contactNumber}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_supportStaff_contactNumber}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Contact.Number"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Contact.Number"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="supportStaff_contactNumber" path="supportStaff.contactNumber"/>
					</div>
				  </div> 
  
				  <div class="control-group">
					<label class="control-label" for="focusedInput">Email ID</label>
					<div class="controls">
					  <form:input class="form-control" id="supportStaff_emailId" path="supportStaff.emailId"/>
					</div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="focusedInput">Qualification</label>
					<div class="controls">
					  <form:input class="form-control" id="supportStaff_qualification" path="supportStaff.qualification"/>
					</div>
				  </div>

				</fieldset>
			</div>
	</div>
</div>

<div class="box col-md-6">
	<div class="box-inner">
		<div class="box-content">
			<fieldset class="form-horizontal">
<legend>Designation</legend>
					<div class="control-group">
					<label class="control-label" for="focusedInput">Designation</label>
					<div class="controls">
					  	<form:select path="supportStaff.designation" data-rel="chosen">
							<core:forEach var="designation" items="${designations}">
								<form:option value="${designation.name}">${designation.name}</form:option>
							</core:forEach>
						</form:select>
					</div>
				
				<legend>Login information</legend>
				  <div class="control-group">
					<label class="control-label" for="focusedInput">Login name</label>
					<div class="controls">
						  <form:input class="form-control" id="supportStaff_loginInformation_userName" path="supportStaff.loginInformation.userName"/>
					</div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="focusedInput">Password</label>
					<div class="controls">
					  <form:input class="form-control" id="supportStaff_loginInformation_password" path="supportStaff.loginInformation.password"/>
					</div>
				  </div>
				  
				  <%@ include file="/WEB-INF/common_views/address.jsp" %>					
				  
			</fieldset>
		</div>
	</div>
</div>

<div class="box col-md-12">
    <div class="box-inner">
        <div class="box-content">
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">Save changes</button>
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
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>