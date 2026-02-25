package com.mycompany.masterslave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MasterSlaveSetupApplication implements ApplicationListener<WebServerInitializedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(MasterSlaveSetupApplication.class, args);
	}

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		System.out.println("Server is running on port: " + event.getWebServer().getPort());
	}
}
