package com.telstra.phoneservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CustomerPhoneServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerPhoneServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String description, @Value("${application-version}") String version) {
		return new OpenAPI()
				.info(new Info().title("Phone Service API Specification")
						.version(version)
						.description(description)
						.termsOfService("http://my-telstra.com/terms/")
						.license(new License().name("SandeepProprietary").url("https://www.linkedin.com/in/sandeepmandori/")));
	}

}
