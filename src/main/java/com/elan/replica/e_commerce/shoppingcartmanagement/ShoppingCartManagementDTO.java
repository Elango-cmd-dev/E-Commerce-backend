package com.elan.replica.e_commerce.shoppingcartmanagement;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.elan.replica.e_commerce.productmanagement.ProductManagementEntity;
import com.elan.replica.e_commerce.userauth.UserAuthEntity;
import lombok.Data;

/**
 * This is DTO class for shopping cart management details. ...
 * 
 * @author ELANGO
 */

@Data
public class ShoppingCartManagementDTO {

	private Integer cartId;
	
	private ProductManagementEntity productId;

    private UserAuthEntity userId; 

	private Integer quantity;

	private BigDecimal totalPrice;

	private Timestamp whenCreatedIST;

	private String createdBy;

	private Timestamp whenUpdatedIST;

	private String updatedBy;
}
