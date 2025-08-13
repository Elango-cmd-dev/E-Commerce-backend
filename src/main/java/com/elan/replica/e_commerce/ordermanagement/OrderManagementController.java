package com.elan.replica.e_commerce.ordermanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * This is controller class for order management detail....
 * 
 * @author ELANGO
 */

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping(path="elan/api/v1")
@Api(tags = "Order management detail", description = "Order management detail")
public class OrderManagementController {
	
	@Autowired
	private OrderManagementService orderManagementService;
	
	@GetMapping(value = "/orderManagement", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View order management details")
	public List<OrderManagementDTO> getOrderManagementDetail(){
		return orderManagementService.findOrderManagementDetails();
	}
	
	@GetMapping(value = "/orderManagement/orderId/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View shopping cart managenment detail by order id")
	public ResponseEntity<Object> getOrderManagementDetailByOrderId(
			@ApiParam("Get order management detail by order id")@PathVariable Integer orderId){
		return orderManagementService.findOrderManagementDetailByOrderId(orderId);
	}
	
	@GetMapping(value = "/orderManagement/productId/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View order managenment detail by product id")
	public ResponseEntity<Object> getOrderManagementDetailByProductId(
			@ApiParam("Get order management detail by product id")@PathVariable Integer productId){
		return orderManagementService.findOrderManagementDetailByProductId(productId);
	}
	
	@GetMapping(value = "/orderManagement/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View order managenment detail by user id")
	public ResponseEntity<Object> getOrderManagementDetailByUserId(
			@ApiParam("Get order management detail by user id")@PathVariable Integer userId){
		return orderManagementService.findOrderManagementDetailByUserId(userId);
	}
	
	@PostMapping(value = "/orderManagement", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create order management detail")
	public ResponseEntity<Object> addOrderManagementDetail(
			@ApiParam(value = "Order management detail") @RequestBody OrderManagementDTO orderManagementDTO) {
		return orderManagementService.saveOrderManagementDetail(orderManagementDTO);
	}
	
	@DeleteMapping("/orderManagement/{cartId}")
	@ApiOperation(value = "Delete order management detail")
	public ResponseEntity<String> deleteOrderManagementDetail(
			@ApiParam(value = "Delete order management detail by cart id") @PathVariable Integer orderId) {
		return orderManagementService.deleteOrderManagementDetailByOrderId(orderId);
	}

}
