package com.exfantasy.server.entity;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private long id;
    /**
     * 建立活動使用者 ID
     */
    private long createUserId;
    /**
     * 緯度
     */
    private double latitude;
    /**
     * 經度
     */
    private double longitude;
    /**
     * 活動名稱
     */
    private String name;
    /**
     * 活動內容
     */
    private String content;
    /**
     * 參加人數
     */
    private int attendeeNum;
    /**
     * 活動日期及時間
     */
    private int datetime;
    /**
     * 參與活動的使用者
     */
    private List<Long> joinedUserIds;
    /**
     * 活動的留言
     */
//    private List<Message> messages;
}
