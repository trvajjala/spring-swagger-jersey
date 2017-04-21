package com.avenuecode.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avenuecode.persistence.model.OnlineOrder;
import com.avenuecode.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * As per the challenge Using it as OrderResource. it would be good to have CartResource.
 *
 * Using Swagger API annotations we can test this at http://localhost:8084
 *
 * @author ThirupathiReddy V
 *
 */
@Component
@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Order Resource", produces = "application/json")
public class OrderResource {

    /** The logger instance */
    private static final Logger LOG = LoggerFactory.getLogger(OrderResource.class);


    @Autowired
    OrderService orderService;

    @GET
    @Path("/{orderNumber}")
    @ApiOperation(value = "Return orders with orderId ", response = OnlineOrder.class)
    public Response getOnlineOrder(@PathParam("orderNumber") String orderNumber) {
	final OnlineOrder order= orderService.getOrder(orderNumber);
	if(order==null){
	    LOG.warn("There is no order with orderNumber {} ",orderNumber);
	    return Response.status(Status.NOT_FOUND).build();
	}
	LOG.info("Returning order with orderNumber {} ",orderNumber);
	return Response.status(Status.OK).entity(order).build();
    }

    @GET
    @ApiOperation(value = "Return all orders")
    public Response getAllOrders() {
	LOG.info("Return all the orders");
	final List<OnlineOrder> list= orderService.getAllOrders();
	return Response.status(Status.OK).entity(list).build();
    }

    @PUT
    @Path("/{productId}")
    @ApiOperation(value = "Add Item to Cart", response = OnlineOrder.class)
    public Response addItem(@PathParam("productId") String productId) {
	LOG.info("Adding new Item {} ",productId);

	final OnlineOrder onlineOrder=  orderService.addToCart(productId);
	return Response.status(Status.OK).entity(onlineOrder).build();
    }


    @DELETE
    @Path("/{productId}")
    @ApiOperation(value = "Delete Item from Cart", response = OnlineOrder.class)
    public Response deleteItem(@PathParam("productId") String productId) {

	LOG.info("Deleting Item {} ",productId);
	final OnlineOrder onlineOrder=  orderService.deleteCartItem(productId);
	return Response.status(Status.OK).entity(onlineOrder).build();
    }

    @PUT
    @Path("/place-order")
    @ApiOperation(value = "PlaceOrder", response = OnlineOrder.class)
    public Response placeOrder(@Context HttpServletRequest req) {
	LOG.info("Adding new Item ");
	final OnlineOrder onlineOrder=  orderService.placeOrder();
	final Response response= Response.status(Status.OK).entity(onlineOrder).build();

	req.getSession().invalidate();// This will cleans your cart

	return response;
    }






}