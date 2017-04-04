package com.samarthsoft.prabandhak.enums;

public enum CrudOperations {
	INSERT(1), UPDATE(2), INSERTORUPDATE(3), DELETE(4);
	private Integer operationId;

	public Integer getOperationId() {
		return operationId;
	}

	private CrudOperations(Integer operationId) {
		this.operationId = operationId;
	}

}
