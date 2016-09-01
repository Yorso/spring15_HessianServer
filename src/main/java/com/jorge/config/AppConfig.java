/**
 * This is a configuration class
 * 
 */

package com.jorge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jorge.service.UserService;
import com.jorge.service.UserServiceImpl;

/**
 * Using the Java RMI, HTTP Invoker, Hessian, and REST
 * 
 * 		HTTP Invoker to interact with another Spring application
 * 		Java RMI to interact with another Java application not using Spring
 * 		Hessian to interact with another Java application not using Spring when you need to go over	proxies and firewalls
 * 		SOAP if you have to
 * 		REST for all other cases. REST is currently the most popular option; it's simple, flexible, and cross-platform
 *
 */
@Configuration // This declares it as a Spring configuration class
@EnableWebMvc // This enables Spring's ability to receive and process web requests. Necessary for interceptors too.
@ComponentScan(basePackages = { "com.jorge.controller" }) // This scans the com.jorge.controller package for Spring components

// @Import({ DatabaseConfig.class, SecurityConfig.class }) => //If you are using a Spring application without a 'ServletInitializer' class,
														      // you can include other configuration classes from your primary configuration class

public class AppConfig{

	 @Bean
	 // Necessary for Java Hessian service. This returns an instance of UserServiceImpl
	 // SERVER SIDE
	 public UserService userService() {
		 return new UserServiceImpl();
	 }

	 
	 /***************************************************************************************************
	  * Creating a Hessian service
	  * 
	  * Hessian is a remote method invocation technology; here, a client executes a method located on a
	  * server-the Hessian service. It uses HTTP, so it can go over proxies and firewalls. It also has
	  * implementations in multiple languages (PHP, Python, Ruby, and so on). So, for example, the client
	  * can use Java and the server can use PHP.
	  * We will add a Hessian service to an existing Spring web application. It will expose the
	  * methods of a Java class.
	  * 
	  * HessianServiceExporter is a Spring class generating a Hessian service from a Java interface
	  * ( UserService ). For each method defined in UserService , the corresponding method from
	  * userService() , in UserServiceImpl , will be executed.
	  * 
	  */
	 
	 @Bean(name = "/userService") // The Hessian service is now available at the /userService URL of the Spring web application
	 // Hessian SERVER SIDE
	 public HessianServiceExporter hessianServiceExporter () {
		 HessianServiceExporter exporter = new HessianServiceExporter ();
		 
		 exporter.setService(userService()); // Refers to 'public UserService userService()' method above
		 exporter.setServiceInterface(UserService.class); // Refers to UserService interface
		 
		 return exporter;
	 }
	 
}