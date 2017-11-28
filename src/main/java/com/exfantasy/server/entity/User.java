package com.exfantasy.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private Long id;
	/**
	 * Email
	 */
	private String email;
	/**
	 * 密碼
	 */
	private String password;
	/**
	 * 名稱
	 */
	private String name;
}