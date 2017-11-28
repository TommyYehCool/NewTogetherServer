package com.exfantasy.server.entity;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	/**
	 * 留言 ID
	 */
	@Id
	@ApiModelProperty(notes = "留言 ID", required = false)
	private Long id;
	
	@ApiModelProperty(notes = "留言者名稱", required = false)
	private String username;
	
	@ApiModelProperty(notes = "留言", required = false)
	private String message;

}
