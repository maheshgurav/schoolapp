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
	<title>School Management System - Student Parent Details</title>

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
            Student Parent Details
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="#"><i class="fa fa-dashboard"></i> Students</a></li>
            <li class="active">Add/Edit Students Parent Details</li>
          </ol>
        </section>

<section class="content">

<form:form commandName="parent" method="POST">
	<form:hidden path="studentGuid"/>
	<div class="row">
		<div class="col-md-12">

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Details Of Father</h3>
            </div>
            <div class="box-body">
            	
	            <div class="row">
					
			<div class="col-md-3">
			  	<c:choose>
				  	<c:when test="${not empty error_nameOfFather_lastName}">
				  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfFather_lastName}"/>"> 
				  	</c:when>
				  	<c:otherwise>
				  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
				  	</c:otherwise>
			  	</c:choose>
				<label class="control-label" for="focusedInput"><spring:message code="Label.Last.Name"/></label>
				<div class="controls">
					  <form:input class="form-control" id="nameOfFather_lastName" path="nameOfFather.lastName"/>
				</div>
				  </div>
			</div>

		        <div class="col-md-3">
					<c:choose>
					  	<c:when test="${not empty error_nameOfFather_firstName}">
					  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfFather_firstName}"/>">
					  	</c:when>
					  	<c:otherwise>
					  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.First.Name"/>">
					  	</c:otherwise>
				  	</c:choose>
				  	<label class="control-label" for="focusedInput"><spring:message code="Label.First.Name"/></label>
				<div class="controls">
					  <form:input class="form-control" id="nameOfFather_firstName" path="nameOfFather.firstName"/>
				</div>	  
				  	</div>
				</div>

		       <div class="col-md-3">
              		<c:choose>
			  			<c:when test="${not empty error_nameOfFather_middleName}">
			  				<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfFather_middleName}"/>">
			  			</c:when>
			  			<c:otherwise>
			  				<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Middle.Name"/>">
			  			</c:otherwise>
			  		</c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Middle.Name"/></label>
				    <div class="controls">
				    	<form:input class="form-control" id="nameOfFather_middleName" path="nameOfFather.middleName"/>
			  		</div>
			  		</div>
				</div>
				
				<div class="col-md-3">
				  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Pin.Code"/>">
					<label class="control-label" for="focusedInput">Date Of Birth</label>
					<div class="controls">
					  <form:input class="form-control" id="dateOfBirthOfFather" path="dateOfBirthOfFather"/>
					</div>
				  </div>
				</div>
				
			</div>
		</div>

	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Details Of Mother</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    
					
			<div class="col-md-3">
			  	<c:choose>
				  	<c:when test="${not empty error_nameOfMother_lastName}">
				  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfMother_lastName}"/>"> 
				  	</c:when>
				  	<c:otherwise>
				  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
				  	</c:otherwise>
			  	</c:choose>
				<label class="control-label" for="focusedInput"><spring:message code="Label.Last.Name"/></label>
				<div class="controls">
					  <form:input class="form-control" id="nameOfMother_lastName" path="nameOfMother.lastName"/>
				</div>
				  </div>
			</div>

		        <div class="col-md-3">
					<c:choose>
					  	<c:when test="${not empty error_nameOfMother_firstName}">
					  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfMother_firstName}"/>">
					  	</c:when>
					  	<c:otherwise>
					  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.First.Name"/>">
					  	</c:otherwise>
				  	</c:choose>
				  	<label class="control-label" for="focusedInput"><spring:message code="Label.First.Name"/></label>
				<div class="controls">
					  <form:input class="form-control" id="nameOfMother_firstName" path="nameOfMother.firstName"/>
				</div>	  
				  	</div>
				</div>

		       <div class="col-md-3">
              		<c:choose>
			  			<c:when test="${not empty error_nameOfMother_middleName}">
			  				<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfMother_middleName}"/>">
			  			</c:when>
			  			<c:otherwise>
			  				<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Middle.Name"/>">
			  			</c:otherwise>
			  		</c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Middle.Name"/></label>
				    <div class="controls">
				    	<form:input class="form-control" id="nameOfMother_middleName" path="nameOfMother.middleName"/>
			  		</div>
			  		</div>
				</div>
				<div class="col-md-3">
				  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Pin.Code"/>">
					<label class="control-label" for="focusedInput">Date Of Birth</label>
					<div class="controls">
					  <form:input class="form-control" id="dateOfBirthOfMother" path="dateOfBirthOfMother"/>
					</div>
				  </div>
				</div>
				
			</div>
</div>
</div></div>

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Permanent Address</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    

				<div class="col-md-4">								  
				  	<c:choose>
				  	<c:when test="${not empty error_permanentAddress_address}">
				  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_permanentAddress_address}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Birth.Place"/>">
				  	</c:otherwise>
					</c:choose>
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

				<div class="col-md-2">
				  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Pin.Code"/>">
					<label class="control-label" for="focusedInput"><spring:message code="Label.PinCode"/></label>
					<div class="controls">
					  <form:input class="form-control" id="permanentAddress_pinCodeNumber" path="permanentAddress.pinCodeNumber"/>
					</div>
				  </div>
				 </div>

				<div class="col-md-2">
				  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Pin.Code"/>">
					<label class="control-label" for="focusedInput">Phone Number</label>
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

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Income Details</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    
				<div class="col-md-2">
				  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Pin.Code"/>">
					<label class="control-label" for="focusedInput">Occupation</label>
					<div class="controls">
					  <form:input class="form-control" id="occupation" path="occupation"/>
					</div>
				  </div>
				  </div>
				  				
				  <div class="col-md-2">
				  <div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Pin.Code"/>">
					<label class="control-label" for="focusedInput">Yearly Income</label>
					<div class="controls">
					  <form:input class="form-control" id="yearlyIncome" path="yearlyIncome"/>
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
	
<script>
  $(function() {
    $( "#dateOfBirthOfFather , #dateOfBirthOfMother" ).datepicker();
  });
</script>
	
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>