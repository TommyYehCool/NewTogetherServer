package com.exfantasy.server.exception;

import com.exfantasy.server.cnst.ResultCode;

/**
 * <pre>
 * 自訂操作系統產生的例外
 * </pre>
 * 
 * @author tommy.feng
 *
 */
public class OperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ResultCode resultCode;
	private String errorMessage;
	private Object data;
	
	public OperationException(ResultCode resultCode) {
		this.resultCode = resultCode;
		this.errorMessage = resultCode.getMessage();
	}

	public OperationException(ResultCode resultCode, Exception e) {
		this.resultCode = resultCode;
		this.errorMessage = resultCode.getMessage() + ", error-msg: " + e.getMessage();
	}
	
	public OperationException(ResultCode resultCode, Object data) {
		this.resultCode = resultCode;
		this.errorMessage = resultCode.getMessage();
		this.data = data;
	}
	
	public ResultCode getResultCode() {
		return resultCode;
	}

	public int getErrorCode() {
		return resultCode.getCode();
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public Object getData() {
		return data;
	}
	
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("result-code: ").append(resultCode).append(", ")
			  .append("error-code: ").append(resultCode.getCode()).append(", ")
			  .append("error-msg: ").append(errorMessage).append(", ")
			  .append("data: ").append(data);
		return buffer.toString();
	}
}
