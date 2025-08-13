package com.elan.replica.e_commerce.userauth;


import java.sql.Timestamp;
import lombok.Data;

/**
 * This is DTO class for user authentication details. ...
 * 
 * @author ELANGO
 */

@Data
public class UserAuthDTO {
	
	private Integer userId;

	private String userName;

	private String emailId;

	private String phoneNumber;

	private String password;

	private String otp;

	private String verified;
	
	private Timestamp whenCreatedIST;

	private String createdBy;

	private Timestamp whenUpdatedIST;
	
	private String updatedBy;

}
