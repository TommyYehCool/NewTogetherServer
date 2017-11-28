package com.exfantasy.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.entity.Activity;
import com.exfantasy.server.service.ActivityService;
import com.exfantasy.server.vo.response.RespCommon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/activity")
@Api(tags = "活動相關 APIs")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	/**
	 * <pre>
	 * 取得活動頁面
	 * </pre>
	 * 
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "取得活動頁面")
	@RequestMapping(method = RequestMethod.GET)
	public String returnEventPage(Model model) {
		model.addAttribute("activity", new Activity());
		return "activity";
	}

	/**
	 * <pre>
	 * 建立活動
	 * </pre>
	 * 
	 * @param activity
	 * @return
	 */
	@ApiOperation(value = "建立活動")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public RespCommon createActivity(@ModelAttribute Activity activity) {
		activityService.createActivity(activity);
		return new RespCommon(ResultCode.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 參加活動
	 * </pre>
	 * 
	 * @param userId
	 * @param activityId
	 * @return
	 */
	@ApiOperation(value = "參加活動")
	@RequestMapping(value = "/join", method = RequestMethod.PUT)
	@ResponseBody
	public RespCommon joinActivity(@RequestParam("activityId") Long activityId, @RequestParam("userId") Long userId) {
		activityService.join(activityId, userId);
		return new RespCommon(ResultCode.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 對活動留言
	 * </pre>
	 * 
	 * @param activityId
	 * @param username
	 * @param message
	 * @return
	 */
	@ApiOperation(value = "對活動留言")
	@RequestMapping(value = "/leave-message", method = RequestMethod.PUT)
	@ResponseBody
	public RespCommon leaveMessage(@RequestParam("activityId") Long activityId, @RequestParam("username") String username, @RequestParam("message") String message) {
		activityService.leaveMessage(activityId, username, message);
		return new RespCommon(ResultCode.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 查詢所在位置附近的活動
	 * </pre> 
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@ApiOperation(value = "查詢所在位置附近的活動")
	@RequestMapping(value = "/nearby", method = RequestMethod.GET)
	@ResponseBody
	public RespCommon nearby(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
		List<Activity> activities = activityService.nearby(latitude, longitude);
		return new RespCommon(ResultCode.SUCCESS, activities);
	}
}
