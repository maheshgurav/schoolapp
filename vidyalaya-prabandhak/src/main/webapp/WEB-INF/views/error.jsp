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
		
		<input type="hidden" id="total_students" value="${total_students}"/>
		<input type="hidden" id="total_boys" value="${total_boys}"/>
		<input type="hidden" id="total_girls" value="${total_girls}"/>

		<input type="hidden" id="total_teachers" value="${total_teachers}"/>
		<input type="hidden" id="total_male_teachers" value="${total_male_teachers}"/>
		<input type="hidden" id="total_female_teachers" value="${total_female_teachers}"/>

		<input type="hidden" id="total_staff" value="${total_staff}"/>
		<input type="hidden" id="total_male_staff" value="${total_male_staff}"/>
		<input type="hidden" id="total_female_staff" value="${total_female_staff}"/>

		<input type="hidden" id="category_data" value="${category_data}"/>

			<div class="col-md-12">
				<div class="alert alert-success">
				</div>
			</div>

			<div class="col-md-12">
				<div class="alert alert-warning">
					<strong>
						${error} 
					</strong>
				</div>
			</div>
			</div>

			<div class="row">
			</div>
		</div>
	</div>
</div>
</div>

<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>