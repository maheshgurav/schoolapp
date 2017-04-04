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

			<div>
				<ul class="breadcrumb">
					<li>
						<a href="dashboard.htm">Home</a> <span class="divider"></span>
					</li>
					<li>
						<b>Leaving certificate</b>
					</li>
				</ul>
			</div>

	<form:form commandName="leavingcertificate" action="leavingcertificate.htm" method="POST" name="leavingcertificate">
	<form:hidden path="studentGuid"/>
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
					<fieldset class="form-horizontal">
					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Student name : </label>
					  		<label> ${leavingcertificate.studentName}</label>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Progress</label>
					  		<form:input class="form-control" id="leavingCertificate_progress" path="leavingCertificate.progress"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Conduct</label>
					  		<form:input class="form-control" id="leavingCertificate_conduct" path="leavingCertificate.conduct"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Reason of leaving</label>
					  		<form:input class="form-control" id="leavingCertificate_reasonOfLeaving" path="leavingCertificate.reasonOfLeaving"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Remark</label>
					  		<form:input class="form-control" id="leavingCertificate_remark" path="leavingCertificate.remark"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Date of leaving</label>
					  		<form:input class="form-control" id="leavingDate" path="leavingDate"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Studying Since (Month And Year)</label>
					  		<form:input class="form-control" id="leavingCertificate_studyingSinceMonthAndYear" path="leavingCertificate.studyingSinceMonthAndYear"/>
			  			</div>
					</fieldset>
                </div>
            </div>
       	</div>
        <!--/span-->


    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-content">
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">Save changes</button>
					<core:if test="${not empty showcertificatebutton}">
						<a class="btn btn-success" href="getlc.htm?id=${studentGuid}">Generate Leaving Certificate</a>
					</core:if>
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