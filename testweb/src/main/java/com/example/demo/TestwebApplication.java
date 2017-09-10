package com.example.demo;

import com.shcem.aop.EnableCommon;
import com.shcem.webcore.freemarker.EnableFreemarker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFreemarker
@EnableCommon
public class TestwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestwebApplication.class, args);
	}
}
