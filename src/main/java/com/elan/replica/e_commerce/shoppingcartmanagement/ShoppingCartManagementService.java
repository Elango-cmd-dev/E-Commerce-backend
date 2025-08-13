package com.elan.replica.e_commerce.shoppingcartmanagement;

import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 * This is service class for shopping cart management details. ...
 * 
 * @author ELANGO
 */

public interface ShoppingCartManagementService {

	List<ShoppingCartManagementDTO> findCartManagementDetails();

	ResponseEntity<Object> findCartManagementDetailByCartId(Integer cartId);

	ResponseEntity<Object> saveCartManagementDetail(ShoppingCartManagementDTO shoppingCartManagementDTO);

	ResponseEntity<String> deleteCartManagementDetailByCartId(Integer cartId);

	ResponseEntity<Object> findCartManagementDetailByProductId(Integer productId);

	ResponseEntity<Object> findCartManagementDetailByUserId(Integer userId);

}
