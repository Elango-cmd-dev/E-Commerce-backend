package com.elan.replica.e_commerce.userauth;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.elan.replica.e_commerce.util.CommonConstant;
import com.elan.replica.e_commerce.util.MailUtil;

/**
 * This is service implement class for user authentication details. ...
 * 
 * @author ELANGO
 */

@Service
public class UserAuthServiceImpl implements UserAuthService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private UserAuthRepository userAuthRepository;

	@Autowired
	private MailUtil mailUtil;
	
	@Override
	public List<UserAuthDTO> findUserAuthenticationDetails() {
		List<UserAuthEntity> userAuthEntities = userAuthRepository.findAll();
		List<UserAuthDTO> userAuthDTOs = userAuthEntities.stream().map(this::userAuthenticationEntityToDTO).collect(Collectors.toList());
		return userAuthDTOs;
	}

	private UserAuthDTO userAuthenticationEntityToDTO(UserAuthEntity userAuthEntity) {
		UserAuthDTO userAuthDTO = new UserAuthDTO();
		userAuthDTO.setUserId(userAuthEntity.getUserId());
		userAuthDTO.setUserName(userAuthEntity.getUserName());
		userAuthDTO.setEmailId(userAuthEntity.getEmailId());
		userAuthDTO.setPhoneNumber(userAuthEntity.getPhoneNumber());
		userAuthDTO.setPassword(userAuthEntity.getPassword());
		userAuthDTO.setWhenCreatedIST(userAuthEntity.getWhenCreatedIST());
		userAuthDTO.setCreatedBy(userAuthEntity.getCreatedBy());
		userAuthDTO.setWhenUpdatedIST(userAuthEntity.getWhenUpdatedIST());
		userAuthDTO.setUpdatedBy(userAuthEntity.getUpdatedBy());
		return userAuthDTO;
	}

	@Override
	public ResponseEntity<Object> findUserAuthenticationDetailByUserId(Integer userId) {
		Optional<UserAuthEntity> i;
		try {
			i = userAuthRepository.findById(userId);
			if (!i.isPresent()) {
				UserAuthDTO userAuthDTO = userAuthenticationEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(userAuthDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("User authentication detail does not exist.Please enter correct user id");
			}
		} catch (Exception e) {
			String message = "Failed to get user authentication detail through user id";
			log.error(message + " {} ", userId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", userId));
		}
	}

	@Override
	public ResponseEntity<Object> saveUserAuthenticationDetail(UserAuthDTO userAuthDTO) {
		Optional<UserAuthEntity> i = userAuthRepository.findByEmailId(userAuthDTO.getEmailId());
		Optional<UserAuthEntity> j = userAuthRepository.findByPhoneNumber(userAuthDTO.getPhoneNumber());
		if (i != null && j != null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email id or phone number already exist");
		}
		UserAuthEntity userAuthEntity = new UserAuthEntity();
		String otpValue = mailUtil.generateOtp();
		userAuthEntity.setUserName(userAuthDTO.getUserName());
		userAuthEntity.setEmailId(userAuthDTO.getEmailId());
		userAuthEntity.setPhoneNumber(userAuthDTO.getPhoneNumber());
		userAuthEntity.setPassword(userAuthDTO.getPassword());
		userAuthEntity.setOtp(otpValue);
		userAuthEntity.setVerified(CommonConstant.NOT_VERIFIED);
		userAuthEntity.setWhenCreatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
		userAuthEntity.setCreatedBy(userAuthDTO.getUserName());
		   try {
		        mailUtil.sendOtpEmail(userAuthDTO.getEmailId(), otpValue);
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send OTP email: " + e.getMessage());
		    }
		
		UserAuthEntity savedUserEntity = userAuthRepository.save(userAuthEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUserEntity);
	}

	@Override
	public ResponseEntity<Object> updateUserAuthenticationDetailByUserId(Integer userId,
			UserAuthDTO userAuthDTO) {
		return userAuthRepository.findById(userId).map(record -> {
		record.setUserName(userAuthDTO.getUserName());
		record.setEmailId(userAuthDTO.getEmailId());
		record.setPhoneNumber(userAuthDTO.getPhoneNumber());
		record.setPassword(userAuthDTO.getPassword());
		record.setWhenUpdatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
		record.setUpdatedBy(userAuthDTO.getUpdatedBy());
		return ResponseEntity.ok().body((Object) userAuthRepository.save(record));
	}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(String.format("User authentication detail by user id - %d does not exists", userId)));
	}

	@Override
	public ResponseEntity<String> deleteUserAuthenticationDetailByUserId(Integer userId) {
		return userAuthRepository.findById(userId).map(record -> {
			userAuthRepository.delete(record);
			return ResponseEntity.status(HttpStatus.OK).body("User authentication detail deleted successfully");
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("User authentication detail by user id - %d does not exists", userId)));
	}

	@Override
	public ResponseEntity<Object> findUserAuthenticationLogin(String emailId, String password) {
		Optional<UserAuthEntity> i;
		try {
			i = userAuthRepository.findByEmailIdAndPassword(emailId, password);
			if (i.isPresent()) {
				UserAuthDTO userAuthDTO = userAuthenticationEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(userAuthDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("User Authentication detail does not exists.Please enter correct email id or password");
			}
		} catch (Exception e) {
			String message = "Failed to get user authentication detail through email id or password";
			log.error(message + " {} ", emailId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", emailId));
		}
	}
	
	@Override
	public ResponseEntity<Object> findUserAuthenticationByEmailId(String emailId) {
		Optional<UserAuthEntity> i;
		try {
			i = userAuthRepository.findByEmailId(emailId);
			if (i.isPresent()) {
				UserAuthDTO userAuthDTO = userAuthenticationEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(userAuthDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("customer data detail does not exists.Please enter correct email id");
			}
		} catch (Exception e) {
			String message = "Failed to get customer data details through email id";
			log.error(message + " {} ", emailId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", emailId));
		}
	}


	@Override
	public ResponseEntity<Object> findUserAuthenticationOTP(String emailId, String otp) {
		Optional<UserAuthEntity> i;
		try {
			i = userAuthRepository.findByEmailIdAndOTP(emailId, otp);
			if (i.isPresent()) {
				UserAuthDTO userAuthDTO = userAuthenticationEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(userAuthDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("customer data detail does not exists.Please enter correct OTP!");
			}
		} catch (Exception e) {
			String message = "Failed to get customer data details through OTP!";
			log.error(message + " {} ", otp);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", otp));
		}
	}

	@Override
	public ResponseEntity<Object> updateUserAuthenticationPasswordByEmailId(String emailId, String newPassword) {
		return userAuthRepository.findByEmailId(emailId).map(record -> {
			record.setPassword(newPassword);
			record.setWhenUpdatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
			record.setUpdatedBy(record.getUserName());
			return ResponseEntity.ok().body((Object) userAuthRepository.save(record));
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("User authentication password and email id does not exists", emailId)));
	}

	@Override
	public ResponseEntity<Object> updateUserAuthenticationOTPDetailByEmailId(String emailId, String otp) {
		return userAuthRepository.findByEmailId(emailId).map(record -> {
			if(!otp.equals(record.getOtp())) {
				return ResponseEntity.ok().body((Object) "OTP not match");
			}
			record.setVerified(CommonConstant.VERIFIED);
			record.setWhenUpdatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
			record.setUpdatedBy(record.getUserName());
			mailUtil.emailSuccesssSend(record.getEmailId(), record.getUserName());
			return ResponseEntity.ok().body((Object) userAuthRepository.save(record));
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("User authentication otp by email id does not exists", emailId)));
	}
}
