package com.elan.replica.e_commerce.shoppingcartmanagement;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.elan.replica.e_commerce.productmanagement.ProductManagementEntity;
import com.elan.replica.e_commerce.userauth.UserAuthEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This is entity class for shopping cart management detail....
 * 
 * @author ELANGO
 */

@Entity
@Table(name = "")
@Data
public class ShoppingCartManagementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartId")
	private Integer cartId;
	
    @ManyToOne
    @JoinColumn(name = "productId")
	@Column(name = "productId")
	private ProductManagementEntity productId;
    
    @OneToOne
    @JoinColumn(name = "userId")
    private UserAuthEntity userId; 
	
	@Column(name = "quantity")
	private Integer quantity;
	 
	@Column(name = "totalPrice")
	private BigDecimal totalPrice;
	
	@Column(name = "whenCreatedIST")
	private Timestamp whenCreatedIST;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "whenUpdatedIST")
	private Timestamp whenUpdatedIST;

	@Column(name = "updatedBy")
	private String updatedBy;

}
