package com.exfantasy.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exfantasy.server.entity.Activity;
import com.exfantasy.server.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api("取得相關頁面 APIs")
public class PageController {

	/**
	 * <pre>
	 * 取得使用者頁面
	 * </pre>
	 * 
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "取得使用者頁面")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String returnUserPage(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}
	
	/**
	 * <pre>
	 * 取得活動頁面
	 * </pre>
	 * 
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "取得活動頁面")
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String returnActivityPage(Model model) {
		model.addAttribute("activity", new Activity());
		return "activity";
	}

	/**
	 * <pre>
	 * 取得留言頁面
	 * </pre>
	 * 
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "取得活動頁面")
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String returnMessagePage(Model model) {
		return "message";
	}
	
	/**
	 * <pre>
	 * 取得發 Email 頁面
	 * </pre>
	 * 
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "取得 Email 頁面")
	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public String returnMailPage(Model model) {
		return "mail";
	}

}
