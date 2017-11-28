package com.exfantasy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.entity.Activity;
import com.exfantasy.server.service.ActivityService;
import com.exfantasy.server.vo.response.RespCommon;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@RequestMapping(method = RequestMethod.GET)
	public String returnEventPage(Model model) {
		model.addAttribute("activity", new Activity());
		return "activity";
	}

	@RequestMapping(value = "/create-from-web", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RespCommon createActivity(Activity activity) {
		activityService.createActivity(activity);
		return new RespCommon(ResultCode.SUCCESS, "Create activiy succeed");
	}
}
