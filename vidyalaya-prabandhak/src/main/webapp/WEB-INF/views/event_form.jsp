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
	<title>School Management System - Event</title>

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
    Event Details
    </h1>
    <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
    <li><a href="#"><i class="fa fa-dashboard"></i> Calendar</a></li>
    <li class="active">Add/Edit Event</li>
    </ol>
    </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

<section class="content">


	<form:form commandName="event" method="POST" name="event" id="event_form">
		<form:hidden path="event.guid"/>
	
    	<div class="row">
		<div class="col-md-12">

		 	<div class="box box-primary">
	            <div class="box-body">
					
					<div class="form-group">
			    		
					
					<div class="col-md-3">
					  	<div class="form-group-text">
							<label class="control-label" for="focusedInput"><spring:message code="Label.Name"/></label>
					  		<form:input class="form-control" id="event_name" path="event.name"/>
			  			</div>

					  	<div class="form-group-text">
							<label class="control-label" for="focusedInput"><spring:message code="Label.Date.Of.Event"/></label>
					  		<form:input class="form-control" id="date" path="date"/>
			  			</div>

					</div>
					<div class="col-md-12">
					  	<div class="form-group-text">
							<label class="control-label" for="focusedInput"><spring:message code="Label.Description"/></label>
					  		<form:input class="form-control" id="event_description" path="event.description"/>
			  			</div>					
			  			
					  	<div class="control-group">
						<label class="control-label"><spring:message code="Label.Type.Of.Event"/></label>
						<div class="controls">
							<div class="radio">
<%-- 								<label>	
									<form:radiobutton path="event.typeOfEvent" value="Birthday"/>Birthday
								</label>
 --%>
 								<label>	
									<form:radiobutton path="event.typeOfEvent" value="Holiday" checked="checked"/>Holiday
								</label>
							  </div>
						</div>
					  </div>
					</div>
					<%-- 	<div class="col-md-3">
						  	<c:choose>
							  	<c:when test="${not empty error_name}">
							  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="${error_name}"> 
							  	</c:when>
							  	<c:otherwise>
							  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="${error_name}">
							  	</c:otherwise>
						  	</c:choose>
							<label class="control-label" for="focusedInput">Name Of Category</label>
							<div class="control">
						  		<form:input class="form-control" id="name" path="name"/>
				  			</div>
						  </div>
						</div> --%>
					</div>
				</div>
			</div>			    

  <div class="box box-default">
    <div class="box-body">
	  		<div class="form-actions">
					<button type="submit" class="btn btn-default">Save changes</button>
					<a class="btn" href="showcategories.htm">Cancel</a>
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
  <script>
  $(function() {
    $( "#date" ).datepicker();
  });
  </script>
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>