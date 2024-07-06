package com.example.Minor1;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.http.conn.HttpHostConnectException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Minor1Application{


	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(FileSystem.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
		app.run(args);
		FileSystem fs = new FileSystem();
		String obj = fs.mainLogic();
		System.out.println(obj);
	}
}

@Controller
@RequestMapping("/api")
class Runner{
	@GetMapping("/data")
	@ResponseBody
	public String runner() throws Exception,HttpHostConnectException {
		FileSystem fs = new FileSystem();
		String obj = fs.mainLogic();
		return obj;
	}
}


@Configuration
class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}