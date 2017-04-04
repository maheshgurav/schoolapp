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
	<title>School Management System - Expense</title>
	
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
						<a href="expense.htm">Expense</a><span class="divider"></span>
					</li>
					<li>
						<span><b>Expense Details</b></span>
					</li>
				</ul>
			</div>

	<form:form commandName="expense" method="POST" name="expense">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
                <form:hidden path="expenseDetails.guid"/>
					<fieldset class="form-horizontal">
					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Expense for</label>
					  		<form:input class="form-control" id="expenseDetails_expenseFor" path="expenseDetails.expenseFor"/>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Date</label>
					  		<form:input class="form-control" id="dateOfExpense" path="dateOfExpense"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="focusedInput">Amount</label>
					  		<form:input class="form-control" id="expenseDetails_amount" path="expenseDetails.amount"/>
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
					<a class="btn" href="expense.htm">Cancel</a>
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