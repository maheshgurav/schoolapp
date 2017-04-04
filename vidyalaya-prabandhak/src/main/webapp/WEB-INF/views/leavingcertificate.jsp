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
	<title>School Management System - Student Leaving Certificate</title>

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
            Student Leaving Certificate
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="#"><i class="fa fa-dashboard"></i> Students</a></li>
            <li class="active">Student Leaving Certificate</li>
          </ol>
        </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

<section class="content">


	<form:form commandName="leavingcertificate" action="leavingcertificate.htm" method="POST" name="leavingcertificate">
	<form:hidden path="studentGuid"/>
    	<div class="row">
		<div class="col-md-12">
		 	<div class="box box-primary">
	            <div class="box-body">
					
					<div class="form-group">
			    		
					
						<div class="col-md-12">
				  			<div class="form-group-text">
								<label class="control-label" for="focusedInput"><spring:message code="Label.Student.Name"/> : </label>
						  		<label> ${leavingcertificate.studentName}</label>
				  			</div>
			  			</div>

						<div class="col-md-4">
				  			<div class="form-group-text">
								<label class="control-label" for="focusedInput"><spring:message code="Label.Progress"/></label>
						  		<form:input class="form-control" id="leavingCertificate_progress" path="leavingCertificate.progress"/>
			  				</div>
			  			</div>
			  			
						<div class="col-md-4">
				  			<div class="form-group-text">
								<label class="control-label" for="focusedInput"><spring:message code="Label.Conduct"/></label>
						  		<form:input class="form-control" id="leavingCertificate_conduct" path="leavingCertificate.conduct"/>
			  				</div>
			  			</div>
			  			
						<div class="col-md-4">
				  			<div class="form-group-text">
								<label class="control-label" for="focusedInput"><spring:message code="Label.Reason.Of.Leaving"/></label>
					  			<form:input class="form-control" id="leavingCertificate_reasonOfLeaving" path="leavingCertificate.reasonOfLeaving"/>
			  				</div>
			  			</div>
			  			
						<div class="col-md-4">
				  			<div class="form-group-text">
								<label class="control-label" for="focusedInput"><spring:message code="Label.Remark"/></label>
						  		<form:input class="form-control" id="leavingCertificate_remark" path="leavingCertificate.remark"/>
				  			</div>
						</div>
						  			
						<div class="col-md-4">
				  			<div class="form-group-text">
								<label class="control-label" for="focusedInput"><spring:message code="Label.Date.Of.Leaving"/></label>
						  		<form:input class="form-control" id="leavingDate" path="leavingDate"/>
				  			</div>					  
            			</div>    		
            </div>
       	</div>
        <!--/span-->
		</div>
		</div>
		</div>

    <div class="box box-default">
            <div class="box-body">
	    		<div class="form-actions">
					<button type="submit" class="btn btn-default">Save changes</button>
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
<script>
 $(function() {
   $( "#leavingDate" ).datepicker({
     	changeMonth: true,
     	changeYear: true
   });
 });
</script>

<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>