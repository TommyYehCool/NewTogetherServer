package com.exfantasy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.entity.User;
import com.exfantasy.server.service.UserService;
import com.exfantasy.server.vo.response.RespCommon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/user")
@Api(tags = "使用者相關 APIs")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * <pre>
	 * 註冊使用者
	 * </pre>
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "註冊使用者")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public RespCommon register(@ModelAttribute User user) {
		userService.register(user);
		return new RespCommon(ResultCode.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 使用 Email 查詢使用者
	 * </pre>
	 * 
	 * @param email
	 * @return
	 */
	@ApiOperation(value = "使用 Email 查詢使用者")
	@RequestMapping(value = "/find-by-email", method = RequestMethod.GET)
	@ResponseBody
	public RespCommon findByEmail(@RequestParam("email") String email) {
		User user = userService.findByEmail(email);
		return new RespCommon(ResultCode.SUCCESS, user);
	}

}