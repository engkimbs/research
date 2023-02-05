package com.engkimbs.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class CoffeeApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CoffeeApplication.class, args);

		// 404 exception 자동 처리
		DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}

}