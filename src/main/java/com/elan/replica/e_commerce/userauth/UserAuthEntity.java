package com.elan.replica.e_commerce.userauth;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This is entity class for user authentication detail....
 * 
 * @author ELANGO
 */

@Entity
@Table(name = "")
@Data
public class UserAuthEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Integer userId;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "emailId")
	private String emailId;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "otp")
	private String otp;
	
	@Column(name = "verified")
	private String verified;
	
	@Column(name = "whenCreatedIST")
	private Timestamp whenCreatedIST;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "whenUpdatedIST")
	private Timestamp whenUpdatedIST;

	@Column(name = "updatedBy")
	private String updatedBy;

}
