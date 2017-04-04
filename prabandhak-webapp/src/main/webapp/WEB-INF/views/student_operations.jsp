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
	<title>School Management System - Student operations</title>
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
						<a href="dashboard.jsp">Home</a> <span class="divider"></span>
					</li>
					<li>
						<b>Operations</b>
					</li>
				</ul>
			</div>

<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
    		
	    	<div class="box-header well" data-original-title="">
	    		<h2><i class="glyphicon glyphicon-leaf"></i> Student Operations</h2>
			</div>
						
			<div class="box-content">
					<a class="btn btn-primary" href="opchangeclass.htm">Change Standard Or Division</a>
					<a class="btn btn-primary" href="opmakealumnis.htm">Mark Alumnis</a>
			</div>
		</div>
	</div>
	</div>
</div>
</div>
</div>

</div>


</div>

<!--footer-->


<script language="javascript" type="text/javascript" src="js/settings.js"></script>
<%@ include file="/WEB-INF/common_views/footer.jsp" %>