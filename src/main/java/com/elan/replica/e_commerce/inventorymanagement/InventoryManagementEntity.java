package com.elan.replica.e_commerce.inventorymanagement;

import java.sql.Date;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This is entity class for inventory management detail....
 * 
 * @author ELANGO
 */

@Entity
@Table(name = "")
@Data
public class InventoryManagementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventoryId")
	private Integer inventoryId;
	
	@Column(name = "productId")
	private Integer productId;
	
	@Column(name = "productQuantity")
	private String productQuantity;
	
	@Column(name = "movementDate")
	private Date movementDate;
	
	@Column(name = "movementType")
	private String movementType;
	
	@Column(name = "whenCreatedIST")
	private Timestamp whenCreatedIST;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "whenUpdatedIST")
	private Timestamp whenUpdatedIST;

	@Column(name = "updatedBy")
	private String updatedBy;

}
