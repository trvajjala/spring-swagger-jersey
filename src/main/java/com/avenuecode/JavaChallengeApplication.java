package com.avenuecode;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
/**
 *
 * Online Order Placement APplication
 *
 * @author ThirupathiReddy V
 *
 */

import com.avenuecode.persistence.model.Product;
import com.avenuecode.service.ProductService;
/**
 * This class is the entry point to the application
 *
 * @author ThirupathiReddy V
 *
 */
@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class JavaChallengeApplication extends SpringBootServletInitializer implements CommandLineRunner {

    /** Reference to logger */
    private static final Logger LOG = LoggerFactory.getLogger(JavaChallengeApplication.class);

    @Autowired
    ProductService productService;



    public static void main(String[] args) throws Exception {
	System.setProperty("spring.profiles.active", "H2DB");
	LOG.info("Running Spring boot application with profile :  {}", System.getProperty("spring.profiles.active"));
	final SpringApplication application = new SpringApplication(JavaChallengeApplication.class);
	application.run(args);
    }


    /**
     * Adds seed data into the database
     */

    @Override
    public void run(String... args) throws Exception {
	LOG.info("Seed data can be handled from here");

	final List<Product> products = Arrays.asList(new Product("US12345678", "IPad", "New Ipad", 800.34),
		new Product("US12345679", "IPadMini", "Refurbished Ipad mini", 700.34), new Product("US12345677", "Iphone5", "Iphone", 200.34),new Product("US12345676", "Iphone6", "Refurbished Iphone", 444.34));

	for (final Product product : products) {
	    productService.createProduct(product);
	}

    }

}
