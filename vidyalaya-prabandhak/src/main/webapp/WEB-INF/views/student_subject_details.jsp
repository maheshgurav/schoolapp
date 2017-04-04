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
	<title>School Management System - Student Subject Details</title>

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
            Student Subject Details
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="#"><i class="fa fa-dashboard"></i> Students</a></li>
            <li class="active">Add/Edit Students Subject Details</li>
          </ol>
        </section>

<section class="content">
		
	<input type="hidden" id="student_guid" value="${student.studentId}"/>

    <div id="success_alert" class="alert alert-success" role="alert" style="display: none;"><strong>Success...</strong> You have updated subject details of students </div>

	<div id="failure_alert" class="alert alert-danger" role="alert" style="display: none;"><strong>Failure...</strong> Problem while updating subject details of students </div>
			
	<div class="row">
		<div class="col-md-12">

	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Subjects</h3>
            </div>
            <div class="box-body">
	            <div class="form-group">
			    <div class="input-group col-md-12" id="subs">
				  	<core:forEach var="subject" items="${subjects}">
				  		<core:choose>
				  		<core:when test="${subject.isTakenByStudent}">
							<input type="checkbox" value="${subject.guid}" id="${subject.guid}" name="${subject.subjectName}" checked="checked" /> ${subject.subjectName}
						</core:when>
						<core:otherwise>
							<input type="checkbox" value="${subject.guid}" id="${subject.guid}" name="${subject.subjectName}" /> ${subject.subjectName}
						</core:otherwise>
						</core:choose>
					</core:forEach>
				</div>			    
			</div>
		</div>
	</div>
	</div>
</div>

<div class="box box-default">
            <div class="box-body">
	    		<div class="form-actions">
					<button type="submit" class="btn btn-default" onclick="javascript:saveSubjects()">Save changes</button>
					<a class="btn" href="students.htm">Cancel</a>
				</div>
			</div>	
		</div>
	</div>	
</div><!--/span-->
<!-- content ends -->
</div><!--/#content.span10-->
</div><!--/fluid-row-->

	<script src='js/common.js'></script>
	<script src='js/student.js'></script>
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>