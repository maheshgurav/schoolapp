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
	<title>School Management System - Income</title>
	
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
						<a href="income.htm">Income</a><span class="divider"></span>
					</li>
					<li>
						<span><b>Income Details</b></span>
					</li>
				</ul>
			</div>

	<form:form commandName="income" method="POST" name="income">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
            	<form:hidden path="incomeDetails.guid"/>
                <div class="box-content">
					<fieldset class="form-horizontal">
					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Income from</label>
					  		<form:input class="form-control" id="incomeDetails_incomeFrom" path="incomeDetails.incomeFrom"/>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Date</label>
					  		<form:input class="form-control" id="dateOfIncome" path="dateOfIncome"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Amount</label>
					  		<form:input class="form-control" id="incomeDetails_amount" path="incomeDetails.amount"/>
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
					<a class="btn" href="income.htm">Cancel</a>
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