package com.exfantasy.server.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.exfantasy.server.entity.Activity;

public interface ActivityRepository extends MongoRepository<Activity, Long> {

	List<Activity> findByCreateUserId(Long userId);

	@Query("{'id': ?0, 'joinedUsers.id': ?1}")
	Activity findByActivityIdAndJoinedUserId(Long activityId, Long userId);
}
