package com.elan.replica.e_commerce.shoppingcartmanagement;

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
 * This is controller class for shopping cart management details. ...
 * 
 * @author ELANGO
 */

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping(path="elan/api/v1")
@Api(tags = "Shopping cart management detail", description = "Shopping cart management detail")
public class ShoppingCartManagementController {
	
	@Autowired
	private ShoppingCartManagementService shoppingCartManagementService;
	
	@GetMapping(value = "/cartManagement", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View shopping cart management details")
	public List<ShoppingCartManagementDTO> getCartManagementDetail(){
		return shoppingCartManagementService.findCartManagementDetails();
	}
	
	@GetMapping(value = "/cartManagement/cartId/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View shopping cart managenment detail by cart id")
	public ResponseEntity<Object> getCartManagementDetailByCartId(
			@ApiParam("Get shopping cart management detail by cart id")@PathVariable Integer cartId){
		return shoppingCartManagementService.findCartManagementDetailByCartId(cartId);
	}
	
	@GetMapping(value = "/cartManagement/productId/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View shopping cart managenment detail by product id")
	public ResponseEntity<Object> getCartManagementDetailByProductId(
			@ApiParam("Get shopping cart management detail by product id")@PathVariable Integer productId){
		return shoppingCartManagementService.findCartManagementDetailByProductId(productId);
	}
	
	@GetMapping(value = "/cartManagement/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View shopping cart managenment detail by user id")
	public ResponseEntity<Object> getCartManagementDetailByUserId(
			@ApiParam("Get shopping cart management detail by user id")@PathVariable Integer userId){
		return shoppingCartManagementService.findCartManagementDetailByUserId(userId);
	}
	
	@PostMapping(value = "/cartManagement", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create shopping cart management detail")
	public ResponseEntity<Object> addCartManagementDetail(
			@ApiParam(value = "Shopping cart management detail") @RequestBody ShoppingCartManagementDTO shoppingCartManagementDTO) {
		return shoppingCartManagementService.saveCartManagementDetail(shoppingCartManagementDTO);
	}
	
	@DeleteMapping("/cartManagement/{cartId}")
	@ApiOperation(value = "Delete product management detail")
	public ResponseEntity<String> deleteCartManagementDetail(
			@ApiParam(value = "Delete shopping cart management detail by cart id") @PathVariable Integer cartId) {
		return shoppingCartManagementService.deleteCartManagementDetailByCartId(cartId);
	}

}
