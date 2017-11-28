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
    private long id;
    /**
     * 建立活動使用者 ID
     */
	@ApiModelProperty(notes = "建立活動使用者 ID", required = true)
    private long createUserId;
    /**
     * 緯度
     */
	@ApiModelProperty(notes = "緯度", required = true)
    private double latitude;
    /**
     * 經度
     */
	@ApiModelProperty(notes = "經度", required = true)
    private double longitude;
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
    private int attendeeNum;
    /**
     * 活動日期及時間
     */
	@ApiModelProperty(notes = "活動日期及時間", required = true)
    private int datetime;
    /**
     * 參與活動的使用者
     */
	@ApiModelProperty(notes = "參與活動的使用者", required = false)
    private List<Long> joinedUserIds;
    /**
     * 活動的留言
     */
//	@ApiModelProperty(notes = "活動的留言", required = false)
//    private List<Message> messages;
}
