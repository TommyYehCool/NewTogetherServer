package com.exfantasy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.entity.User;
import com.exfantasy.server.service.UserService;
import com.exfantasy.server.vo.response.RespCommon;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String returnUserPage(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RespCommon register(User user) {
		userService.register(user);
		return new RespCommon(ResultCode.SUCCESS, "Register user succeed");
	}
	
	@RequestMapping(value = "/find-by-email", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public User findByEmail(@RequestParam("email") String email) {
		return userService.findByEmail(email);
	}
}