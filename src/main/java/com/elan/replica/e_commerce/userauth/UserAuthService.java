package com.elan.replica.e_commerce.userauth;

import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 * This is service class for user authentication details. ...
 * 
 * @author ELANGO
 */

public interface UserAuthService{

	List<UserAuthDTO> findUserAuthenticationDetails();
	
	ResponseEntity<Object> findUserAuthenticationDetailByUserId(Integer userId);

	ResponseEntity<Object> saveUserAuthenticationDetail(UserAuthDTO userAuthDTO);

	ResponseEntity<Object> updateUserAuthenticationDetailByUserId(Integer userId, UserAuthDTO userAuthDTO);

	ResponseEntity<String> deleteUserAuthenticationDetailByUserId(Integer userId);

	ResponseEntity<Object> findUserAuthenticationLogin(String emailId, String password);

	ResponseEntity<Object> findUserAuthenticationByEmailId(String emailId);

	ResponseEntity<Object> updateUserAuthenticationPasswordByEmailId(String emailId, String password);

	ResponseEntity<Object> findUserAuthenticationOTP(String emailId, String otp);

	ResponseEntity<Object> updateUserAuthenticationOTPDetailByEmailId(String emailId, String otp);
	
}
