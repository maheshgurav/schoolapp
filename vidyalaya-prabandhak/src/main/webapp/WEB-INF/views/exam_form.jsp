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
	<title>School Management System - Exam</title>

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
    Exam Details
    </h1>
    <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
    <li><a href="#"><i class="fa fa-dashboard"></i> Settings</a></li>
    <li class="active">Add/Edit Exam</li>
    </ol>
    </section>
	<!--
	<div class="col-md-3">
		<a class="btn btn-default pull-right" href="addstudent.htm" role="button"><spring:message code="Label.Add.Student"/></a>		
	</div>
	</div>-->

<section class="content">
<form:form commandName="exam" method="POST" name="exam" id="exam_form">
	<form:hidden path="exam.guid"/>
	<form:hidden path="enteredSubjectAndMarks" id="enteredSubjectAndMarks" value=""/>
	<input type="hidden" id="subjects" value='${subjects}'/>

    	<div class="row">
		<div class="col-md-12">
		 	<div class="box box-primary">
	            <div class="box-body">
					
					<div class="form-group">
			    		
					
						<div class="col-md-4">
					  	<c:choose>
						  	<c:when test="${not empty error_exam_name}">
						  		<div class="form-group-text has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="${error_exam_name}"> 
						  	</c:when>
						  	<c:otherwise>
						  		<div class="form-group-text" data-toggle="tooltip" data-placement="bottom" title="${error_exam_name}">
						  	</c:otherwise>
						</c:choose>
							<label class="control-label" for="focusedInput"><spring:message code="Label.Name"/></label>
							<div class="control">
					  			<form:input class="form-control" id="exam_name" path="exam.name"/>
					  		</div>	
			  			</div>
			  			</div>

						<div class="col-md-4">
					  	<c:choose>
						  	<c:when test="${not empty error_startDateOfExam}">
						  		<div class="form-group-text has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="${error_startDateOfExam}"> 
						  	</c:when>
						  	<c:otherwise>
						  		<div class="form-group-text" data-toggle="tooltip" data-placement="bottom" title="${error_startDateOfExam}">
						  	</c:otherwise>
						</c:choose>
							<label class="control-label" for="focusedInput"><spring:message code="Label.Start.Date.Of.Exam"/></label>
					  		<div class="control">
					  			<form:input class="form-control" id="startDateOfExam" path="startDateOfExam"/>
					  		</div>	
			  			</div>
			  			</div>
			  			
			  			<div class="col-md-4">
					  	<c:choose>
						  	<c:when test="${not empty error_endDateOfExam}">
						  		<div class="form-group-text has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="${error_endDateOfExam}"> 
						  	</c:when>
						  	<c:otherwise>
						  		<div class="form-group-text" data-toggle="tooltip" data-placement="bottom" title="${error_endDateOfExam}">
						  	</c:otherwise>
						</c:choose>
							<label class="control-label" for="focusedInput"><spring:message code="Label.End.Date.Of.Exam"/></label>
							<div class="control">
					  			<form:input class="form-control" id="endDateOfExam" path="endDateOfExam"/>
					  		</div>	
			  			</div>
			  			</div>
			  			
						<div class="col-md-6">
						<c:choose>
						  	<c:when test="${not empty error_examForStandards}">
						  		<div class="form-group has-error red-tooltip" data-toggle="tooltip" data-placement="bottom" title="${error_examForStandards}"> 
						  	</c:when>
						  	<c:otherwise>
						  		<div class="form-group" data-toggle="tooltip" data-placement="bottom" title="${error_examForStandards}">
						  	</c:otherwise>
						</c:choose>
							<label class="control-label" for="selectError1"><spring:message code="Label.Exam.For.Standards"/></label>
							<div class="controls">
							  <form:select style="width:150px;height:500px;" id="exam_standards" path="exam.standards" multiple="multiple" data-rel="chosen-select" class="chosen-select" >
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
						</div>

						<div class="col-md-12">
			  			<div class="form-group">
							<label class="control-label" for="selectError1"><spring:message code="Label.Exam.For.Standards"/></label>
							<div class="controls">
								<table id="subject_table" class="table table-striped table-bordered bootstrap-datatable responsive">
									<tr>
										<th><spring:message code="Header.Subject"/></th>
										<th><spring:message code="Header.Marks"/></th>
										<th><spring:message code="Header.Marks.For.Passing"/></th>
									</tr>
									<core:if test="${subjectAndMarks.size() > 0}">
									   	<core:forEach var="subjectAndMark" items="${subjectAndMarks}" varStatus="i">
										<tr>
											<td>
												<select id="subject_${i.index + 1}">
												   	<core:forEach var="subjects" items="${subjectList}" varStatus="i">
												   	<core:out value="${subjectAndMark.subject}"/>
												   		<core:choose>
													   		<core:when test="${subjectAndMark.subject eq subjects.guid}">
																<option id="${subjects.guid}" selected="selected">${subjects.name}</option>
															</core:when>
															<core:otherwise>
																<option id="${subjects.guid}">${subjects.name}</option>
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
						
			  			<div class="form-group">
							<div class="controls">
								<a class="btn btn-default" onclick="addsubjectrow()"><spring:message code="Label.Add.New.Subject"/></a>
							</div>
						</div>
						</div>
						
                </div>
            </div>
       	</div>
        <!--/span-->
		</div>
		</div>

  <div class="box box-default">
    <div class="box-body">
	  		<div class="form-actions">
					<button class="btn btn-default" onclick="submitExamSettingsFormObject()">Save changes</button>
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

  <script src="js/chosen.jquery.js" type="text/javascript"></script>
  <script src="js/prism.js" type="text/javascript" charset="utf-8"></script>
  
  <style type="text/css" media="all">
    /* fix rtl for demo */
    .chosen-rtl .chosen-drop { left: -9000px; }
  </style>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
  
  <script>
	  $(function() {
	    $( "#startDateOfExam , #endDateOfExam" ).datepicker();
	  });
  </script>
  

<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>