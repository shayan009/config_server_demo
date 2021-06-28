package com.shayan.config_server;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
@EnableConfigServer
public class ConfigServerApp extends MvcConfiguration {

	
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApp.class, args);
	}
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	         return application.sources(ConfigServerApp.class);
	    }
	 
	@Bean
	public TomcatServletWebServerFactory servletContainerFactory() {
	    return new TomcatServletWebServerFactory() {
	    
	      @Override
	      protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
	          // webapps directory does not exist by default, needs to be created
	          new File(tomcat.getServer().getCatalinaBase(), "webapps").mkdirs();
	    
	          // Add a war with given context path
	          // Can add multiple wars this way with different context paths
	         // tomcat.addWebapp("SampleWebApp.war", "target/SampleWebApp.war");
	    
	          return super.getTomcatWebServer(tomcat);
	      }
	    
	    };
	}
}
