<select id="examguid">
<option value="">Select exam</option>
	<forEach var="examInfo" items="${exams}">
		<option value="${examInfo.guid}">${examInfo.name}</option>
	</forEach>
</select>
