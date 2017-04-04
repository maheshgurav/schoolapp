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
	<title>School Management System - Add/edit Subject</title>
	
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
						<a href="showsubjects.htm">Subjects</a>
					</li>
					<li>
						<b>Subject Details</b>
					</li>
				</ul>
			</div>

	<form:form commandName="subject" method="POST" name="subject">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
					<fieldset class="form-horizontal">
						<form:hidden path="guid"/>
					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Name</label>
					  		<form:input class="form-control" id="name" path="name"/>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Code</label>
					  		<form:input class="form-control" id="code" path="code"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="selectError1">Default for classes</label>
							<div class="controls">
							  <form:select style="width:150px;height:500px;" id="defaultForClasses" path="defaultForClasses" multiple="multiple" data-rel="chosen">
								<core:forEach var="classInfo" items="${classes}">
									<core:choose>
								  	<core:when test="${subjectforclasses != null and subjectforclasses.size() > 0}">
										<core:forEach var="standard" items="${subject.subjectforclasses}">
								  			<core:choose>
								  			<core:when test="${classInfo.standardOrClass == standard}">
					  							<form:option value="${classInfo.standardOrClass}" selected="selected">${classInfo.standardOrClass}</form:option>
					  						</core:when>
					  						<core:otherwise>
					  							<form:option value="${classInfo.standardOrClass}">${classInfo.standardOrClass}</form:option>
					  						</core:otherwise>
					  						</core:choose>
					  					</core:forEach>
					  				</core:when>
					  				<core:otherwise>
					  						<form:option value="${classInfo.standardOrClass}">${classInfo.standardOrClass}</form:option>
					  				</core:otherwise>
					  				</core:choose>
				  				</core:forEach>	
							</form:select>
							</div>
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
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>