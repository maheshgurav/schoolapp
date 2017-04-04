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
	<title>School Management System - Fill examination marks</title>
	
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
						<a href="dashboard.htm">Home</a> <span class="divider"></span>
					</li>
					<li>
						<b>Fill Examination marks</b>
					</li>
				</ul>
			</div>
			<!-- breadcrum ends -->		

	<form:form commandName="fillmarks" id="fillmarks" method="POST" class="form-horizontal">
	<form:hidden path="classGuid" id="form_classGuid"/>
	<form:hidden path="subjectGuid" id="form_subjectGuid"/>
	<form:hidden path="examGuid" id="form_examGuid"/>
	<form:hidden path="marks" id="marks"/>
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
                	<fieldset class="form-horizontal">
						<div class="control-group">
							<label class="control-label" for="selectError">Class</label>
							<div class="controls">
							    <select path="classGuid" id="classguid" data-rel="chosen" style="width:30%">
			  						<c:forEach var="classInfo" items="${classes}">
			  							<option value="${classInfo.standardOrClass}-${classInfo.division}" id="${classInfo.standardOrClass}-${classInfo.division}">${classInfo.standardOrClass} - ${classInfo.division}</option>
			  						</c:forEach>
								</select>
								<a href="javascript:getExams()">Load exams</a>
							</div>
						</div>

						<div class="control-group">
								<label class="control-label" for="selectError">Exam</label>
								<div class="controls">
								    <select id="examguid" data-rel="chosen" style="width:30%">
											<c:forEach var="examInfo" items="${exams}">
								  				<option value="${examInfo.guid}">${examInfo.name}</option>
								  			</c:forEach>
									</select>
									<a href="javascript:getSubjects()">Load Subjects</a>
								</div>
						</div>
						
						<div class="control-group">
								<label class="control-label" for="selectError">Subject</label>
								<div class="controls">
									<select path="subjectGuid" id="subject" onchange="javascript:getStudents()" data-rel="chosen"  style="width:30%">
									</select>
								</div>
						</div>
						
						<div class="box-content">
							<div class="form-actions">
								<a class="btn btn-primary" href="javascript:getStudentsAndMarks()">Show students</a>
							</div>
						</div>
						
						<div class="box-content" id="students">
						<table id="markstable" class="table table-striped table-bordered">
						  <thead>
							  <tr>
								  <th>Roll Number</th>
								  <th>Student Name</th>
								  <th>Marks</th>
							  </tr>
						  </thead>
						  <tbody>   
						  </tbody>
					  </table>            
						
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
					<a class="btn btn-primary" href="javascript:submitFillExamMarksFormObject()">Save changes</a>
					<a class="btn">Cancel</a>
				</div>
			</div>	
		</div>
	</div>	
</div><!--/span-->

</form:form>
</div><!--/#content.span10-->
</div><!--/fluid-row-->

<script src='js/common.js'></script>
<script src='js/exam.js'></script>
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>