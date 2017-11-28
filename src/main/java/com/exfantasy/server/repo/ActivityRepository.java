package com.exfantasy.server.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.exfantasy.server.entity.Activity;


public interface ActivityRepository extends MongoRepository<Activity, Long> {

	List<Activity> findByCreateUserId(Long userId);

	/**
	 * <pre>
	 * 根據 ActivityId 及 JoinedUserId 查詢
	 * 
	 * 參考: <a href="http://javasampleapproach.com/spring-framework/spring-data/mongodb-model-one-one-one-many-relationships-mongodb-embedded-documents-springboot">Spring Boot mongodb one-to-many</a>
	 * </pre>
	 * 
	 * @param activityId
	 * @param userId
	 * @return
	 */
	@Query("{'id': ?0, 'joinedUsers.id': ?1}")
	Activity findByActivityIdAndJoinedUserId(Long activityId, Long joinedUserId);
}
