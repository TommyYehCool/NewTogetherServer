package com.exfantasy.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 定義某個網址對應到電腦的某個資料夾
 * 
 * @author Tommy
 */
@Configuration
public class ResourceConfig extends WebMvcConfigurerAdapter {
	
	@Value("${store.file.path}")
	private String STORE_FILE_PATH;
	
//	@Autowired
//	private UserManager userManager;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!STORE_FILE_PATH.endsWith("/")) {
			STORE_FILE_PATH += "/";
		}
		
//		UserEntity[] allUsers = userManager.findAllUsers();
//		
//		for (UserEntity user : allUsers) {
//			String userEmail = user.getEmail();
//			
//			// FIXME �o��A�ݬݭn���n�έӶýX���N userEmail, �K�o�Q�}��
//			// FIXME �٦��n�Ҽ{�s���U���n���[ ResourceHandlerRegistry
//			String userFolderPath = STORE_FILE_PATH + userEmail + "/";
//			File userFolder = new File(userFolderPath);
//			if (!userFolder.isDirectory()) {
//				userFolder.mkdirs();
//			}
//			registry.addResourceHandler("/" + userEmail + "/**").addResourceLocations("file:" + userFolderPath);
//		}
	}
}
