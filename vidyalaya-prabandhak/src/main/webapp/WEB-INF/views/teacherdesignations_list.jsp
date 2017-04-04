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
	<title>School Management System - Teacher Designations</title>

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
            Teacher Designations
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li class="active">Teacher Designations</li>
            <li><a class="badge bg-light-blue" href="addteacherdesignation.htm"><i class="fa fa-plus"></i> Add Teacher Designation</a></li>
          </ol>
        </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

<section class="content">
				
	<div class="row">
		<div class="col-xs-12">
              <div class="box">
                <div class="box-body table-responsive no-padding">

			<table class="table table-hover">
				<thead>
					<tr>
						<th>Teacher Designation</th>
						<th>Actions</th>
					</tr>
				</thead>   
				<tbody>
					<core:if test="${teacherdesignations.size() > 0}">
						<core:forEach var="teacherdesignation" items="${teacherdesignations}">
							<tr>
								<td>${teacherdesignation.name}</td>
								<td class="center">
									<a class="btn btn-default" href="editteacherdesignation.htm?id=${teacherdesignation.name}">
										<i class="glyphicon glyphicon-edit glyphicon glyphicon-white"></i> 
									</a>
								</td>
							</tr>
						</core:forEach>
					</core:if>
		  		</tbody>
			</table>
		</div>
	</div>

	<div class="seperator_line">
	</div>

	<div class="row">
		<div class="col-md-12">
		<core:choose>		
			<core:when test="${teacherdesignations.size() > 0}">
				<ul class="pager">
				<core:choose>
					<core:when test="${number_of_pages == 0}">
						<li class="active">1</li>
					</core:when>
					<core:otherwise>
					<core:choose>
						<core:when test="${is_first_page}">
						</core:when>
						<core:otherwise>
								<core:if test="${startPageNumber gt 1}">
									<li><a href="javascript:getListByFilter(${startPageNumber - 20})">Previous 20</a></li>
								</core:if>
									<li><a href="javascript:getListByFilter(${selected_staff_page - 1})">Prev</a></li>
						</core:otherwise>
					</core:choose>
					<core:forEach var="i" begin="${startPageNumber}" end="${endPageNumber}">
						<core:choose>
							<core:when test="${selected_staff_page == i}">
								<li><a href="javascript:getListByFilter(${i})">${i}</a></li>
							</core:when>
							<core:otherwise>
								<li><a href="javascript:getListByFilter(${i})">${i}</a></li>
							</core:otherwise>
						</core:choose>
					</core:forEach>
					<core:choose>
						<core:when test="${is_last_page}">
						</core:when>
						<core:otherwise>
							<li><a href="javascript:getListByFilter(${selected_staff_page + 1})">Next</a></li>
						<core:if test="${endPageNumber lt number_of_pages}">
						<li><a href="javascript:getListByFilter(${endPageNumber + 1})">Next</a></li>
					</core:if>
					</core:otherwise>
					</core:choose>
					</core:otherwise>	
					</core:choose>
		        </ul>
			</core:when>
			<core:otherwise>
				<div class="alert alert-block span10">
					<!--<h4 class="alert-heading">Warning!</h4>-->
						<p>No Teacher Designation Found.</p>
				</div>
			</core:otherwise>
		</core:choose>
	</div>	
</div>
</div>
</div>
</div>
<!--footer-->

<script src="js/common.js"></script>
<%@ include file="/WEB-INF/common_views/footer.jsp" %>
