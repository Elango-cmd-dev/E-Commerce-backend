package com.elan.replica.e_commerce.inventorymanagement;

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
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * This is controller class for inventory management detail....
 * 
 * @author ELANGO
 */

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping(path="elan/api/v1")
@Api(tags = "Inventory management detail", description = "Inventory management detail")
public class InventoryManagementController {
	
	@Autowired
	private InventoryManagementService inventoryManagementService;
	
	@GetMapping(value = "/inventoryManagement", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View inventory management details")
	public List<InventoryManagementDTO> getInventoryManagementDetail(){
		return inventoryManagementService.findInventoryManagementDetails();
	}
	
	@GetMapping(value = "/inventoryManagement/productId/{inventoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View inventory managenment detail by product id")
	public ResponseEntity<Object> getInventoryManagementDetailByInventoryId(
			@ApiParam("Get inventory management detail by inventory id")@PathVariable Integer inventoryId){
		return inventoryManagementService.findInventoryManagementDetailByInventoryId(inventoryId);
	}
	
	@GetMapping(value = "/inventoryManagement/productId/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View inventory managenment detail by product id")
	public ResponseEntity<Object> getInventoryManagementDetailByProductId(
			@ApiParam("Get inventory management detail by product id")@PathVariable Integer productId){
		return inventoryManagementService.findInventoryManagementDetailByProductId(productId);
	}
	
	@PostMapping(value = "/inventoryManagement", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create inventory management detail")
	public ResponseEntity<Object> addInventoryManagementDetail(
			@ApiParam(value = "Inventory management detail") @RequestBody InventoryManagementDTO inventoryManagementDTO) {
		return inventoryManagementService.saveInventoryManagementDetail(inventoryManagementDTO);
	}
	
	@PutMapping(value = "/inventoryManagement/{productId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update product management detail")
	public ResponseEntity<Object> updateInventoryManagementDetail(
			@ApiParam(value = "Inventory management detail update by inventory id") @PathVariable Integer inventoryId,
			@ApiParam(value = "Inventory management detail")  @RequestBody InventoryManagementDTO inventoryManagementDTO) {
		return inventoryManagementService.updateInventoryManagementDetailByInventoryId(inventoryId, inventoryManagementDTO);
	}

	@DeleteMapping("/inventoryManagement/{inventoryId}")
	@ApiOperation(value = "Delete inventory management detail")
	public ResponseEntity<String> deleteInventoryManagementDetail(
			@ApiParam(value = "Delete product management detail by inventory id") @PathVariable Integer inventoryId) {
		return inventoryManagementService.deleteInventoryManagementDetailByInventoryId(inventoryId);
	}

}
