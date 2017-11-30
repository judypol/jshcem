package com.example.demo;

import com.shcem.aop.EnableCommon;
import com.shcem.logback.EnableLogbackFile;
import com.shcem.webcore.freemarker.EnableFreemarker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFreemarker
@EnableCommon
@EnableLogbackFile
//@ComponentScan(basePackages = {"com.example.demo","com.shcem.webcore"})
//public class TestwebApplication extends SpringBootServletInitializer {
//
//	public static void main(String[] args) {
//		SpringApplication.run(TestwebApplication.class, args);
//	}
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		builder.sources(this.getClass());
//		return super.configure(builder);
//	}
//}
public class TestwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestwebApplication.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		builder.sources(this.getClass());
//		return super.configure(builder);
//	}
}
