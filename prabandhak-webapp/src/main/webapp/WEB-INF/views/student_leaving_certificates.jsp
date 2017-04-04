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
	<title>School Management System - Student Leaving Certificates</title>
	
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
						<a href="certificates.htm">Certificates</a> <span class="divider"></span>
					</li>
					<li>
						<b>Generate Leaving Certificates</b>
					</li>
				</ul>
			</div>
			<!-- breadcrum ends -->		

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
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="selectError">Report Type</label>
							<div class="controls">
							    <select path="reporttype" id="reporttype" data-rel="chosen" style="width:30%">
			  						<option value="pdf" id="pdf">PDF</option>
			  						<option value="html" id="html">HTML</option>
								</select>
							</div>
							
							
						<div class="control-group">
							<label class="control-label" for="focusedInput">Progress</label>
					  		<input class="form-control" id="progress"></input>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Conduct</label>
					  		<input class="form-control" id="conduct"></input>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Reason of leaving</label>
					  		<input class="form-control" id="reasonOfLeaving"></input>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Remark</label>
					  		<input class="form-control" id="remark"></input>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Date of leaving</label>
					  		<input class="form-control" id="leavingDate"></input>
			  			</div>					  

			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Studying Since (Month And Year)</label>
					  		<input class="form-control" id="studyingSinceMonthAndYear"></input>
			  			</div>

						</div>

						<div class="box-content">
							<div class="form-actions">
								<a class="btn btn-primary" href="javascript:getStudents()">Show students</a>
							</div>
						</div>
						
						<div class="box-content" id="students">
						<table id="tbl_student" class="table table-striped table-bordered">
						  <thead>
							  <tr>
								  <th>Roll Number</th>
								  <th>Student Name</th>
								  <th>Generate Certificates</th>
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
					<a class="btn btn-primary" href="javascript:getLeavingCertificates()">Generate Leaving Certificates</a>
					<a class="btn">Cancel</a>
				</div>
			</div>	
		</div>
	</div>	
</div><!--/span-->

</div><!--/#content.span10-->
</div><!--/fluid-row-->

<script src='js/common.js'></script>
<script src='js/certificates.js'></script>
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>