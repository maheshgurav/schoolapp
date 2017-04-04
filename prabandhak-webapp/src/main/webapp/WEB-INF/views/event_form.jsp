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
	<title>School Management System - Add/edit Event</title>
	
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
						<a href="dashboard.htm">Home</a> <span class="divider"></span>
					</li>
					<li>
						<a href="calendar.htm">Calendar</a><span class="divider"></span>
					</li>
					<li>
						<a href="events.htm?date=${date}">Events</a><span class="divider"></span>
					</li>
					<li>
						<b>Event Details</b>
					</li>
				</ul>
			</div>
			

	<form:form commandName="event" method="POST" name="event" id="event_form">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
					<fieldset class="form-horizontal">
						<form:hidden path="event.guid"/>
					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Name</label>
					  		<form:input class="form-control" id="event_name" path="event.name"/>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Description</label>
					  		<form:input class="form-control" id="event_description" path="event.description"/>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Date Of Event</label>
					  		<form:input class="form-control" id="date" path="date"/>
			  			</div>
			  			
					  	<div class="control-group">
						<label class="control-label">Type of event</label>
						<div class="controls">
							<div class="radio">
								<label>	
									<form:radiobutton path="event.typeOfEvent" value="Birthday"/>Birthday
								</label>
								<label>	
									<form:radiobutton path="event.typeOfEvent" value="Female" />Holiday
								</label>
							  </div>
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
					<button class="btn btn-primary" onclick="submitExamSettingsFormObject()">Save changes</button>
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
<script src='js/exam.js'></script>

<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>