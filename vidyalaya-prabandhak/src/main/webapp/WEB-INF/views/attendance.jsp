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
	<title>School Management System - Attendance</title>

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
    Attendance
    </h1>
    <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
    <li><a href="#"><i class="fa fa-dashboard"></i> Students</a></li>
    <li class="active">Attendance</li>
    </ol>
    </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

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
			    
					
			<div class="col-md-4">
				<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
					<label class="control-label" for="selectError"><spring:message code="Label.Class"/></label>
					<div class="controls">
					    <select id="classguid" data-rel="chosen-select" class="chosen-select" style="width:100%">
	  						<c:forEach var="classInfo" items="${classes}">
	  							<option value="${classInfo.standardOrClass}-${classInfo.division}" id="${classInfo.standardOrClass}-${classInfo.division}">${classInfo.standardOrClass} - ${classInfo.division}</option>
	  						</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
							<label class="control-label" for="selectError"><spring:message code="Label.Date"/></label>
								 <div class="controls">
							  	<input id="date" class="form-control"/>
							  </div>
				</div>
				</div>			

			<div class="col-md-4">
				<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
					<label class="control-label" for="selectError">&nbsp;</label>
					<div class="controls">
						<a class="btn btn-default" href="javascript:getAttendance()"><spring:message code="Label.Show.Attendance"/></a>
					</div>
				</div>
			</div>
		</div>			    
	</div>
	</div>
	</div></div>
	

	 	<div class="box box-primary">
            	
	<div class="box-body">
		<div class="form-group">
		    
			    <div class="control-group">
			    	<label class="control-label" for="selectError">Roll Numbers Of Absent Students</label>
				    	<div class="controls">
							  <input class="form-control" id="absentRollNumbers" 
							  onkeypress="return checkAndSelectCheckBox(this,event);" onblur="addCommaAtLast()"/>
						</div>
				</div>
			</div>
		</div>
	</div>

        <div class="form-group">
		    <div class="input-group col-md-12" id="table_here">
           	</div>
		</div>	

  <div class="box box-default">
    <div class="box-body">
	  		<div class="form-actions">
					<a class="btn btn-default" href="javascript:saveAttendance()">Save Attendance</a>
					<a class="btn" href="students.htm">Cancel</a>
				</div>
			</div>	
		</div>
	</div>	
	

</div>

<!--footer-->

<script src='js/common.js'></script>
<script src='js/student.js'></script>
<script src='js/attendance.js'></script>

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
  
<script>
	$(function() {
	  $( "#date" ).datepicker();
	});
</script>
  

<%@ include file="/WEB-INF/common_views/footer.jsp" %>