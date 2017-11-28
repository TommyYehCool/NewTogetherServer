package com.exfantasy.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * <pre>
	 * 避免 swagger-ui.html 出現 basic-error-controller
	 * 
	 * 參考: <a href="http://stackoverflow.com/questions/33431343/avoiding-default-basic-error-controller-from-swagger-api">Avoiding default basic-error-controller from swagger api</a>
	 * </pre>
	 * 
	 * @return
	 */
	@Bean
	public Docket createSwaggerDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.paths(Predicates.not(PathSelectors.regex("/signup/facebook.*")))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		String title = "Together REST APIs";
		String description = createDescription();
		String version = "1.0";
		String termsOfServiceUrl = "exfantasy7wolves@gmail.com";
		String contactName = "Exfantasy";
		String license = "API License URL";

		ApiInfo apiInfo = new ApiInfo(title, description, version, termsOfServiceUrl, contactName, license, null);

		return apiInfo;
	}

	private String createDescription() {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("提供 Together 功能需要 APIs");
		
		return buffer.toString();
	}

}
