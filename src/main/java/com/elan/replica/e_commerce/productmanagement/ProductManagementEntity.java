package com.elan.replica.e_commerce.productmanagement;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This is entity class for product management detail....
 * 
 * @author ELANGO
 */

@Entity
@Table(name = "")
@Data
public class ProductManagementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private Integer productId;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "productCategories")
	private String productCategories;
	
	@Column(name = "availableQuentity")
	private String availableQuentity;
	
	@Column(name = "productDescription")
	private String productDescription;
	
	@Column(name = "productPrice")
	private String productPrice;
	
	@Column(name = "productImage")
	private String productImage;
	
	@Column(name = "whenCreatedIST")
	private Timestamp whenCreatedIST;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "whenUpdatedIST")
	private Timestamp whenUpdatedIST;

	@Column(name = "updatedBy")
	private String updatedBy;

}
