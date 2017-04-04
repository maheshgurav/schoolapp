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
	<title>School Management System - Dashboard</title>

		<!-- topbar starts -->
			<%@ include file="/WEB-INF/common_views/header.jsp" %>
		<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<%@ include file="/WEB-INF/common_views/menu.jsp" %>			
			<!-- left menu ends -->
			
			<div id="content" class="col-lg-10 col-sm-10">
			<!-- content starts -->

			<div class="row">
						<div class="row">
		
			<div class="box col-md-12">
				<div class="box-inner">

					<div class="box-content">
					<span>
						Varvand Gram Shikshan Sanstha's
						Shri Gopinath Secondary, Higher Secondary & Technical School, Varvand. 
					</span>
					</div>
				</div>
			</div>


			<div class="box col-md-4">
				<div class="box-inner">
					<div class="box-content" id="container_students">
					</div>
				</div>
			</div>


			<div class="box col-md-4">
				<div class="box-inner">
					<div class="box-content" id="container_teachers">
					</div>
				</div>
			</div>


			<div class="box col-md-4">
				<div class="box-inner">
					<div class="box-content" id="container_staff">
					</div>
				</div>
			</div>

			</div>

			<div class="row">
			</div>
		</div>
	</div>
</div>
</div>


<script src="js/highcharts.js"></script>
<script src="js/exporting.js"></script>
<script src="js/dashboard.js"></script>
		
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>