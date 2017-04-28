package com.tvajjala.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.tvajjala.persistence.model.Product;
import com.tvajjala.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This Jersey REST resource exposes product operations as web services.
 *
 * Using Swagger API annotations we can test this at http://localhost:8084
 *
 * @author ThirupathiReddy V
 *
 */
@Component
@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Product Resource", produces = "application/json")
public class ProductResource {

    /** The logger instance */
    private static final Logger LOG = LoggerFactory.getLogger(ProductResource.class);


    @Autowired
    ProductService productService;

    @GET
    @Path("/{productId}")
    @ApiOperation(value = "Return products with productId ", response = Product.class)
    public Response getHelloVersionInUrl(@PathParam("productId") String productId) {

	final Product product= productService.getProduct(productId);

	if(product==null){
	    LOG.warn("There is no product with productId {} ",productId);
	    return Response.status(Status.NOT_FOUND).build();
	}

	LOG.info("Returning product with productId {} ",productId);
	return Response.status(Status.OK).entity(product).build();
    }

    @GET
    @ApiOperation(value = "Return all the products")
    @Cacheable
    public Response getHelloVersionInAcceptHeader() {
	LOG.info("Return all the products");
	final List<Product> list= productService.getAllProducts();
	return Response.status(Status.OK).entity(list).build();
    }

    @POST
    @ApiOperation(value = "Creates Products", response = Product.class)
    public Response createHelloVersionInUrl(Product product) {
	product=productService.createProduct(product);

	LOG.info("Creatin new product ");

	return Response.status(Status.OK).entity(product).build();
    }

}