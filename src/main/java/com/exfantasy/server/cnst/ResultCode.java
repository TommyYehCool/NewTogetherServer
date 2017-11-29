package com.exfantasy.server.cnst;

/**
 * <pre>
 * 定義操作結果代碼
 * </pre>
 * 
 * @author tommy.feng
 *
 */
public enum ResultCode {

	/** Code: 0, Message: 成功 */
	SUCCESS(0, "Succeed"),

	/** Code: 1001, Message: 格式錯誤 */
	INVALID_FORMAT(1001, "Invalid format"),
	
	/** Code: 2001, Message: 帳號已存在 */
	USER_EXISTED(2001, "User existed"),
	
	/** Code: 2002, Message: 帳號不存在 */
	USER_NOT_FOUND(2002, "User not found"), 
	
	/** Code: 3001, Message: 活動不存在 */
	ACTIVITY_NOT_FOUND(3001, "Activity not found"), 
	
	/** Code: 3002, Message: 不能參加你建立的活動 */
	CANNOT_JOIN_ACTITVITY_THAT_USER_CREATED(3002, "Cannot join activity that you created"), 
	
	/** Code: 3003, Message: 不能參加已參加的活動  */
	CAONNOT_JOIN_ACTIVITY_THAT_ALREADY_JOINED(3003, "Cannot join activity that already joined"),
	
	/** Code: 4001, Message: 發送 Email 失敗 */
	SEND_MAIL_FAILED(4001, "Send mail failed"), 
	
	/** Code: 4444, Message: 無權限操作 */
	ACCESS_DENIED(4444, "Access is denied"),
	
	/** Code: 9999, Message: 系統發生錯誤 */
	SYSTEM_EXCEPTION(9999, ""),  
	
	;
	
	private final int code;
	private final String message;
	
	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public static ResultCode convert(int code) {
		for (ResultCode e : ResultCode.values()) {
			if (e.getCode() == code) {
				return e;
			}
		}
		return null;
	}
}
