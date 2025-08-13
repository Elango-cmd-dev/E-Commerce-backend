package com.elan.replica.e_commerce.inventorymanagement;

import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * This is service class for inventory management detail....
 * 
 * @author ELANGO
 */

public interface InventoryManagementService {

	List<InventoryManagementDTO> findInventoryManagementDetails();

	ResponseEntity<Object> findInventoryManagementDetailByInventoryId(Integer inventoryId);

	ResponseEntity<Object> findInventoryManagementDetailByProductId(Integer productId);

	ResponseEntity<Object> saveInventoryManagementDetail(InventoryManagementDTO inventoryManagementDTO);

	ResponseEntity<Object> updateInventoryManagementDetailByInventoryId(Integer inventoryId,
			InventoryManagementDTO inventoryManagementDTO);

	ResponseEntity<String> deleteInventoryManagementDetailByInventoryId(Integer inventoryId);

}
