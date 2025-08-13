package com.elan.replica.e_commerce.inventorymanagement;

import java.sql.Date;
import java.sql.Timestamp;
import lombok.Data;

/**
 * This is DTO class for inventory management details. ...
 * 
 * @author ELANGO
 */

@Data
public class InventoryManagementDTO {
	
	private Integer inventoryId;

	private Integer productId;

	private String productQuantity;

	private Date movementDate;

	private String movementType;

	private Timestamp whenCreatedIST;

	private String createdBy;

	private Timestamp whenUpdatedIST;

	private String updatedBy;

}
