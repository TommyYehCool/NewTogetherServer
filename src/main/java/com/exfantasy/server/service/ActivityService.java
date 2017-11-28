package com.exfantasy.server.service;

import java.util.Arrays;
import java.util.List;

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
		
		// 把建立者的 userId 加入 joinedUserIds
		activity.setJoinedUserIds(Arrays.asList(activity.getCreateUserId()));
		
		Long id = counterService.getNextSequence(ACTIVITY_COLLECTION);
		activity.setId(id);
		
		activityRepo.save(activity);
		
		logger.info(">>>>> Create activity: {} succeed", activity);
	}

	public void join(Long joinUserId, Long activityId) {
		logger.info(">>>>> Prepare to let user with id: <" + joinUserId + "> join activity with id: <" + activityId + ">");
		
		Activity activity = activityRepo.findOne(activityId);
		
		if (activity == null) {
			logger.error("<<<<< Cannot find mapping activity with id: <{}>", activityId);
			throw new OperationException(ResultCode.ACTIVITY_NOT_FOUND);
		}

		logger.info("Found " + activity);
		
		long createUserId = activity.getCreateUserId();
		if (createUserId == joinUserId) {
			logger.error("<<<<< Cannot join event with createUserId: <{}> equals joinUserId: <{}>", createUserId, joinUserId);
			throw new OperationException(ResultCode.CANNOT_JOIN_ACTITVITY_THAT_USER_CREATED);
		}
		
		List<Long> joinedUserIds = activity.getJoinedUserIds();
		if (joinedUserIds.contains(joinUserId)) {
			logger.error("<<<<< Cannot join event that joinUserId: <" + joinUserId + "> already joined");
			throw new OperationException(ResultCode.CAONNOT_JOIN_ACTIVITY_THAT_ALREADY_JOINED);
		}
		
		User user = userRepo.findOne(joinUserId);
		if (user == null) {
			logger.error("<<<<< Cannot find mapping user with userId: <" + joinUserId + ">");
			throw new OperationException(ResultCode.USER_NOT_FOUND);
		}

		logger.info("Found " + user);
		
		activity.getJoinedUserIds().add(joinUserId);
		activityRepo.save(activity);
		
		logger.info("<<<<< User with id: <{}> join activity with id: <{}> succeed", joinUserId, activityId);
	}
}
