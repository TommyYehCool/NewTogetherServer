package com.exfantasy.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.entity.User;
import com.exfantasy.server.exception.OperationException;
import com.exfantasy.server.repo.UserRepository;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private final String USER_COLLECTION = "users";
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private UserRepository userRepo;

	public void register(User user) {
		logger.info(">>>>> Prepare to register user: {}", user);
		
		String email = user.getEmail();
		logger.info("Prepare to find user by email: <{}>", email);
		
		User userFindByEmail = userRepo.findByEmail(email);
		if (userFindByEmail != null) {
			logger.warn("User with email: <{}> existed", email);
			throw new OperationException(ResultCode.USER_EXISTED);
		}
		
		Long id = counterService.getNextSequence(USER_COLLECTION);
		user.setId(id);
		
		userRepo.save(user);
		
		logger.info("<<<<< Register user: {} succeed", user);
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

}
