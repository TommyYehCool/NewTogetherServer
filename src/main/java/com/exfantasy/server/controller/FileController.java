package com.exfantasy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.cnst.file.CloudStorage;
import com.exfantasy.server.entity.User;
import com.exfantasy.server.exception.OperationException;
import com.exfantasy.server.service.file.FileService;
import com.exfantasy.server.vo.response.RespCommon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "file")
@Api("檔案管理相關 API")
public class FileController {

	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ApiOperation(value = "上傳檔案", notes = "上傳檔案", response = RespCommon.class)
	public @ResponseBody RespCommon upload(@RequestParam(value = "file", required = true) MultipartFile multipartFile) {
		if (!multipartFile.isEmpty()) {
//			User user = sessionService.getLoginUser();
			User user = new User(1L, "tommy.yeh1112@gmail.com", "1234qwer", "Tommy");
			String pathAndName = user.getEmail() + "/" + multipartFile.getOriginalFilename();
			CloudStorage cloudStorage = fileService.uploadFile(multipartFile, pathAndName);
			return new RespCommon(ResultCode.SUCCESS, "Upload file to " + cloudStorage + " succeed");
		}
		else {
			throw new OperationException(ResultCode.FILE_IS_EMPTY);
		}
	}
}
