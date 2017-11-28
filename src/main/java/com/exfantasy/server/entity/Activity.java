package com.exfantasy.server.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "activities")
public class Activity {
	/**
     * 活動 ID
     */
	@Id
	@ApiModelProperty(notes = "活動 ID", required = false)
    private Long id;
    /**
     * 建立活動使用者 ID
     */
	@ApiModelProperty(notes = "建立活動使用者 ID", required = true)
    private Long createUserId;
    /**
     * 緯度
     */
	@ApiModelProperty(notes = "緯度", required = true)
    private Float latitude;
    /**
     * 經度
     */
	@ApiModelProperty(notes = "經度", required = true)
    private Float longitude;
    /**
     * 活動名稱
     */
	@ApiModelProperty(notes = "活動名稱", required = true)
    private String name;
    /**
     * 活動內容
     */
	@ApiModelProperty(notes = "活動內容", required = true)
    private String content;
    /**
     * 參加人數
     */
	@ApiModelProperty(notes = "參加人數", required = true)
    private Integer attendeeNum;
    /**
     * 活動日期及時間
     */
	@ApiModelProperty(notes = "活動日期及時間", required = true)
    private Integer datetime;
    /**
     * 參與活動的使用者
     */
	@ApiModelProperty(notes = "參與活動的使用者", required = false)
    private List<User> joinedUsers;
    /**
     * 活動的留言
     */
	@ApiModelProperty(notes = "活動的留言", required = false)
    private List<Message> messages;
}
