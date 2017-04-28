package com.avenuecode.service.test;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tvajjala.persistence.model.OnlineOrder;
import com.tvajjala.persistence.model.Product;
import com.tvajjala.service.OrderService;
import com.tvajjala.service.ProductService;

/**
 * This test case covers addItem to cart and placing order scenarios
 *
 * @author ThirupathiReddy V
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    Product product;

    @Before
    public void createProduct() {
	product = new Product();
	product.setDescription("MacBook Pro");
	product.setPrice(1425.00);
	product.setName("Apple Macbook Pro");
	product.setProductId("ABCJ838838");
	product = productService.createProduct(product);
	Assert.assertNotNull(product.getId());
    }

    @Test
    public void addItemToCartTest() {
	final OnlineOrder order = orderService.addToCart(product.getProductId());
	Assert.assertThat(order.getTotalPrice(), CoreMatchers.equalTo(product.getPrice()));
    }

    @After
    public void placeOrder() {
	orderService.placeOrder();
    }

}
