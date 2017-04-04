<!DOCTYPE html>
<%@ page session="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<script type="text/javascript">
	function test(date){
		location.replace("http://localhost:8080/prabandhak-webapp/events.htm?date="+date);
	}
</script>
	<meta charset="utf-8">
	<title>School Management System - Settings</title>
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
						<b>Calendar</b>
					</li>
				</ul>
			</div>

			<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
    		<div class="box-content">

<table class="fc-header" style="width:100%">
<tbody>
<tr>
	<td class="fc-header-left">
	<span class="fc-button fc-button-prev fc-state-default fc-corner-left">
		<a class="fc-icon fc-icon-left-single-arrow" href="calendar.htm?month=${currentMonth - 1}&year=${currentYear}"></a>
	</span>
	<span class="fc-button fc-button-next fc-state-default fc-corner-right">
		<a class="fc-icon fc-icon-right-single-arrow" href="calendar.htm?month=${currentMonth + 1}&year=${currentYear}"></a>
	</span>
	<span class="fc-header-space"></span>
</td>
<td class="fc-header-center">
	<span class="fc-header-title"><h2>${month}</h2></span>
</td>
<td class="fc-header-right"></td>
</tr>
</tbody>
</table>

<table class="fc-border-separate" style="width:100%" cellspacing="0">
	<thead>
		<tr class="fc-first fc-last">
		<th class="fc-day-header fc-sun fc-widget-header fc-first" style="width: 141px;">Sun</th><th class="fc-day-header fc-mon fc-widget-header" style="width: 141px;">Mon</th><th class="fc-day-header fc-tue fc-widget-header" style="width: 141px;">Tue</th><th class="fc-day-header fc-wed fc-widget-header" style="width: 141px;">Wed</th><th class="fc-day-header fc-thu fc-widget-header" style="width: 141px;">Thu</th><th class="fc-day-header fc-fri fc-widget-header" style="width: 141px;">Fri</th><th class="fc-day-header fc-sat fc-widget-header fc-last" style="width: 141px;">Sat</th></tr></thead>
	<tbody>
	<core:forEach var="week" begin="1" end="${totalWeeks}" step="1">
	<tr class="fc-week">
	<core:forEach items="${dayAndDateValues}" var="dayanddate">
			<core:if test="${dayanddate.week == week}">
				<td class="fc-day fc-${dayanddate.day} fc-widget-content fc-past blue-button" data-date="${dayanddate.date}" onclick="test('${dayanddate.date}')">
					<div style="min-height: 118px;">
						<div class="fc-day-number">${dayanddate.dayNumber}</div>
							<div class="fc-day-content">
								<div style="position: relative; height: 20px;">
								<core:if test="${dayanddate.events.size() > 0}">
									<core:forEach items="${dayanddate.events}" var="eventObj"> 
										<span class="label label-success">${eventObj}</span><br/>
									</core:forEach>	
								</core:if>
							</div>
						</div>
					</div>
				</td>
			</core:if>
	</core:forEach>
	</tr>
	</core:forEach>
	
	</tbody>
	</table>
		</div>
	</div>
</div>
</div>
</div>
</div>
</div>
</div>
<!--footer-->

<%@ include file="/WEB-INF/common_views/footer.jsp" %>