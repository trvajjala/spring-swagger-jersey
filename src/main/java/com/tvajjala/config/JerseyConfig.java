package com.tvajjala.config;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.tvajjala.resource.OrderResource;
import com.tvajjala.resource.ProductResource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
/**
 * This configuration file allows to configure spring boot with Jersey
 *
 * @author ThirupathiReddy V
 *
 */
@Component
public class JerseyConfig extends ResourceConfig {

    @Value("${spring.jersey.application-path:/api}")
    private String apiPath;


    /**
     * This bean allows your access H2 database console
     *
     * @return ServletBean
     */
    @Bean
    ServletRegistrationBean h2servletRegistration(){
	final ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
	registrationBean.addUrlMappings("/console/*");
	return registrationBean;
    }

    public JerseyConfig() {
	registerEndpoints();
    }

    @PostConstruct
    public void init() {
	// Register components where DI is needed
	configureSwagger();
    }

    private void registerEndpoints() {
	this.register(OrderResource.class);
	this.register(ProductResource.class);
	this.register(WadlResource.class);
    }

    private void configureSwagger() {
	// Available at localhost:port/swagger.json
	this.register(ApiListingResource.class);
	this.register(SwaggerSerializers.class);

	final BeanConfig config = new BeanConfig();
	config.setConfigId("avenue-code-java-challenge");
	config.setTitle("Online Shopping Cart Application");
	//config.setVersion("v1");
	config.setContact("ThirupathiReddy");
	config.setSchemes(new String[] { "http", "https" });
	config.setBasePath(apiPath);
	config.setResourcePackage("com.avenuecode.resource");
	config.setPrettyPrint(true);
	config.setScan(true);
    }
}