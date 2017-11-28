package com.exfantasy.server.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.entity.Activity;
import com.exfantasy.server.entity.User;
import com.exfantasy.server.exception.OperationException;
import com.exfantasy.server.repo.ActivityRepository;
import com.exfantasy.server.repo.UserRepository;

@Service
public class ActivityService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private final String ACTIVITY_COLLECTION = "activities";
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ActivityRepository activityRepo;

	public void createActivity(Activity activity) {
		logger.info(">>>>> Prepare to create activity: {}", activity);

		User createUser = userRepo.findOne(activity.getCreateUserId());
		
		// 把建立者的 userId 加入 joinedUserIds
		activity.setJoinedUsers(Arrays.asList(createUser));
		
		Long id = counterService.getNextSequence(ACTIVITY_COLLECTION);
		activity.setId(id);
		
		activityRepo.save(activity);
		
		logger.info(">>>>> Create activity: {} succeed", activity);
	}

	public void join(Long joinUserId, Long activityId) {
		logger.info(">>>>> Prepare to let user with id: <" + joinUserId + "> join activity with id: <" + activityId + ">");
		
		// 判斷想參加的使用者資料是否存在
		User user = userRepo.findOne(joinUserId);
		if (user == null) {
			logger.error("<<<<< Cannot find mapping user with id: <" + joinUserId + ">");
			throw new OperationException(ResultCode.USER_NOT_FOUND);
		}
		logger.info("Found " + user);
		
		// 判斷想參加的活動是否存在
		Activity activity = activityRepo.findOne(activityId);
		if (activity == null) {
			logger.error("<<<<< Cannot find mapping activity with id: <{}>", activityId);
			throw new OperationException(ResultCode.ACTIVITY_NOT_FOUND);
		}
		logger.info("Found " + activity);
		
		// 判斷想參加的使用者不為此活動建立者
		long createUserId = activity.getCreateUserId();
		if (createUserId == joinUserId) {
			logger.error("<<<<< Cannot join event with createUserId: <{}> equals joinUserId: <{}>", createUserId, joinUserId);
			throw new OperationException(ResultCode.CANNOT_JOIN_ACTITVITY_THAT_USER_CREATED);
		}
		
		// 判斷想參加的使用者是否已參加過此活動
		Activity joinedActivity = activityRepo.findByActivityIdAndJoinedUserId(activityId, joinUserId);
		if (joinedActivity != null) {
			logger.error("<<<<< Cannot join event that joinUserId: <" + joinUserId + "> already joined");
			throw new OperationException(ResultCode.CAONNOT_JOIN_ACTIVITY_THAT_ALREADY_JOINED);
		}
		
		activity.getJoinedUsers().add(user);
		activityRepo.save(activity);
		
		logger.info("<<<<< User with id: <{}> join activity with id: <{}> succeed", joinUserId, activityId);
	}

}
