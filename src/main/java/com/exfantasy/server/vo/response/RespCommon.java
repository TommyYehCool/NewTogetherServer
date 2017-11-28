package com.exfantasy.server.vo.response;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.exception.OperationException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <pre>
 * 統一回應前端格式 
 * </pre>
 * 
 * @author tommy.feng
 *
 */
@ApiModel(description = "操作回應")
public class RespCommon {

	@ApiModelProperty(notes = "回應代碼")
	private int code;
	
	@ApiModelProperty(notes = "回應訊息")
	private String message;
	
	@ApiModelProperty(notes = "回應資料")
	private Object data;

	public RespCommon(ResultCode resultCode, Object data) {
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
		this.data = data;
	}
	
	public RespCommon(ResultCode resultCode) {
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
	}
	
	public RespCommon(OperationException ex) {
		this.code = ex.getErrorCode();
		this.message = ex.getErrorMessage();
		this.data = ex.getData();
	}

	public int getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}

	public Object getData() {
		return this.data;
	}
}
