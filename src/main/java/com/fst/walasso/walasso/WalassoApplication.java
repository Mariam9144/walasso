package com.fst.walasso.walasso;



import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class WalassoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalassoApplication.class, args);
		
		
	}
	
	
	@Bean
	ServletRegistrationBean<FacesServlet> jsfServletRegistration(ServletContext
	servletContext) { // spring boot only works if this is
	// set
	servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration",
	Boolean.TRUE.toString());
	// registration
	ServletRegistrationBean<FacesServlet> srb = new
	ServletRegistrationBean<FacesServlet>();
	srb.setServlet(new FacesServlet());
	srb.setUrlMappings(Arrays.asList("*.xhtml"));
	srb.setLoadOnStartup(1);
	return srb;
	}

	
	
}
