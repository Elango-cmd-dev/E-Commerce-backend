package com.elan.replica.e_commerce.productmanagement;

import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * This is service class for product management details. ...
 * 
 * @author ELANGO
 */

public interface ProductManagementService {

	List<ProductManagementDTO> findProductManagementDetails();

	ResponseEntity<Object> findProductManagementDetailByProductId(Integer productId);

	ResponseEntity<Object> saveProductManagementDetail(ProductManagementDTO productManagementDTO);

	ResponseEntity<Object> updateProductManagementDetailByProductId(Integer productId,
			ProductManagementDTO productManagementDTO);

	ResponseEntity<String> deleteProductManagementDetailByProductId(Integer productId);

	ResponseEntity<Object> findProductManagementDetailByProductName(String productName);

	ResponseEntity<Object> findProductManagementDetailByProductPrice(Integer productStartPrice,
			Integer productEndPrice);

	ResponseEntity<Object> findProductManagementDetailByProductCategory(String productCategory);

}
