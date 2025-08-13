package com.elan.replica.e_commerce.productmanagement;

import java.sql.Timestamp;

import lombok.Data;

/**
 * This is DTO class for product management details. ...
 * 
 * @author ELANGO
 */

@Data
public class ProductManagementDTO {

	private Integer productId;

	private String productName;
	
	private String productCategories;
	
	private String availableQuentity;

	private String productDescription;

	private String productPrice;

	private String productImage;

	private Timestamp whenCreatedIST;

	private String createdBy;

	private Timestamp whenUpdatedIST;

	private String updatedBy;

}
