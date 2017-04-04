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
	<title>School Management System - Student add/edit</title>	
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
					<li>
						<a href="dashboard.htm">Home</a> <span class="divider"></span>
					</li>
					<li>
						<b>Attendance</b>
					</li>
				</ul>
			</div>
			<!-- breadcrum ends -->		


				<div class="row">
				        <div class="box col-md-6">
				            <div class="box-inner">
				                <div class="box-content">
								<fieldset class="form-horizontal">
								  <legend>Attendance for class</legend>
									<div class="control-group">
										    <select id="classguid" data-rel="chosen" class="form-control" style="width:30%">
									  			<option value="0">Select class</option> 
									  			<core:forEach var="classInfo" items="${classes}">
									  				<option value="${classInfo.toString()}">${classInfo.toString()}</option>
									  			</core:forEach>
											</select>
									</div>
				</fieldset>
                    </div>
                </div>
            </div>	


				        <div class="box col-md-6">
				            <div class="box-inner">
				                <div class="box-content">
								<fieldset class="form-horizontal">
								  <legend>Date</legend>
				    
							  <div class="control-group">
								 <div class="controls">
							  	<input path="date" id="date" class="form-control"/>
							  </div>
							  </div>

				</fieldset>
                    </div>
                </div>
            </div>	


				        <div class="box col-md-6">
				            <div class="box-inner">
				                <div class="box-content">
								<fieldset class="form-horizontal">
								  <legend>Check Attendance</legend>
				    
			            <div class="box-content">
							<div class="form-actions">
								<a class="btn btn-primary" href="javascript:getAttendance()">Show attendance</a>
							</div>
						</div>	

				</fieldset>
                    </div>
                </div>
            </div>	

				        <div class="box col-md-6">
				            <div class="box-inner">
				                <div class="box-content">
								<fieldset class="form-horizontal">
								  <legend>Absent students</legend>
				    
							  <div class="control-group">
								<div class="controls">
								  <input path="absentRollNumbers" class="form-control" id="absentRollNumbers" 
								  onkeypress="return checkAndSelectCheckBox(this,event);" onblur="addCommaAtLast()"/>
								</div>
							  </div>

				</fieldset>
                    </div>
                </div>
            </div>								  


				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
								<fieldset class="form-horizontal">
								  <legend>Absent students</legend>
				    
					<div class="box-content">
						<table id="attendancetable" class="table table-striped table-bordered">
						  <thead>
							  <tr>
								  <th>Roll Number</th>
								  <th>Student Name</th>
								  <th>Present</th>
							  </tr>
						  </thead>
						  <tbody>   
						  </tbody>
					  </table>            
					</div>

				</fieldset>
                    </div>
                </div>
            </div>								  

					
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-content">
				<div class="form-actions">
					<a class="btn btn-primary" href="javascript:saveAttendance()">Save</a>
					<a class="btn btn-primary">Cancel</a>
				</div>
			</div>	
		</div>
	</div>	
</div><!--/span-->

</div><!--/#content.span10-->
</div><!--/fluid-row-->

		<div class="modal hide fade" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Settings</h3>
			</div>
			<div class="modal-body">
				<p>Here settings can be configured...</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary">Save changes</a>
			</div>
		</div>

<script src='js/common.js'></script>
<script src='js/student.js'></script>
<script src='js/attendance.js'></script>


<!--footer-->
<%@ include file="/WEB-INF/common_views/footer.jsp" %>