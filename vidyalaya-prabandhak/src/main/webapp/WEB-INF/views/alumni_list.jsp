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
	<title>School Management System - Alumni</title>

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
            Alumni
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li class="active">Alumni</li>
            <li><a class="badge bg-light-blue" href="addalumni.htm"><i class="fa fa-plus"></i> Add Alumni</a></li>
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
			<input type="text" id="filter_1" value="${prn}" name="prn" placeholder="PRN" style="text-align: left; width:10%;" onblur="getListByFilter()">
		  	<input type="text" id="filter_2" value="${rollnumber}" name="rollnumber" placeholder="Roll" style="text-align: left; width:5%;" onblur="getListByFilter()">
		  	<input type="text" id="filter_3" value="${name}" name="name" placeholder="Name" style="text-align: left; width:20%;" onblur="getListByFilter()">
		  	<input type="text" id="filter_4" value="${birthdate}" name="birthdate" placeholder="Birth date" style="text-align: left; width:15%;" onblur="getListByFilter()">
		  	<input type="text" id="filter_5" value="${caste}" name="caste" placeholder="Caste" style="text-align: left; width:15%;" onblur="getListByFilter()">
		  	<input type="text" id="filter_6" value="${scholarshipName}" name="scholarshiptype" placeholder="Scholarship type" style="text-align: left; width:15%;" onblur="getListByFilter()">
		  	<input type="text" id="filter_7" value="${classanddivision}" name="classanddivision" placeholder="Class" style="text-align: left; width:10%;" onblur="getListByFilter()">
		</div>
	</div>

	<div class="seperator_line">
	</div>
	
	<div class="row">
		<div class="col-xs-12">
              <div class="box">
                <div class="box-body table-responsive no-padding">
                  <table class="table table-hover">
				<thead>
				<tr>
						<th><spring:message code="Header.PRN"/></th>
						<th><spring:message code="Header.Roll.Number"/></th>
						<th><spring:message code="Header.Name"/></th>
						<th><spring:message code="Header.BirthDate"/></th>
						<th><spring:message code="Header.Caste"/></th>
						<th><spring:message code="Header.Class"/></th>
						<th><spring:message code="Header.Actions"/></th>
						
				</tr>
				</thead>
				<tbody>
					<core:if test="${alumnis.size() > 0}">
						<core:forEach var="student" items="${alumnis}">
							<tr>
								<td>${student.alumni.PRN}</td>
								<td class="center">${student.alumni.rollNumber}</td>
								<td class="center"><a href="editalumni.htm?id=${student.alumni.guid}">${student.alumni.name.toString()}</a></td>
								<td class="center">${student.birthDate}</td>
								<td class="center">${student.alumni.caste}</td>
								<td class="center">${student.alumni.standard}</td>
								<td class="center">
									<a class="btn btn-default" href="editalumni.htm?id=${student.alumni.guid}">
										<i class="glyphicon glyphicon-pencil glyphicon glyphicon-white"></i>
									</a>

									<a class="btn btn-default" href="editalumnicontactdetails.htm?id=${student.alumni.guid}">
										<i class="glyphicon glyphicon-envelope glyphicon glyphicon-white"></i>
									</a>

<%-- 									<a class="btn btn-default" href="setsubjects.htm?id=${student.alumni.guid}">
										<i class="glyphicon glyphicon-book glyphicon glyphicon-white"></i>
									</a> --%>

									<a class="btn btn-default" href="editalumniparentdetails.htm?id=${student.alumni.guid}">
										<i class="glyphicon glyphicon-edit glyphicon glyphicon-white"></i>
									</a>

									<a class="btn btn-default" href="javascript:getBonafide('${student.alumni.guid}')">
										<i class="glyphicon glyphicon-white">B</i> 
									</a>
									
									<a class="btn btn-default" href="leavingcertificate.htm?id=${student.alumni.guid}">
										<i class="glyphicon glyphicon-white">LC</i>
									</a>

									<a class="btn btn-default" href="fillindividualstudentmarks.htm?id=${student.alumni.guid}">
										<i class="glyphicon glyphicon-white">E</i> 
									</a>
									
									<a class="btn btn-default" href="removestudentfromcatalogue.htm?id=${student.alumni.guid}">
										<i class="glyphicon glyphicon-trash glyphicon glyphicon-white"></i> 
									</a>

									<a class="btn btn-default" href="changeclassordivisionofstudent.htm?id=${student.alumni.guid}">
										<i class="glyhicon glyphicon-circle-arrow-right glyphicon glyphicon-white"></i> 
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
			<core:when test="${alumnis.size() > 0}">
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
									<li><a href="javascript:getListByFilter(${selected_student_page - 1})">Prev</a></li>
						</core:otherwise>
					</core:choose>
					<core:forEach var="i" begin="${startPageNumber}" end="${endPageNumber}">
						<core:choose>
							<core:when test="${selected_student_page == i}">
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
							<li><a href="javascript:getListByFilter(${selected_student_page + 1})">Next</a></li>
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
						<p><spring:message code="Error.No.Students.Found"/></p>
				</div>
			</core:otherwise>
		</core:choose>
	</div>	
</div>
</div>
</div>
</div>
<!--footer-->

<script src="js/student.js"></script>
<script src="js/common.js"></script>
<%@ include file="/WEB-INF/common_views/footer.jsp" %>
