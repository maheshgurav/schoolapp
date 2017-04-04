<select id="classguid" data-rel="chosen">
		<core:forEach var="classInfo" items="${classes}">
			<option value="${classInfo.toString()}">${classInfo.standardOrClass} - ${classInfo.division}</option>
		</core:forEach>
</select>