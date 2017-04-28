package com.avenuecode.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tvajjala.persistence.model.Product;
import com.tvajjala.service.ProductService;

/**
 * This test case covers product creation service
 *
 * @author ThirupathiReddy V
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    public void createProductTest() {
	Product product = new Product();
	product.setDescription("MacBook Pro");
	product.setPrice(1425.00);
	product.setName("Apple Macbook Pro");
	product.setProductId("ABCJ838838");
	product = productService.createProduct(product);
	Assert.assertNotNull(product.getId());
    }

}
