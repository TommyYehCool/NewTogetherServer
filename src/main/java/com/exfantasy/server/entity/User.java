package com.exfantasy.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
	/**
	 * 使用者 ID
	 */
	@Id
	@ApiModelProperty(notes = "使用者 ID", required = false)
	private Long id;
	/**
	 * Email
	 */
	@ApiModelProperty(notes = "Email", required = true)
	private String email;
	/**
	 * 密碼
	 */
	@ApiModelProperty(notes = "密碼", required = true)
	private String password;
	/**
	 * 名稱
	 */
	@ApiModelProperty(notes = "名稱", required = true)
	private String name;
}