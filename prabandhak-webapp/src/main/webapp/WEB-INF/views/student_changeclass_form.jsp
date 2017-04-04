<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/views/include.jsp" %>
<!--<link rel="stylesheet" type="text/css" href="css/style.css">-->
<html>
<body>
	<form:form commandName="changeclass" action="changeclassordivisionofstudent.htm" method="POST" name="changeclass">
	<form:hidden path="studentGuid"/>
	<table>
	<tr>
		<td>
			<label>Student name : </label>
		</td>
		<td>
			${changeclass.studentName}
		</td>
	</tr>
	<tr>
		<td>
			<label>Current class : </label>
		</td>
		<td>
			${changeclass.currentClass}
		</td>
	</tr>
	<tr>
		<td>
			<label>Changed class : </label>
		</td>
		<td>
		<form:select id="classguid" path="changedClass">
			<core:forEach var="classInfo" items="${classes}">
				<form:option value="${classInfo.guid}">${classInfo.standardOrClass} - ${classInfo.division}</form:option>
			</core:forEach>
		</form:select>	
		</td>
	</tr>
	<tr>
		<td>
			<label>Date : </label>
		</td>
		<td>
			<form:input path="dateOfChange" />
		</td>
	</tr>
	<tr>
		<td>
			<label>&nbsp;</label>
		</td>
		<td>
			<form:button name="changestudentclass" value="Login">CHANGE CLASS/DIVISION</form:button>
	</td>
	</tr>
	</table>
	</form:form>
</body>
</html>