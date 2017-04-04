			<legend>Permanent address</legend>
			  <div class="control-group">
				<label class="control-label" for="focusedInput">Address line 1</label>
				<div class="controls">
				  	<form:input class="form-control" id="permanentAddressDetails_addressLineFirst" path="permanentAddressDetails.addressLineFirst"/>
				</div>
			  </div>
			  <div class="control-group">
				<label class="control-label" for="focusedInput">Address line 2</label>
				<div class="controls">
				  <form:input class="form-control" id="permanentAddressDetails_addressLineSecond" path="permanentAddressDetails.addressLineSecond"/>
				</div>
			  </div>
			  <!--
			  <div class="control-group">
				<label class="control-label" for="focusedInput">City</label>
				<div class="controls">
				  <form:input class="form-control" id="permanentAddressDetails_city" path="permanentAddressDetails.city"/>
				</div>
			  </div>
			  -->
			  <div class="control-group">
				<label class="control-label" for="focusedInput">State</label>
				<div class="controls">
				  	<form:select path="permanentAddressDetails.state" data-rel="chosen">
						<core:forEach var="stateInfo" items="${states}">
							<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
						</core:forEach>
					</form:select>
				</div>
			  </div>
			  <div class="control-group">
				<label class="control-label" for="focusedInput">Pin code</label>
				<div class="controls">
				  <form:input class="form-control" id="permanentAddressDetails_pinCodeNumber" path="permanentAddressDetails.pinCodeNumber"/>
				</div>
			  </div>

		<legend>Current address</legend>
		  <div class="control-group">
			<label class="control-label" for="focusedInput">Address line 1</label>
			<div class="controls">
			  <form:input class="form-control" id="currentAddressDetails_addressLineFirst" path="currentAddressDetails.addressLineFirst"/>
			</div>
		  </div>
		  <div class="control-group">
			<label class="control-label" for="focusedInput">Address line 2</label>
			<div class="controls">
			  <form:input class="form-control" id="currentAddressDetails_addressLineSecond" path="currentAddressDetails.addressLineSecond"/>
			</div>
		  </div>
		  <!--
		  <div class="control-group">
			<label class="control-label" for="focusedInput">City</label>
			<div class="controls">
			  <form:input class="form-control" id="currentAddressDetails_city" path="currentAddressDetails.city"/>
			</div>
		  </div>
		  -->
		  <div class="control-group">
			<label class="control-label" for="focusedInput">State</label>
			<div class="controls">
			  	<form:select path="currentAddressDetails.state" data-rel="chosen">
					<core:forEach var="stateInfo" items="${states}">
						<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
					</core:forEach>
				</form:select>
			</div>
		  </div>
		  <div class="control-group">
			<label class="control-label" for="focusedInput">Pin code</label>
			<div class="controls">
			  <form:input class="form-control" id="currentAddressDetails_pinCodeNumber" path="currentAddressDetails.pinCodeNumber"/>
			</div>
		  </div>