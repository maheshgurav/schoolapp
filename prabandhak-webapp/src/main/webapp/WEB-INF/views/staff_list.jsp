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
	<title>School Management System - Supporting staff</title>
		<!-- topbar starts -->
			<%@ include file="/WEB-INF/common_views/header.jsp" %>
		<!-- topbar ends -->

		<div class="container-fluid">
			<div class="row-fluid">
				
			<!-- left menu starts -->
			<%@ include file="/WEB-INF/common_views/menu.jsp" %>			
			<!-- left menu ends -->
			
			<div id="content" class="col-lg-10 col-sm-10">
			<div class="row">
		    <div class="box col-md-12">
		    <div class="box-inner">
    		
    		<div class="box-header well" data-original-title="">
    		<h2><i class="glyphicon glyphicon-info-sign"></i> Supporting staff</h2>
                <div class="box-icon">
						<a class="btn btn-info" style="width:100%;height:25px;padding:1px;" href="addsupportingstaffmember.htm">
							Add new member
						</a>
                </div>
			</div>
					
			<div class="box-content">
				  	<!--Filters
					  	<input type="text" id="filter_1" value="${prn}" name="prn" placeholder="PRN" style="text-align: left; width:10%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_2" value="${rollnumber}" name="rollnumber" placeholder="Roll" style="text-align: left; width:5%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_3" value="${name}" name="name" placeholder="Name" style="text-align: left; width:20%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_4" value="${birthdate}" name="birthdate" placeholder="Birth date" style="text-align: left; width:15%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_5" value="${caste}" name="caste" placeholder="Caste" style="text-align: left; width:15%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_6" value="${scholarshiptype}" name="scholarshiptype" placeholder="Scholarship type" style="text-align: left; width:15%;" onblur="getListByFilter()">
					  	<input type="text" id="filter_7" value="${classanddivision}" name="classanddivision" placeholder="Class" style="text-align: left; width:10%;" onblur="getListByFilter()">
				  	Filters ended-->
					<table class="table table-striped table-bordered bootstrap-datatable">
						  <thead>
							  <tr>
								  <th>Name</th>
								  <th>Birth date</th>
								  <th>Designation</th>
								  <th>Qualification</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody>
						  <core:if test="${staffs.size() > 0}">
						  <core:forEach var="staff" items="${staffs}">
						  <tr>
								<td>${staff.name.toString()}</td>
								<td>${staff.birthDate}</td>
								<td class="center">${staff.supportStaff.qualification}</td>
								<td class="center">${staff.supportStaff.designation}</td>
								<td class="center">
									<!--<span class="label label-success">Active</span>-->
								</td>
								<td class="center">
									<!--<a class="btn btn-success" href="viewstudent.htm?id=${student.editId}">
										<i class="icon-zoom-in icon-white"></i>  
										View                                            
									</a>-->
									<a class="btn btn-info" href="editsupportingstaffmember.htm?id=${staff.editId}">
										<i class="icon-edit icon-white"></i>  
										Edit                                            
									</a>
									<!--<a class="btn btn-info" href="leavingcertificate.htm?id=${student.editId}">
										<i class="icon-edit icon-white"></i> 
										LC
									</a>
									<a class="btn btn-info" href="javascript:getBonafide('${student.editId}')">
										<i class="icon-edit icon-white"></i> 
										BC
									</a>
									<a class="btn btn-danger" href="removestudentfromcatalogue.htm?id=${student.editId}">
										<i class="icon-trash icon-white"></i> 
										Del
									</a>
									<a class="btn btn-info" href="changeclassordivisionofstudent.htm?id=${student.editId}">
										<i class="icon-edit icon-white"></i> 
										Change class
									</a>-->
								</td>
							</tr>
							</core:forEach>
							</core:if>
		  				</tbody>
					  </table>
			<core:choose>
	  <core:when test="${staff.size() > 0}">
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
				<p>No supporting staff member found.</p>
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