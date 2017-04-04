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
	<title>School Management System - Reports</title>

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
    Reports
    </h1>
    <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
    <li class="active">Reports</li>
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
			    
					
			<div class="col-md-3">
				<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
					<label class="control-label" for="selectError"><spring:message code="Label.Class"/></label>
					<div class="controls">
					    <select path="classGuid" id="classguid" data-rel="chosen-select" class="chosen-select"  style="width:30%">
	  						<c:forEach var="classInfo" items="${classes}">
	  							<option value="${classInfo.standardOrClass}-${classInfo.division}" id="${classInfo.standardOrClass}-${classInfo.division}">${classInfo.standardOrClass} - ${classInfo.division}</option>
	  						</c:forEach>
						</select>
					</div>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
					<label class="control-label" for="selectError"><spring:message code="Label.Class"/></label>
					<div class="controls">
						<%@ include file="/WEB-INF/common_views/genders.jsp" %>
					</div>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="form-group" data-toggle="tooltip" data-placement="bottom">
					<label class="control-label" for="selectError"><spring:message code="Label.Report.Type"/></label>
					<div class="controls">
				        <select id="reportFormat" data-rel="chosen-select" class="chosen-select" >
	                		<option>Html</option>
	                		<option>Pdf</option>
	                	</select>
					</div>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="form-group" data-toggle="tooltip" data-placement="bottom">
					<label class="control-label" for="selectError">Month & Year</label>
					<div class="controls">
                	<select id="month" data-rel="chosen-select" class="chosen-select" >
                		<option>January</option>
                		<option>February</option>
                		<option>March</option>
						<option>April</option>
						<option>May</option>
						<option>June</option>
						<option>July</option>
						<option>August</option>
						<option>September</option>
						<option>October</option>
						<option>November</option>
						<option>December</option>
                	</select>
                	<select id="year" data-rel="chosen-select" class="chosen-select" >
                		<option>2015</option>
                		<option>2014</option>
                		<option>2013</option>
						<option>2012</option>
                	</select>						
					</div>
				</div>
			</div>
		</div>			    
	</div>
	</div>
	
<div class="row">
	
		<div class="col-md-6">
	
	 	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Class Wise Students Report</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<a class="btn btn-default" href="javascript:getReport('0')">Generate Report</a>		
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Caste Wise Students Report</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
				    <select id="casteguid" data-rel="chosen-select" class="chosen-select" >
		  				<core:forEach var="castInfo" items="${castes}">
		  					<option value="${castInfo.toString()}">${castInfo.toString()}</option>
		  				</core:forEach>
				</select>
				<a class="btn btn-default" href="javascript:getReport('3')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Scholarship Wise Students Report</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<select id="scholarshipguid" data-rel="chosen-select" class="chosen-select">
							<core:forEach var="scholarshiptypeInfo" items="${scholarshiptypes}">
								<option value="${scholarshiptypeInfo.name}">${scholarshiptypeInfo.name}</option>
							</core:forEach>
						</select>
						<a class="btn btn-default" href="javascript:getReport('6')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Exam Wise Report</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<select id="examguid" data-rel="chosen-select" class="chosen-select">
							<option value="">Select exam</option>
							<core:forEach var="examInfo" items="${exams}">
								<option value="${examInfo.guid}">${examInfo.name}</option>
							</core:forEach>
						</select>
						<a class="btn btn-default" href="javascript:getReport('6')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		</div>
	
	<div class="row">
	
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">ICards</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<a class="btn btn-default" href="javascript:getReport('8')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Category Wise Summary Report</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<select style="width:150px;" id="category" data-rel="chosen-select" class="chosen-select">
				  			<core:forEach var="categoryInfo" items="${categories}">
				  				<option value="${categoryInfo.toString()}" id="${categoryInfo.guid}">${categoryInfo.toString()}</option>
				  			</core:forEach>
						</select>
						<a class="btn btn-default" href="javascript:getReport('1')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		</div>

<div class="row">
	
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">General Register Report</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<a class="btn btn-default" href="javascript:getReport('9')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Teacher Details Report</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<a class="btn btn-default" href="javascript:getReport('0')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		</div>


<div class="row">
	
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Blank Attendance Summary Of Month</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<a class="btn btn-default" href="javascript:getReport('12')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Student Details For Attendance Sheet</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<a class="btn btn-default" href="javascript:getReport('10')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		</div>

<div class="row">
	
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Blank Attendance Sheet</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<a class="btn btn-default" href="javascript:getReport('11')">Generate Report</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Blank Attendance Summary Last Page</h3>
            </div>    	
            <div class="box-body">
	            <div class="form-group">
				    
						<a class="btn btn-default" href="javascript:getReport('13')">Generate Report</a>
					</div>
				</div>
			</div>
		</div> 
		</div>
</div>
		</div> 
		</div>
<!--footer-->
<script src='js/common.js'></script>
<script src='js/reports.js'></script>

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


<%@ include file="/WEB-INF/common_views/footer.jsp" %>