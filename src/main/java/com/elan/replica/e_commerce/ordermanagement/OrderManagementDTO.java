package com.elan.replica.e_commerce.ordermanagement;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.elan.replica.e_commerce.productmanagement.ProductManagementEntity;
import com.elan.replica.e_commerce.userauth.UserAuthEntity;
import lombok.Data;

/**
 * This is DTO class for order management detail....
 * 
 * @author ELANGO
 */

@Data
public class OrderManagementDTO {

	private Integer orderId;
	
	private ProductManagementEntity productId;
    
	private UserAuthEntity userId;

	private Integer quantity;

	private BigDecimal totalPrice;

	private Timestamp orderDate;

	private Timestamp whenCreatedIST;

	private String createdBy;

	private Timestamp whenUpdatedIST;

	private String updatedBy;

}
