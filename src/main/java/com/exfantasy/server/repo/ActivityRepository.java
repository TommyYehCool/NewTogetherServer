package com.exfantasy.server.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exfantasy.server.entity.Activity;

public interface ActivityRepository extends MongoRepository<Activity, Long> {

	List<Activity> findByCreateUserId(Long userId);

}
