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
	<title>School Management System - Add/edit Exam</title>
	
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
						<a href="settings.htm">Settings</a><span class="divider"></span>
					</li>
					<li>
						<a href="showexams.htm">Exams</a>
					</li>
					<li>
						<b>Exam Details</b>
					</li>
				</ul>
			</div>
			

	<form:form commandName="exam" method="POST" name="exam" id="exam_form">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
					<fieldset class="form-horizontal">
						<form:hidden path="exam.guid"/>
						<form:hidden path="enteredSubjectAndMarks" id="enteredSubjectAndMarks" value=""/>
						<input type="hidden" id="subjects" value='${subjects}'/>
					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Name</label>
					  		<form:input class="form-control" id="exam_name" path="exam.name"/>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">Start Date Of Exam</label>
					  		<form:input class="form-control" id="startDateOfExam" path="startDateOfExam"/>
			  			</div>

					  	<div class="control-group">
							<label class="control-label" for="focusedInput">End Date Of Exam</label>
					  		<form:input class="form-control" id="endDateOfExam" path="endDateOfExam"/>
			  			</div>
			  			
			  			<div class="control-group">
							<label class="control-label" for="selectError1">Exam for standards</label>
							<div class="controls">
							  <form:select style="width:150px;height:500px;" id="exam_standards" path="exam.standards" multiple="multiple" data-rel="chosen">
				  				<core:forEach var="classInfo" items="${classes}">
									<core:choose>
								  	<core:when test="${exam.examForStandards != null and exam.examForStandards.size() > 0}">
										<core:forEach var="standard" items="${exam.examForStandards}">
								  			<core:choose>
									  			<core:when test="${classInfo.standardOrClass == standard}">
						  							<form:option value="${classInfo.standardOrClass}" selected="selected">${classInfo.standardOrClass}</form:option>
						  						</core:when>
						  						<core:otherwise>
						  							<form:option value="${classInfo.standardOrClass}">${classInfo.standardOrClass}</form:option>
						  						</core:otherwise>
					  						</core:choose>
					  					</core:forEach>
					  				</core:when>
					  				<core:otherwise>
					  						<form:option value="${classInfo.standardOrClass}">${classInfo.standardOrClass}</form:option>
					  				</core:otherwise>
					  				</core:choose>
				  				</core:forEach>
							  </form:select>
							</div>
						</div>

			  			<div class="control-group">
							<label class="control-label" for="selectError1">Exam for standards</label>
							<div class="controls">
								<table id="subject_table" class="table table-striped table-bordered bootstrap-datatable responsive">
									<tr>
										<th>Subject</th>
										<th>Marks</th>
										<th>Marks For Passing</th>
									</tr>
									<core:if test="${subjectAndMarks.size() > 0}">
									   	<core:forEach var="subjectAndMark" items="${subjectAndMarks}" varStatus="i">
										<tr>
											<td>
												<select id="subject_${i.index + 1}">
												   	<core:forEach var="subjects" items="${subjectList}" varStatus="i">
												   		<core:choose>
													   		<core:when test="${subjectAndMark.subject eq subjects.code}">
																<option id="${subjects.code}" selected="selected">${subjects.name}</option>
															</core:when>
															<core:otherwise>
																<option id="${subjects.code}">${subjects.name}</option>
															</core:otherwise>
														</core:choose>	
												   	</core:forEach>
												</select>   	
											</td>
											<td><input type="text" id="marks_${i.index + 1}" value="${subjectAndMark.marks}"/></td>
											<td><input type="text" id="marksforpassing_${i.index + 1}" value="${subjectAndMark.marksRequiredForPassing}"/></td>
											</tr>		
					  					</core:forEach>
					  				</core:if>
								</table>
							</div>
						</div>

			  			<div class="control-group">
							<div class="controls">
								<a class="btn btn-info" onclick="addsubjectrow()">Add new subject</a>
							</div>
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
					<button class="btn btn-primary" onclick="submitExamSettingsFormObject()">Save changes</button>
					<button class="btn">Cancel</button>
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
<script src='js/exam.js'></script>

<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>