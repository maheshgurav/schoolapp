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
	<title>School Management System - Change Class Or Division</title>

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
            Change Class Or Division
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="#"><i class="fa fa-dashboard"></i> Students</a></li>
            <li class="active">Change Class Or Division</li>
          </ol>
        </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

<section class="content">

	<form:form commandName="changeclass" action="changeclassordivisionofstudent.htm" method="POST" name="changeclass">
	<form:hidden path="studentGuid"/>
	
    	<div class="row">
		<div class="col-md-12">

		 	<div class="box box-primary">
	            <div class="box-body">
					
					<div class="form-group">
			    		
					
						<div class="col-md-12">
							<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
							<label class="control-label" for="focusedInput"><spring:message code="Label.Student.Name"/> : ${changeclass.studentName}</label>
						  </div>
						</div>
						
						<div class="col-md-3">
							<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
							<label class="control-label" for="focusedInput">Current Standard</label>
							<div class="control">
						  		<form:input class="form-control disabled" id="currentStandard" path="currentStandard"/>
				  			</div>
						  </div>
						</div>
						
						<div class="col-md-3">
							<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
							<label class="control-label" for="focusedInput">Current Division</label>
							<div class="control">
						  		<form:input class="form-control" id="currentDivision" path="currentDivision"/>
				  			</div>
						  </div>
						</div>
						</div>
					</div>
				</div>
			</div>			    
		
		<div class="col-md-12">

		 	<div class="box box-primary">
	            <div class="box-body">
					
					<div class="form-group">
			    		
			
			
									<div class="col-md-3">
							<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
							<label class="control-label" for="focusedInput">New Standard</label>
							<div class="control">
				  				<form:select id="changedStandard" path="changedStandard">
									<core:forEach var="classInfo" items="${classes}">
										<form:option value="${classInfo.standardOrClass}">${classInfo.standardOrClass}</form:option>
									</core:forEach>
								</form:select>	
				  			</div>
						  </div>
						</div>

						<div class="col-md-3">
							<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
							<label class="control-label" for="focusedInput">New Division</label>
							<div class="control">
				  				<form:select id="changedDivision" path="changedDivision">
									<core:forEach var="classInfo" items="${classes}">
										<form:option value="${classInfo.division}">${classInfo.division}</form:option>
									</core:forEach>
								</form:select>	
				  			</div>
						  </div>
						</div>

						
						<div class="col-md-3">
						  	<c:choose>
							  	<c:when test="${not empty error_name}">
							  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_name}"/>"> 
							  	</c:when>
							  	<c:otherwise>
							  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Enter.Student.Last.Name"/>">
							  	</c:otherwise>
						  	</c:choose>
							<label class="control-label" for="focusedInput">Date Of Change</label>
							<div class="control">
						  		<form:input class="form-control" id="dateOfChange" path="dateOfChange"/>
				  			</div>
						  </div>
						</div>
						</div>
						</div>
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
<script src='js/student.js'></script>

<script>
$(function() {
  $( "#dateOfChange" ).datepicker();
});
</script>


<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>