	 	<div class="panel panel-primary">
            <div class="panel-heading">
              <h3 class="panel-title">Permanent address</h3>
            </div>
            <div class="panel-body">
	            <div class="form-group">
			    <div class="input-group col-md-12">

				<div class="box col-md-4">
				  <div class="form-group">
					<label class="control-label" for="focusedInput">Permanent Address</label>
					<div class="controls">
					  	<form:input class="form-control" id="teacher_permanentAddress_address" path="teacher.permanentAddress.address"/>
					</div>
				  </div>
			  	</div>

				<div class="box col-md-4">
				  <div class="form-group">
					<label class="control-label" for="focusedInput">State</label>
					<div class="controls">
					  	<form:select path="teacher.permanentAddress.state" data-rel="chosen">
							<core:forEach var="stateInfo" items="${states}">
								<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
							</core:forEach>
						</form:select>
					</div>
			  	</div>
			  	</div>
			  
			  <div class="box col-md-4">
				<div class="control-group">
					<label class="control-label" for="focusedInput">Pin code</label>
					<div class="controls">
					  <form:input class="form-control" id="teacher_permanentAddress_pinCodeNumber" path="teacher.permanentAddress.pinCodeNumber"/>
					</div>
			  	</div>
			  </div>
			  </div></div></div></div>

		<div class="panel panel-primary">
            <div class="panel-heading">
              <h3 class="panel-title">Current Address</h3>
            </div>
            <div class="panel-body">
	            <div class="form-group">
			    <div class="input-group col-md-12">

				<div class="box col-md-4">
					<div class="form-group">
					<label class="control-label" for="focusedInput">Current Address</label>
					<div class="controls">
						<form:input class="form-control" id="teacher_currentAddress_address" path="teacher.currentAddress.address"></form:input>
					</div>
					</div>
				</div>

					<div class="box col-md-4">
						<div class="form-group">
				<label class="control-label" for="focusedInput">State</label>
				<div class="controls">
				  	<form:select path="teacher.currentAddress.state" data-rel="chosen">
						<core:forEach var="stateInfo" items="${states}">
							<form:option value="${stateInfo.name}">${stateInfo.name}</form:option>
						</core:forEach>
					</form:select>
				</div>
			  </div>
			  </div>
					<div class="box col-md-4">
						<div class="form-group">
			<label class="control-label" for="focusedInput">Pin code</label>
			<div class="controls">
			  <form:input class="form-control" id="teacher_currentAddress_pinCodeNumber" path="teacher.currentAddress.pinCodeNumber"/>
			</div>
		  </div>
		  </div>
		  </div>
		  </div>
		  </div>
		  </div>