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
	<title>School Management System - Fill Examination Marks</title>

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
    Generate Leaving Certificates
    </h1>
    <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
    <li><a href="#"><i class="fa fa-dashboard"></i> Students</a></li>
    <li class="active">Fill Examination Marks</li>
    </ol>
    </section>

<section class="content">
	<div class="row">
		<div class="col-md-12">
		</div>
	</div>
			
	<div class="row">
		<div class="col-md-12">
	 	<div class="box box-primary">
            <div class="box-body">
	            <div class="form-group">	            
			    
	<form:form commandName="fillmarks" id="fillmarks" method="POST" class="form-horizontal">
		<form:hidden path="classGuid" id="form_classGuid"/>
		<form:hidden path="subjectGuid" id="form_subjectGuid"/>
		<form:hidden path="examGuid" id="form_examGuid"/>
		<form:hidden path="marks" id="marks"/>
					
						<div class="col-md-4">
							<label class="control-label" for="selectError"><spring:message code="Label.Class"/></label>
							<div class="controls">
							    <select path="classGuid" id="classguid" data-rel="chosen-select" class="chosen-select"  style="width:30%">
			  						<c:forEach var="classInfo" items="${classes}">
			  							<option value="${classInfo.standardOrClass}-${classInfo.division}" id="${classInfo.standardOrClass}-${classInfo.division}">${classInfo.standardOrClass} - ${classInfo.division}</option>
			  						</c:forEach>
								</select>
								<a href="javascript:getExams()"><spring:message code="Label.Load.Exams"/></a>
							</div>
						</div>

						<div class="col-md-4">
							<label class="control-label" for="selectError"><spring:message code="Label.Exam"/></label>
								<div class="controls">
								    <select id="examguid" style="width:30%">
											<c:forEach var="examInfo" items="${exams}">
								  				<option value="${examInfo.guid}">${examInfo.name}</option>
								  			</c:forEach>
									</select>
									<a href="javascript:getSubjects()">Load Subjects</a>
								</div>
						</div>

						<div class="col-md-4">
							<label class="control-label" for="selectError"><spring:message code="Label.Subject"/></label>
								<div class="controls">
									<select path="subjectGuid" id="subject" onchange="javascript:getStudents()" style="width:30%">
									</select>
									<a class="btn btn-default" href="javascript:getStudentsAndMarks()"><spring:message code="Label.Show.Students"/></a>
								</div>
						</div>
				</div>
			</div>
		</div>
	
	            <div class="form-group">
				    <div class="input-group col-md-12" id="table_here">
						 <%-- <table id="markstable" class="table table-striped table-bordered">
							  <thead>
								  <tr>
									  <th><spring:message code="Label.Roll.Number"/></th>
									  <th><spring:message code="Label.Student.Name"/></th>
									  <th><spring:message code="Label.Marks"/></th>
								  </tr>
							  </thead>
							  <tbody>   
							  </tbody>
						  </table> --%>
					</div>
				</div>
  <div class="box box-default">
    <div class="box-body">
	  		<div class="form-actions">
						<button type="submit" class="btn btn-default" onclick="submitFillExamMarksFormObject()">Save changes</button>
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
	<script src='js/exam.js'></script>
	
	  <script src="js/chosen.jquery.js" type="text/javascript"></script>
  <script src="js/prism.js" type="text/javascript" charset="utf-8"></script>
  
  <style type="text/css" media="all">
    /* fix rtl for demo */
    .chosen-rtl .chosen-drop { left: -9000px; }
  </style>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
	
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>