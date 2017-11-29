package com.exfantasy.server.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <pre>
 * Mvc 設定
 * 
 * 參考:
 * 	<a href="https://spring.io/guides/gs/securing-web/">Spring Security 範例</a>
 * </pre>
 * 
 * @author tommy.feng
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * 設定輸入什麼網址會被導到哪個頁面
	 * 
	 * @param registry
	 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/").setViewName("index");
    }
    
}