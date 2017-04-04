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
	<title>School Management System - Students</title>
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
						<b>Students</b>
					</li>
				</ul>
			</div>

			<div class="row">
		    <div class="box col-md-12">
		    <div class="box-inner">
    		
    		<div class="box-header well" data-original-title="">
    		<h2><i class="glyphicon glyphicon-leaf"></i> Students</h2>
                <div class="box-icon">
						<a class="btn btn-info" style="width:100%;height:25px;padding:1px;" href="addstudent.htm">
							Add new student
						</a>
                </div>
			</div>
					
			<div class="box-content">
				  	<!--Filters-->
					  	<input type="text" id="filter_1" value="${prn}" name="prn" placeholder="PRN" style="text-align: left; width:10%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_2" value="${rollnumber}" name="rollnumber" placeholder="Roll" style="text-align: left; width:5%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_3" value="${name}" name="name" placeholder="Name" style="text-align: left; width:20%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_4" value="${birthdate}" name="birthdate" placeholder="Birth date" style="text-align: left; width:15%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_5" value="${caste}" name="caste" placeholder="Caste" style="text-align: left; width:15%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_6" value="${scholarshiptype}" name="scholarshiptype" placeholder="Scholarship type" style="text-align: left; width:15%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_7" value="${classanddivision}" name="classanddivision" placeholder="Class" style="text-align: left; width:10%;" onblur="getListByFilter()">
				  	<!--Filters ended-->
			<table class="table table-striped table-bordered bootstrap-datatable responsive">
				<thead>
					<tr>
						<th>PRN</th>
						<th>Roll</th>
						<th>Name</th>
						<th>Birth Date</th>
						<th>Caste</th>
						<th>Scholaship Type</th>
						<th>Class</th>
						<th>Actions</th>
					</tr>
				</thead>   
				<tbody>
					<core:if test="${students.size() > 0}">
						<core:forEach var="student" items="${students}">
							<tr>
								<td>${student.student.PRN}</td>
								<td class="center">${student.student.rollNumber}</td>
								<td class="center"><a href="editstudent.htm?id=${student.editId}">${student.nameOfStudent.toString()}</a></td>
								<td class="center">${student.birthDate}</td>
								<td class="center">${student.student.caste}</td>
								<td class="center">${student.student.scholarshipType}</td>
								<td class="center">${student.student.standard} - ${student.student.division}</td>
								<td class="center">
									<a class="btn btn-info" href="javascript:getBonafide('${student.editId}')">
										<i class="glyphicon glyphicon-white">B</i> 
									</a>
									<a class="btn btn-info" href="leavingcertificate.htm?id=${student.editId}">
										<i class="glyphicon glyphicon-white">LC</i>
									</a>

									<a class="btn btn-info" href="fillindividualstudentmarks.htm?id=${student.editId}">
										<i class="glyphicon glyphicon-white">E</i> 
									</a>

									<a class="btn btn-info" href="studentdashboard.htm?id=${student.editId}">
										<i class="glyphicon glyphicon-dashboard glyphicon glyphicon-white"></i> 
									</a>
									
									<a class="btn btn-danger" href="removestudentfromcatalogue.htm?id=${student.editId}">
										<i class="glyphicon glyphicon-minus-sign glyphicon glyphicon-white"></i> 
									</a>
								</td>
							</tr>
						</core:forEach>
		  			</tbody>
		  			</core:if>
				</table>
	  <core:choose>
	  <core:when test="${students.size() > 0}">
		<ul class="pagination pagination-centered">
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
				<p>No students found.</p>
		</div>
	</core:otherwise>
</core:choose>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<!--footer-->

<script src="js/student.js"></script>
<%@ include file="/WEB-INF/common_views/footer.jsp" %>