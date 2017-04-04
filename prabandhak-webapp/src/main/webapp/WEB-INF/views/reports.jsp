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
<title>School Management System - Teacher add/edit</title>
<script>
 $(function() {
   $( "#birthDate,#admissionDate" ).datepicker({
     	changeMonth: true,
     	changeYear: true
   });
 });
</script>
	
<!-- topbar starts -->
<%@ include file="/WEB-INF/common_views/header.jsp" %>
<!-- topbar ends -->
		
<div class="container-fluid">
<div class="row-fluid">

<!-- left menu starts -->
<%@ include file="/WEB-INF/common_views/menu.jsp" %>			
<!-- left menu ends -->

<div id="content" class="col-lg-10 col-sm-10">

<!-- breadcrum starts -->
	<div>
		<ul class="breadcrumb">
			<li><a href="#">Home</a> <span class="divider"></span></li>
			<li><a href="#">Reports</a></li>
		</ul>
	</div>
<!-- breadcrum ends -->			


    <div class="row">
        <div class="box col-md-3">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Reports for class</h2>
                </div>
                <div class="box-content">
                	<%@ include file="/WEB-INF/common_views/classes.jsp" %>
                </div>
            </div>
        </div>
        <!--/span-->
        <div class="box col-md-3">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Gender</h2>
                </div>
                <div class="box-content">
                	<%@ include file="/WEB-INF/common_views/genders.jsp" %>
                </div>
            </div>
        </div>
        
       <div class="box col-md-3">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Report type</h2>
                </div>
                <div class="box-content">
                	<select id="reportFormat" data-rel="chosen">
                		<option>Html</option>
                		<option>Pdf</option>
                	</select>
                </div>
            </div>
        </div>
        <!--/span-->
    </div><!--/row-->

<div class="row">
	<div class="box col-md-6">
      <div class="box-inner">
			<div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Class wise students report</h2>
            </div>
          <div class="box-content">
             <a href="javascript:getReport('0')">Get report</a>
          </div>
       </div>
    </div>
	
	<div class="box col-md-6">
      <div class="box-inner">
			<div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Caste wise students</h2>
                </div>
          <div class="box-content">
            <div class="control-group">
				<select id="casteguid" data-rel="chosen">
		  				<core:forEach var="castInfo" items="${castes}">
		  					<option value="${castInfo.toString()}">${castInfo.toString()}</option>
		  				</core:forEach>
				</select>
				<a href="javascript:getReport('3')">Get report</a>
			</div>
          </div>
       </div>
    </div>


	<div class="box col-md-6">
      <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-leaf"></i> Scholarship wise report</h2>
            </div>
          <div class="box-content">
          	</legend>
          	<div class="control-group">
				<select id="scholarshipguid" data-rel="chosen">
						<core:forEach var="scholarshiptypeInfo" items="${scholarshiptypes}">
							<option value="${scholarshiptypeInfo.name}">${scholarshiptypeInfo.name}</option>
						</core:forEach>
					</select>
				<a href="javascript:getReport('6')">Get report</a>
			</div>
          </div>
       </div>
    </div>


	<div class="box col-md-6">
      <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-leaf"></i> Exam wise report</h2>
            </div>
          <div class="box-content">
          	<div class="control-group">
				<select id="examguid" data-rel="chosen">
					<option value="">Select exam</option>
					<forEach var="examInfo" items="${exams}">
						<option value="${examInfo.guid}">${examInfo.name}</option>
					</forEach>
				</select>
				<a href="javascript:getReport('6')">Get report</a>
			</div>
          </div>
       </div>
    </div>


	<div class="box col-md-6">
      <div class="box-inner">
			<div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> ICards</h2>
            </div>
          <div class="box-content">
             <a href="javascript:getReport('8')">Get report</a>
          </div>
       </div>
    </div>
    
    <div class="box col-md-6">
      <div class="box-inner">
			<div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Category wise summary report</h2>
            </div>
          <div class="box-content">
			<select style="width:150px;" id="category" data-rel="chosen">
	  			<core:forEach var="categoryInfo" items="${categories}">
	  				<option value="${categoryInfo.toString()}" id="${categoryInfo.guid}">${categoryInfo.toString()}</option>
	  			</core:forEach>
			</select>
            <a href="javascript:getReport('1')">Get report</a>
          </div>
       </div>
    </div>

	<div class="box col-md-6">
      <div class="box-inner">
			<div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> GR report</h2>
            </div>
          <div class="box-content">
             <a href="javascript:getReport('9')">Get report</a>
          </div>
       </div>
    </div>


	<div class="box col-md-6">
      <div class="box-inner">
			<div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Teacher details report</h2>
            </div>
          <div class="box-content">
             <a href="javascript:getReport('0')">Get report</a>
          </div>
       </div>
    </div>

	<div class="box col-md-6">
      <div class="box-inner">
			<div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Student Details For Attendance Sheet</h2>
            </div>
          <div class="box-content">
             <a href="javascript:getReport('10')">Get report</a>
          </div>
       </div>
    </div>
    
	<div class="box col-md-6">
      <div class="box-inner">
			<div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Blank Attendance Sheet</h2>
            </div>
          <div class="box-content">
             <a href="javascript:getReport('11')">Get report</a>
          </div>
       </div>
    </div>

	<div class="box col-md-6">
      <div class="box-inner">
			<div class="box-header well" data-original-title="">
                    <h2><i class="glyphicon glyphicon-leaf"></i> Blank Attendance Summary Of Month</h2>
            </div>
          <div class="box-content">
             <a href="javascript:getReport('12')">Get report</a>
          </div>
       </div>
    </div>

</div>


</div><!--/#content.span10-->
</div><!--/fluid-row-->

	<script src='js/common.js'></script>
	<script src='js/reports.js'></script>
<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>