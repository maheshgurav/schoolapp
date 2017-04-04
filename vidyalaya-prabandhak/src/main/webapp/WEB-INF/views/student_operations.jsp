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
	<title>School Management System - Student Operations</title>

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
    Student Operations
    </h1>
    <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
    <li><a href="#"><i class="fa fa-dashboard"></i> Students</a></li>
    <li class="active">Student Operations</li>
    </ol>
    </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

<section class="content">

	<div class="row">
		<div class="col-md-12">
			<a class="btn btn-default" href="opchangeclass.htm"><spring:message code="Label.Change.Standard.Or.Division"/></a>
			<a class="btn btn-default" href="opmakealumnis.htm"><spring:message code="Label.Mark.Alumnus"/></a>
		</div>
	</div>

	<div class="seperator_line">
	</div>

	<div class="row">
		<div class="col-md-12">
	</div>	
</div>
</div>

<!--footer-->

<script src="js/student.js"></script>
<script src="js/common.js"></script>
<%@ include file="/WEB-INF/common_views/footer.jsp" %>