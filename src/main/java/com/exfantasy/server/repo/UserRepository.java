package com.exfantasy.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exfantasy.server.entity.User;

public interface UserRepository extends MongoRepository<User, Long> {

	User findByEmail(String email);

}
