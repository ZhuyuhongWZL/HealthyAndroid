package com.mrjk.demo;

import com.mrjk.demo.statistic.DoctorStarStatisticTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		DoctorStarStatisticTask.start();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory config = new MultipartConfigFactory();
		config.setMaxFileSize("80MB");
		config.setMaxRequestSize("80MB");
		return config.createMultipartConfig();
	}
}
