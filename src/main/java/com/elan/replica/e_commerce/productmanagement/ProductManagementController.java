package com.elan.replica.e_commerce.productmanagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * This is controller class for product management details. ...
 * 
 * @author ELANGO
 */

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping(path="elan/api/v1")
@Api(tags = "Product management detail", description = "Product management detail")
public class ProductManagementController {

	@Autowired
	private ProductManagementService productManagementService;
	
	@GetMapping(value = "/productManagement", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View product management details")
	public List<ProductManagementDTO> getProductManagementDetail(){
		return productManagementService.findProductManagementDetails();
	}
	
	@GetMapping(value = "/productManagement/productId/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View product managenment detail by product id")
	public ResponseEntity<Object> getProductManagementDetailByProductId(
			@ApiParam("Get product management detail by product id")@PathVariable Integer productId){
		return productManagementService.findProductManagementDetailByProductId(productId);
	}
	
	@GetMapping(value = "/productManagement/productName/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View product managenment detail by product name")
	public ResponseEntity<Object> getProductManagementDetailByProductName(
			@ApiParam("Get product management detail by product name")@PathVariable String productName){
		return productManagementService.findProductManagementDetailByProductName(productName);
	}
	
	@GetMapping(value = "/productManagement/productPrice", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View product managenment detail by product price")
	public ResponseEntity<Object> getProductManagementDetailByProductPrice(
			@ApiParam("Get product management detail by product start price")@RequestParam Integer productStartPrice,
			@ApiParam("Get product management detail by product end price")@RequestParam Integer productEndPrice){
		return productManagementService.findProductManagementDetailByProductPrice(productStartPrice,productEndPrice);
	}
	
	@GetMapping(value = "/productManagement/productCategory/{productCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View product managenment detail by product category")
	public ResponseEntity<Object> getProductManagementDetailByProductCategory(
			@ApiParam("Get product management detail by product category")@PathVariable String productCategory){
		return productManagementService.findProductManagementDetailByProductCategory(productCategory);
	}
	
	@PostMapping(value = "/productManagement", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create product management detail")
	public ResponseEntity<Object> addProductManagementDetail(
			@ApiParam(value = "Product management detail") @RequestBody ProductManagementDTO productManagementDTO) {
		return productManagementService.saveProductManagementDetail(productManagementDTO);
	}
	
	@PutMapping(value = "/productManagement/{productId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update product management detail")
	public ResponseEntity<Object> updateProductManagementDetail(
			@ApiParam(value = "Product management detail update by product id") @PathVariable Integer productId,
			@ApiParam(value = "Product management detail")  @RequestBody ProductManagementDTO productManagementDTO) {
		return productManagementService.updateProductManagementDetailByProductId(productId, productManagementDTO);
	}

	@DeleteMapping("/ProductManagement/{productId}")
	@ApiOperation(value = "Delete product management detail")
	public ResponseEntity<String> deleteProductManagementDetail(
			@ApiParam(value = "Delete product management detail by product id") @PathVariable Integer productId) {
		return productManagementService.deleteProductManagementDetailByProductId(productId);
	}	
}

