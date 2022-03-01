package com.epam.vaccine_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class VaccineManagementSystemApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(VaccineManagementSystemApplication.class, args);

	}
	@Bean
	public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	}
	
	@Bean
	public Docket swaggerConfiguration() {
	return new Docket(DocumentationType.SWAGGER_2).select()
	.apis(RequestHandlerSelectors.basePackage("com.epam.vaccine_management")).build()
	.apiInfo(apiInfo());
	}

	 

	private ApiInfo apiInfo() {
	return new ApiInfo("Vaccine Management System ", "REST API for Vaccine Management System", "version : 1.1.2",
	"Free to use", new Contact("Vaccine Management System MVC Application", "/", ""), "License of API", "",
	java.util.Collections.emptyList());
	}
	
	
	
}
