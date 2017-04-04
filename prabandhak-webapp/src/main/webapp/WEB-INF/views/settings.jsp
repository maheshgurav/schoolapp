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
	<title>School Management System - Settings</title>
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
						<a href="dashboard.jsp">Home</a> <span class="divider"></span>
					</li>
					<li>
						<b>Settings</b>
					</li>
				</ul>
			</div>

<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
    		
	    	<div class="box-header well" data-original-title="">
	    		<h2><i class="glyphicon glyphicon-leaf"></i> Settings</h2>
			</div>
						
			<div class="box-content">
					<!--
					<a class="btn btn-primary" href="javascript:loadCasts()">Load castes</a>
					<a class="btn btn-primary" href="javascript:loadCategories()">Load categories</a>
					<a class="btn btn-primary" href="javascript:loadScholarshipTypes()">Load scholarship types</a>
					<a class="btn btn-primary" href="javascript:loadClasses()">Load classes</a>
					<a class="btn btn-primary" id="btn-exams" onclick="javascript:loadExams(event)">Load classes</a>
					-->
					<a class="btn btn-primary" href="showcasts.htm">Castes</a>
					<a class="btn btn-primary" href="showcategories.htm">Categories</a>
					<a class="btn btn-primary" href="showscholarshiptypes.htm">Scholarship types</a>
					<a class="btn btn-primary" href="showclasses.htm">Classes</a>
					<a class="btn btn-primary" href="showschooltypes.htm">School types</a>
					<a class="btn btn-primary" href="showsubjects.htm">Subjects</a>
					<a class="btn btn-primary" id="btn-exams" href="showexams.htm">Exams</a>
			</div>
		</div>
	</div>

	<div class="box col-md-12">
		<div class="box-inner">
    		
	    	<div class="box-header well" data-original-title="">
	    		<h2><i class="glyphicon glyphicon-leaf"></i> Roll Number Update Service</h2>
			</div>
						
			<div class="box-content">
				<%@ include file="/WEB-INF/common_views/classes.jsp" %>
				<a class="btn btn-primary" href="javascript:updateRollNumbers()">Update roll numbers</a>
			</div>

		</div>

	</div>
</div>
</div>
</div>

</div>


</div>

<!--footer-->


<script language="javascript" type="text/javascript" src="js/settings.js"></script>
<%@ include file="/WEB-INF/common_views/footer.jsp" %>