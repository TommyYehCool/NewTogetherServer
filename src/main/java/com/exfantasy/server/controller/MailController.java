package com.exfantasy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.service.MailService;
import com.exfantasy.server.vo.response.RespCommon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/mail")
@Api(tags = "發 Mail 相關 APIs")
public class MailController {

	@Autowired
	private MailService mailService;
	
	/**
	 * <pre>
	 * 發送 Email
	 * </pre>
	 * 
	 * @param mailTo 收件者信箱
	 * @param subject 主旨
	 * @param mailContent 內容
	 * @return
	 */
	@ApiOperation(value = "對活動留言")
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public RespCommon send(@RequestParam("mailTo") String mailTo, @RequestParam("subject") String subject, @RequestParam("mailContent") String mailContent) {
		mailService.send(mailTo, subject, mailContent);
		return new RespCommon(ResultCode.SUCCESS);
	}

}
