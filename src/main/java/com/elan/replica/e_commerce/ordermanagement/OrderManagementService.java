package com.elan.replica.e_commerce.ordermanagement;

import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 * This is service class for order management detail....
 * 
 * @author ELANGO
 */

public interface OrderManagementService {

	List<OrderManagementDTO> findOrderManagementDetails();

	ResponseEntity<Object> findOrderManagementDetailByOrderId(Integer orderId);

	ResponseEntity<Object> findOrderManagementDetailByProductId(Integer productId);

	ResponseEntity<Object> findOrderManagementDetailByUserId(Integer userId);

	ResponseEntity<Object> saveOrderManagementDetail(OrderManagementDTO orderManagementDTO);

	ResponseEntity<String> deleteOrderManagementDetailByOrderId(Integer cartId);

}
