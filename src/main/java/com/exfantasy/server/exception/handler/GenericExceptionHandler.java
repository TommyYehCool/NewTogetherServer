package com.exfantasy.server.exception.handler;

import java.nio.file.AccessDeniedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.exception.OperationException;
import com.exfantasy.server.vo.response.RespCommon;

/**
 * <pre>
 * 統一處理系統例外用
 * </pre>
 * 
 * @author tommy.feng
 *
 */
@ControllerAdvice
public class GenericExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GenericExceptionHandler.class);
	
	@ExceptionHandler(OperationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RespCommon handleOperationException(OperationException ex) {
		logger.error("OperationException raised, error-code: <{}>, error-msg: <{}>", ex.getErrorCode(), ex.getErrorMessage());
		return new RespCommon(ex);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public RespCommon handleAccessDeniedException(AccessDeniedException ex) {
		logger.error("AccessDeniedException raised", ex);
		return new RespCommon(ResultCode.ACCESS_DENIED);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RespCommon handleAllException(Exception ex) {
		logger.error("Exception raised", ex);
		return new RespCommon(ResultCode.SYSTEM_EXCEPTION, ex.getMessage());
	}
}
