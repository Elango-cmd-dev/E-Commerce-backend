package com.elan.replica.e_commerce.userauth;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * This is controller class for user authentication details. ...
 * 
 * @author ELANGO
 */

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping(path="elan/api/v1")
@Api(tags = "User authentication detail", description = "user authentication detail")
public class UserAuthController {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private UserAuthService userAuthService;
	
	@GetMapping(value = "/userAuth", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View user authentication details")
	public List<UserAuthDTO> getUserAuthDetail(){
		return userAuthService.findUserAuthenticationDetails();
	}
	
	@GetMapping(value = "/userAuth/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View user authentication detail by user id")
	public ResponseEntity<Object> getUserAuthDetailByUserId(
			@ApiParam("Get user authentication detail by user id")@PathVariable Integer userId){
		return userAuthService.findUserAuthenticationDetailByUserId(userId);
	}
	
	@GetMapping(value = "/userAuth/emailId/forgot", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View user authentication detail through the emil id")
	public ResponseEntity<Object> getUserAuthDetailByEmailId(
			@ApiParam("Get user authentication detail by email id ") @RequestParam String emailId) {
		return userAuthService.findUserAuthenticationByEmailId(emailId);
	}
	
	@GetMapping(value = "/userAuth/emailId/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View user authentication login by email id and password")
	public ResponseEntity<Object> getUserAuthDetailLogin(
			@ApiParam("Get user authentication login by email id")@RequestParam String emailId,
			@ApiParam("Get user authentication login by password")@RequestParam String password){
		return userAuthService.findUserAuthenticationLogin(emailId,password);
	}
	
	@GetMapping(value = "/userAuth/emailId/otp", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "View user authentication by email id and OTP")
	public ResponseEntity<Object> getUserAuthDetailOTP(
			@ApiParam("Get user authentication login by email id")@RequestParam String emailId,
			@ApiParam("Get user authentication login by password")@RequestParam String otp){
		return userAuthService.findUserAuthenticationOTP(emailId,otp);
	}
	
	@PostMapping(value = "/userAuth", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create user authentication detail")
	public ResponseEntity<Object> addUserAuthDetail(
			@ApiParam(value = "User authentication detail") @RequestBody UserAuthDTO userAuthDTO) {
		return userAuthService.saveUserAuthenticationDetail(userAuthDTO);
	}
	
	@PutMapping(value = "/userAuth/{userId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update user authentication detail")
	public ResponseEntity<Object> updateUserAuthenticationDetail(
			@ApiParam(value = "User authentication detail update by user id") @PathVariable Integer userId,
			@ApiParam(value = "User authentication detail")  @RequestBody UserAuthDTO userAuthDTO) {
		return userAuthService.updateUserAuthenticationDetailByUserId(userId, userAuthDTO);
	}
	
	@PutMapping(value = "/userAuth/emailId/changePassword", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update user authentication password by emil id")
	public ResponseEntity<Object> updateUserAuthenticationPassword(
			@ApiParam("Get user authentication login by email id")@RequestParam String emailId,
			@ApiParam("Get user authentication login by password")@RequestParam String password) {
		return userAuthService.updateUserAuthenticationPasswordByEmailId(emailId, password);
	}
	
	@PutMapping(value = "/customerData/otp", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update user authentication OTP by email id")
	public ResponseEntity<Object> updateUserAuthenticationOTP(
			@ApiParam("Get user authentication by email id")@RequestParam String emailId,
			@ApiParam("Get user authentication by OTP")@RequestParam String otp) {
		return userAuthService.updateUserAuthenticationOTPDetailByEmailId(emailId, otp);
	}

	@DeleteMapping("/userAuth/{userId}")
	@ApiOperation(value = "Delete user authentication detail")
	public ResponseEntity<String> deleteUserAuthenticationDetail(
			@ApiParam(value = "Delete user authentication detail by user id") @PathVariable Integer userId) {
		return userAuthService.deleteUserAuthenticationDetailByUserId(userId);
	}	
	

}
