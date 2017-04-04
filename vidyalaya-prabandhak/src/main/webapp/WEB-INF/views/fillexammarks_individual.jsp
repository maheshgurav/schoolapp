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
	<title>School Management System - Student Examination Marks</title>

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
            Student Examination Marks
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="#"><i class="fa fa-dashboard"></i> Students</a></li>
            <li class="active">Student Examination Marks</li>
          </ol>
        </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

<section class="content">

	<form id="mark_form" method="POST">
		<input type="hidden" id="studentGuid" value="${student_guid}" /> <input
			type="hidden" id="classguid" value="${class_guid}" /> <input
			type="hidden" id="enteredSubjectAndMarks" />

		<div class="row">
			<div class="col-md-12">

				<div class="box box-primary">

					<div class="box-body">
						<div class="form-group">
							

								<div class="control-group">
									<label class="control-label" for="selectError">Exam</label>
									<div class="controls">
										<select id="examguid" data-rel="chosen" style="width: 30%">
											<c:forEach var="examInfo" items="${exams}">
												<option value="${examInfo.guid}">${examInfo.name}</option>
											</c:forEach>
										</select> <a class="btn btn-default"
											href="javascript:getSubjectAndMarks()">Load Subjects And
											Marks</a> <a class="btn btn-default"
											href="javascript:getStudentReportCard('html')">Get Report
											Card (HTML)</a> <a class="btn btn-default"
											href="javascript:getStudentReportCard('pdf')">Get Report
											Card (PDF)</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
		<div class="box box-primary">

			<div class="box-body">
				<div class="form-group">
					
						<div class="control-group">

							<div class="box-content" id="students">
								<table id="markstable"
									class="table table-striped table-bordered">
									<thead>
										<tr>
											<th><spring:message code="Label.Subject.Code" /></th>
											<th><spring:message code="Label.Subject.Name" /></th>
											<th><spring:message code="Label.Marks" /></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
			</div>

    <div class="box box-default">
            <div class="box-body">
	    		<div class="form-actions">
				<a class="btn btn-default" href="javascript:saveSubjectAndMarks()">Save
					changes</a> <a class="btn">Cancel</a>
			</div>
		</div>
	</div>
</div>
</form>
</div>
<!--/span-->


</div>
<!--/#content.span10-->
</div>
<!--/fluid-row-->

<script src='js/common.js'></script>
<script src='js/exam.js'></script>
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp"%>