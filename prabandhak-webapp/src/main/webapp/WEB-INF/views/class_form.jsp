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
	<title>School Management System - Add/edit Class</title>
	
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
						<a href="settings.htm">Settings</a><span class="divider"></span>
					</li>
					<li>
						<a href="showclasses.htm">Classes</a><span class="divider"></span>
					</li>
					<li>
						<b>Class Details</b>
					</li>
				</ul>
			</div>


	<form:form commandName="classinfo" method="POST" name="classinfo">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
					<fieldset class="form-horizontal">
						<form:hidden path="guid"/>
						
					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Standard</label>
					  		<form:input class="form-control" id="standardOrClass" path="standardOrClass"/>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Division</label>
					  		<form:input class="form-control" id="division" path="division"/>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Type of school</label>
					  		<form:select style="width:150px;" id="schoolTypeGuid" path="schoolTypeGuid" data-rel="chosen">
						  			<core:forEach var="schoolType" items="${schoolTypes}">
						  				<form:option value="${schoolType.guid}" id="${schoolType.guid}">${schoolType.name}</form:option>
						  			</core:forEach>
							</form:select>
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
					<a class="btn" href="showclasses.htm">Cancel</a>
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
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>