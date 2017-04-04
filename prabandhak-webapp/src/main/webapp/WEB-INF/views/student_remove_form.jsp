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
			
			<!-- breadcrum starts -->
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="dashboard.htm">Home</a> <span class="divider"></span>
					</li>
					<li>
						<b>Remove student from catalog</b>
					</li>
				</ul>
			</div>
			<!-- breadcrum ends -->			

	<form:form commandName="studentremove" action="removestudentfromcatalogue.htm" method="POST" name="studentremove">
	<form:hidden path="studentGuid"/>
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
					<fieldset class="form-horizontal">
					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Student name</label>
					  		<label> : ${studentremove.studentName}</label>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Reason of removing</label>
					  		<form:input class="form-control" id="reasonOfRemoving" path="reasonOfRemoving"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Date of removing</label>
					  		<form:input class="form-control" id="dateOfRemoving" path="dateOfRemoving"/>
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
					<button class="btn">Cancel</button>
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