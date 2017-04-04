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
	<title>School Management System - Student add/edit</title>
	
		<!-- topbar starts -->
			<%@ include file="/WEB-INF/common_views/header.jsp" %>
		<!-- topbar ends -->
		
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<%@ include file="/WEB-INF/common_views/menu.jsp" %>			
			<!-- left menu ends -->
			<div id="content" class="col-lg-10 col-sm-10">
			
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="dashboard.htm">Home</a> <span class="divider"></span>
					</li>
					<li>
						<a href="students.htm">Students</a> <span class="divider"></span>
					</li>
					<li>
						<b>Student Details</b>
					</li>
				</ul>
			</div>

<form:form commandName="student" method="POST">
	<form:hidden path="student.guid"/>
	
    <div class="row">
        <div class="box col-md-6">
            <div class="box-inner">
                <div class="box-content">
				<fieldset class="form-horizontal">
				  <legend><spring:message code="Label.Student.Name"/></legend>
			  
			  <c:choose>
			  	<c:when test="${not empty error_nameOfStudent_firstName}">
			  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfStudent_firstName}"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.First.Name"/>">
			  	</c:otherwise>
			  </c:choose>
			  <label class="control-label" for="focusedInput"><spring:message code="Label.First.Name"/></label>
				  <form:input class="form-control" id="nameOfStudent_firstName" path="nameOfStudent.firstName"/>
			  </div>
				  
			
              <c:choose>
			  	<c:when test="${not empty error_nameOfStudent_middleName}">
			  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfStudent_middleName}"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Middle.Name"/>">
			  	</c:otherwise>
			  </c:choose>
				<label class="control-label" for="focusedInput"><spring:message code="Label.Middle.Name"/></label>

				  <form:input class="form-control" id="nameOfStudent_middleName" path="nameOfStudent.middleName" 
				  onkeyup="copyTo('nameOfStudent_middleName','nameOfFather_firstName,nameOfMother_middleName,nameOfGuardian_firstName')" 
				  				onblur="copyTo('nameOfStudent_middleName','nameOfFather_firstName,nameOfMother_middleName,nameOfGuardian_firstName')"/>
			  </div>
				  
			  <c:choose>
			  	<c:when test="${not empty error_nameOfStudent_lastName}">
			  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfStudent_lastName}"/>"> 
			  	</c:when>
			  	<c:otherwise>
			  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
			  	</c:otherwise>
			  </c:choose>
				<label class="control-label" for="focusedInput"><spring:message code="Label.Last.Name"/></label>
				
				  <form:input class="form-control"  id="nameOfStudent_lastName" path="nameOfStudent.lastName" onkeyup="copyTo('nameOfStudent_lastName','nameOfFather_lastName,nameOfMother_lastName,nameOfGuardian_lastName')"
				  					onblur="copyTo('nameOfStudent_lastName','nameOfFather_lastName,nameOfMother_lastName,nameOfGuardian_lastName')"/>
			  </div>
				  
			  <div class="control-group">
				<label class="control-label">Gender</label>
				<div class="controls">
					<div class="radio">
						<label>	
							<form:radiobutton path="student.gender" value="Male"/>Male
						</label>
						<label>	
							<form:radiobutton path="student.gender" value="Female" />Female
						</label>
					  </div>
				</div>
			  </div>
				  
			  <legend><spring:message code="Label.Father.Name"/></legend>
			  
			  <c:choose>
			  	<c:when test="${not empty error_nameOfFather_firstName}">
			  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfFather_firstName}"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Father.First.Name"/>">
			  	</c:otherwise>
			  </c:choose>
			  <label class="control-label" for="focusedInput"><spring:message code="Label.First.Name"/></label>
				<div class="controls">
				  <form:input class="form-control"  id="nameOfFather_firstName" path="nameOfFather.firstName"/>
				</div>
			  </div>
				  				  
				  <c:choose>
				  	<c:when test="${not empty error_nameOfFather_middleName}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfFather_middleName}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Father.Middle.Name"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Middle.Name"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="nameOfFather_middleName" path="nameOfFather.middleName" onkeyup="copyTo('nameOfFather_middleName','nameOfGuardian_middleName')"
					  				onblur="copyTo('nameOfFather_middleName','nameOfGuardian_middleName')"/>
					</div>
				  </div>
				  
				  <c:choose>
				  	<c:when test="${not empty error_nameOfFather_lastName}">
				  		<div class="control-group has-error red-tooltip"  data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfFather_lastName}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Father.Last.Name"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Last.Name"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="nameOfFather_lastName" path="nameOfFather.lastName"/>
					</div>
				  </div>


				 <legend><spring:message code="Label.Mother.Name"/></legend>
				  <c:choose>
				  	<c:when test="${not empty error_nameOfMother_firstName}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfMother_firstName}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group"  data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Mother.First.Name"/>">
				  	</c:otherwise>
				  </c:choose>
				  <label class="control-label" for="focusedInput"><spring:message code="Label.First.Name"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="nameOfMother_firstName" path="nameOfMother.firstName"/>
					</div>
				  </div>
				  				  
				  <c:choose>
				  	<c:when test="${not empty error_nameOfMother_middleName}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfMother_middleName}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Mother.Middle.Name"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Middle.Name"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="nameOfMother_middleName" path="nameOfMother.middleName"/>
					</div>
				  </div>
				  
				  <c:choose>
				  	<c:when test="${not empty error_nameOfMother_lastName}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfMother_lastName}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Mother.Last.Name"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Last.Name"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="nameOfMother_lastName" path="nameOfMother.lastName"/>
					</div>
				  </div>
				  
				  <legend><spring:message code="Label.Guardian.Name"/></legend>
				  <c:choose>
				  	<c:when test="${not empty error_nameOfGuardian_firstName}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfGuardian_firstName}"/>">    
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Guardian.First.Name"/>">
				  	</c:otherwise>
				  </c:choose>
				  <label class="control-label" for="focusedInput"><spring:message code="Label.First.Name"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="nameOfGuardian_firstName" path="nameOfGuardian.firstName"/>
					</div>
				  </div>
				  				  
				  <c:choose>
				  	<c:when test="${not empty error_nameOfGuardian_middleName}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfGuardian_middleName}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Guardian.Middle.Name"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Middle.Name"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="nameOfGuardian_middleName" path="nameOfGuardian.middleName"/>
					</div>
				  </div>
				  
				  <c:choose>
				  	<c:when test="${not empty error_nameOfGuardian_lastName}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_nameOfGuardian_lastName}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Guardian.Last.Name"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Last.Name"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="nameOfGuardian_lastName" path="nameOfGuardian.lastName"/>
					</div>
				  </div>
				  
				  <legend>Parent/Guardian's contact</legend>
				  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Guardian.Contact"/>">
				  <label class="control-label" for="focusedInput">Contact number</label>
					<div class="controls">
					  <form:input class="form-control"  id="student_contactNumberOfGuardian" path="student.contactNumberOfGuardian"/>
					</div>
				  </div>
				  
				<legend><spring:message code="Label.Birth.Information"/></legend>
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
				  	<c:when test="${not empty error_birthPlaceDetails_addressLineFirst}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_birthPlaceDetails_addressLineFirst}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Birth.Place"/>">
				  	</c:otherwise>
					</c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Birth.Place"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="birthPlaceDetails_addressLineFirst" path="birthPlaceDetails.addressLineFirst"/>
					</div>
				  </div>
				  
				  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Address.Line.Second"/>">
					<label class="control-label" for="focusedInput">Address line 2</label>
					<div class="controls">
					  <form:input class="form-control"  id="birthPlaceDetails_addressLineSecond" path="birthPlaceDetails.addressLineSecond"/>
					</div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="focusedInput">State</label>
					<div class="controls">
					  <form:select path="birthPlaceDetails.state" data-rel="chosen" style="width:30%">
						<core:forEach var="stateInfo" items="${states}">
							<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
						</core:forEach>
					  </form:select>
					</div>
				  </div>
				  
				  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Pin.Code"/>">
					<label class="control-label" for="focusedInput">Pin code</label>
					<div class="controls">
					  <form:input class="form-control"  id="birthPlaceDetails_pinCodeNumber" path="birthPlaceDetails.pinCodeNumber"/>
					</div>
				  </div>
				</fieldset>
                    </div>
                </div>
            </div>
        <!--/span-->

        <div class="box col-md-6">
            <div class="box-inner">
                <div class="box-content">
                 <fieldset class="form-horizontal">
         	<legend><spring:message code="Label.Joining.Information"/></legend>
					<c:choose>
				  	<c:when test="${not empty error_student_PRN}">
					  <div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_student_PRN}"/>">
					</c:when>
					  	<c:otherwise>
					  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.PRN.Number"/>">
					  	</c:otherwise>
					</c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.PRN.Number"/></label>
					<div class="controls">
						  <form:input class="form-control"  id="student_PRN" path="student.PRN"/>
					</div>
				  </div>
				  
				  <c:choose>
				  	<c:when test="${not empty error_admissionDate}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_admissionDate}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Admission.Date"/>">
				  	</c:otherwise>
				  </c:choose>
					<label class="control-label" for="focusedInput"><spring:message code="Label.Admission.Date"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="admissionDate" path="admissionDate"/>
					</div>
				  </div>
				  
				  <c:choose>
				  	<c:when test="${not empty error_student_addmissionTakenInclass}">
				  		<div class="control-group has-error">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group">
				  	</c:otherwise>
				  </c:choose>
				  <label class="control-label" for="focusedInput">Admission from class</label>
					<div class="controls">
					  <form:select id="student_addmissionTakenInclass" path="student.addmissionTakenInclass" data-rel="chosen"  style="width:15%">
				  			<core:forEach var="classInfo" items="${classes}">
				  				<form:option value="${classInfo.standardOrClass}">${classInfo.standardOrClass}</form:option>
				  			</core:forEach>
					  </form:select>
					</div>
				  </div>
				  
				  <c:choose>
				  	<c:when test="${not empty error_student_lastAttendedSchoolName}">
				  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_student_lastAttendedSchoolName}"/>">
				  	</c:when>
				  	<c:otherwise>
				  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Last.School.Name"/>">
				  	</c:otherwise>
				  </c:choose>
				  <label class="control-label" for="focusedInput"><spring:message code="Label.Last.School.Name"/></label>
					<div class="controls">
					  <form:input class="form-control"  id="student_lastAttendedSchoolName" path="student.lastAttendedSchoolName"/>
					</div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="focusedInput">Current class</label>
					<div class="controls">
					  	<form:select path="student.standard" data-rel="chosen" style="width:15%">
				  			<core:forEach var="classInfo" items="${classes}">
				  				<form:option value="${classInfo.standardOrClass}">${classInfo.standardOrClass}</form:option>
				  			</core:forEach>
						</form:select>
						<form:select path="student.division" data-rel="chosen"  style="width:15%">
				  			<core:forEach var="classInfo" items="${classes}">
				  				<form:option value="${classInfo.division}">${classInfo.division}</form:option>
				  			</core:forEach>
						</form:select>
					</div>
				  </div>
				  
				  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="Enter Roll Number">
					<label class="control-label" for="focusedInput">Roll Number</label>
					<div class="controls">
					  <form:input class="form-control"  id="student_rollNumber" path="student.rollNumber"/>
					</div>
				  </div>

				  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Contact.Number"/>">
					<label class="control-label" for="focusedInput">Contact number</label>
					<div class="controls">
					  <form:input class="form-control"  id="student_contactNumber" path="student.contactNumber"/>
					</div>
				  </div>
				  
				  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Email.Id"/>">
					<label class="control-label" for="focusedInput">Email Id</label>
					<div class="controls">
					  <form:input class="form-control"  id="student_emailId" path="student.emailId"/>
					</div>
				  </div>
				  
				  <legend>Caste and scholarship</legend>
				  		<div class="control-group">
						<label class="control-label" for="focusedInput">Blood group</label>
						<div>
							<form:select path="student.bloodGroup" data-rel="chosen"  style="width:30%">
								<form:option value="">Select blood group</form:option>
								<core:forEach var="bloodGroups" items="<%=com.samarthsoft.prabandhak.enums.BloodGroup.values()%>">
									<form:option value="${bloodGroups.bloodGroupName}">${bloodGroups.bloodGroupName}</form:option>
								</core:forEach>
							</form:select>
						</div>
				  	</div>

					<div class="control-group">
						<label class="control-label" for="focusedInput">Caste</label>
						<div class="controls">
						  	<form:select id="student_caste" path="student.caste" data-rel="chosen" onChange="changeDD('student_caste','student_category')" style="width:30%">
					  			<form:option id="${student.student.caste}" value="${student.student.caste}">${student.student.caste}</form:option>
					  			<core:forEach var="castInfo" items="${castes}">
					  				<form:option id="${castInfo.categoryGuid}" value="${castInfo.toString()}">${castInfo.toString()}</form:option>
	  							</core:forEach>
							</form:select>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="focusedInput">Category</label>
						<div class="controls">
							<form:select id="student_category" path="student.category" data-rel="chosen" style="width:30%">
						  			<core:forEach var="categoryInfo" items="${categories}">
						  				<form:option value="${categoryInfo.toString()}" id="${categoryInfo.guid}">${categoryInfo.toString()}</form:option>
						  			</core:forEach>
							</form:select>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="focusedInput">Scholarship type</label>
						<div class="controls">
						  	<form:select path="student.scholarshipType" data-rel="chosen" style="width:30%">
					  			<core:forEach var="scholarshiptypeInfo" items="${scholarshiptypes}">
					  				<form:option value="${scholarshiptypeInfo.name}">${scholarshiptypeInfo.name}</form:option>
					  			</core:forEach>
							</form:select>
						</div>
					</div>
					
					<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Aadhar.Number"/>">
						<label class="control-label" for="focusedInput">Adhar card number</label>
						<div class="controls">
						  <form:input class="form-control"  id="student_aadharCardNumber" path="student.aadharCardNumber"/>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="focusedInput">Nationality</label>
						<div class="controls">
					  		<form:select path="student.nationality" data-rel="chosen" style="width:30%">
								<core:forEach var="nationalityInfo" items="${nationalities}">
									<form:option value="${nationalityInfo.name}">${nationalityInfo.name}</form:option>
								</core:forEach>
							</form:select>
						</div>
				  	</div>
				  	
				<legend><spring:message code="Label.Permanent.Address"/></legend>
				<c:choose>
			  	<c:when test="${not empty error_permanentAddressDetails_addressLineFirst}">
			  		<div class="control-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_permanentAddressDetails_addressLineFirst}"/>">
			  	</c:when>
			  	<c:otherwise>
			  		<div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Permanent.Address"/>">
			  	</c:otherwise>
				</c:choose>
				<label class="control-label" for="focusedInput"><spring:message code="Label.Permanent.Address"/></label>
				<div class="controls">
				  	<form:input class="form-control"  id="permanentAddressDetails_addressLineFirst" path="permanentAddressDetails.addressLineFirst" onkeyup="copyTo('permanentAddressDetails_addressLineFirst','currentAddressDetails_addressLineFirst')"
				  	 				onblur="copyTo('permanentAddressDetails_addressLineFirst','currentAddressDetails_addressLineFirst')"/>
				</div>
			  </div>
			  
			  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Permanent.Address.Line.Second.Generic"/>">
				<label class="control-label" for="focusedInput">Address line 2</label>
				<div class="controls">
				  <form:input class="form-control"  id="permanentAddressDetails_addressLineSecond" path="permanentAddressDetails.addressLineSecond" onkeyup="copyTo('permanentAddressDetails_addressLineSecond','currentAddressDetails_addressLineSecond')"
				  					onblur="copyTo('permanentAddressDetails_addressLineSecond','currentAddressDetails_addressLineSecond')"/>
				</div>
			  </div>
			  <div class="control-group">
				<label class="control-label" for="focusedInput">State</label>
				<div class="controls">
				  	<form:select path="permanentAddressDetails.state" data-rel="chosen" style="width:30%">
						<core:forEach var="stateInfo" items="${states}">
							<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
						</core:forEach>
					</form:select>
				</div>
			  </div>
			  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Permanent.Address.Pin.Code.Generic"/>">
				<label class="control-label" for="focusedInput">Pin code</label>
				<div class="controls">
				  <form:input class="form-control"  id="permanentAddressDetails_pinCodeNumber" path="permanentAddressDetails.pinCodeNumber" onkeyup="copyTo('permanentAddressDetails_pinCodeNumber','currentAddressDetails_pinCodeNumber')"
				  		onblur="copyTo('permanentAddressDetails_pinCodeNumber','currentAddressDetails_pinCodeNumber')"/>
				</div>
			  </div>
				  	
			<legend>Current address</legend>
			<c:choose>
		  	<c:when test="${not empty error_currentAddressDetails_addressLineFirst}">
		  		<div class="control-group has-error">
		  	</c:when>
		  	<c:otherwise>
		  		<div class="control-group">
		  	</c:otherwise>
			</c:choose>
			<label class="control-label" for="focusedInput">Address line 1</label>
			<div class="controls"  data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Current.Address.Generic"/>">
			  <form:input class="form-control"  id="currentAddressDetails_addressLineFirst" path="currentAddressDetails.addressLineFirst"/>
			</div>
		  </div>
		  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Current.Address.Line.Second.Generic"/>">
			<label class="control-label" for="focusedInput">Address line 2</label>
			<div class="controls">
			  <form:input class="form-control"  id="currentAddressDetails_addressLineSecond" path="currentAddressDetails.addressLineSecond"/>
			</div>
		  </div>
		  <div class="control-group">
			<label class="control-label" for="focusedInput">State</label>
			<div class="controls">
			  	<form:select path="currentAddressDetails.state" data-rel="chosen" style="width:30%">
					<core:forEach var="stateInfo" items="${states}">
						<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
					</core:forEach>
				</form:select>
			</div>
		  </div>
		  <div class="control-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Permanent.Address.Pin.Code.Generic"/>">
			<label class="control-label" for="focusedInput">Pin code</label>
			<div class="controls">
			  <form:input class="form-control"  id="currentAddressDetails_pinCodeNumber" path="currentAddressDetails.pinCodeNumber"/>
			</div>
		  </div>

		  <div class="control-group">
		  </div>
				</fieldset>
                    </div>
                </div>
            </div>
    
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
				<fieldset class="form-horizontal">
				<legend>Subjects</legend>
				<c:if test="${not empty subjects}">
					  <c:forEach var="subject" items="${subjects}">
					  		<core:choose>
						  		<core:when test="${fn:contains(student.student.subjectsForExam,subject.code)}">
						    		<span>
								  		<form:checkbox path="student.subjectsForExam" id="${subject.code}" value="${subject.code}" checked="true"/>
								  		${subject.name}
							  		</span>
						  		</core:when>
						  		<core:otherwise>
							  		<span>
								  		<form:checkbox path="student.subjectsForExam" id="${subject.code}" value="${subject.code}"/>
								  		${subject.name}
							  		</span>
						  		</core:otherwise>
					  		</core:choose>
					  </c:forEach>
				</c:if>
				</fieldset>
			</div>
		</div><!--/span-->
	</div>
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-content">
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">Save changes</button>
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