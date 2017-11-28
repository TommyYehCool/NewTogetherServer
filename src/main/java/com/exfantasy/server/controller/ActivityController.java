package com.exfantasy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		return new RespCommon(ResultCode.SUCCESS, "Create activiy succeed");
	}
}
