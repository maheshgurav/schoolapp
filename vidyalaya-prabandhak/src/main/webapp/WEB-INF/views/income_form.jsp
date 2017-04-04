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
	<title>School Management System - Income</title>

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
    Income
    </h1>
    <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
    <li><a href="#"><i class="fa fa-dashboard"></i> Income</a></li>
    <li class="active">Add/Edit Income</li>
    </ol>
    </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

<section class="content">


	<form:form commandName="income" method="POST" name="income">
    <form:hidden path="incomeDetails.guid"/>
	
    	<div class="row">
		<div class="col-md-12">

		 	<div class="box box-primary">
	            <div class="box-body">
					
					<div class="form-group">
			    		

					<div class="col-md-3">
					  <c:choose>
					  	<c:when test="${not empty error_incomeDetails_incomeFrom}">
					  		<div class="form-group-text has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_incomeDetails_incomeFrom}"/>">
					  	</c:when>
					  	<c:otherwise>
					  		<div class="form-group-text" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.Income.From"/>">
					  	</c:otherwise>
					  </c:choose>
					  <label class="control-label" for="focusedInput"><spring:message code="Label.Income.From"/></label>
				  	  <div class="control">
				  	  	<form:input class="form-control" id="incomeDetails_incomeFrom" path="incomeDetails.incomeFrom"/>
					  </div>
					  </div>
					  </div>
					  
					  <div class="col-md-3">
					  <c:choose>
					  	<c:when test="${not empty error_dateOfIncome}">
					  		<div class="form-group-text has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_dateOfIncome}"/>">
					  	</c:when>
					  	<c:otherwise>
					  		<div class="form-group-text" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.Reason"/>">
					  	</c:otherwise>
					  </c:choose>
						<label class="control-label" for="focusedInput"><spring:message code="Label.Date"/></label>
					  	<div class="control">
							<form:input class="form-control" id="dateOfIncome" path="dateOfIncome"/>
						</div>	
					  </div>
					  </div>
	
					<div class="col-md-3">
					  <c:choose>
					  	<c:when test="${not empty error_incomeDetails_amount}">
					  		<div class="form-group-text has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="${error_incomeDetails_amount}"/>">
					  	</c:when>
					  	<c:otherwise>
					  		<div class="form-group-text" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Error.Empty.Date"/>">
					  	</c:otherwise>
					  </c:choose>
						<label class="control-label" for="focusedInput"><spring:message code="Label.Amount"/></label>
					  	<div class="control">
					  		<form:input class="form-control" id="incomeDetails_amount" path="incomeDetails.amount"/>
					  	</div>
					  </div>
					</div>
					
					</div>
				</div>
			</div>			    
		</div>
    
   <div class="col-md-12">
        <div class="box-inner">
            <div class="box-content">
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
  $( "#dateOfIncome" ).datepicker();
});
</script>

<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>