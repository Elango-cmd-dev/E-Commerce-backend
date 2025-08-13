package com.elan.replica.e_commerce.userauth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This is repository class for user authentication details. ...
 * 
 * @author ELANGO
 */

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer>{
	
	@Query("SELECT u FROM UserAuthEntity u WHERE u.emailId = ?1 AND u.password = ?2")
	Optional<UserAuthEntity> findByEmailIdAndPassword(String emailId, String password);

	Optional<UserAuthEntity> findByEmailId(String emailId);

	@Query("SELECT u FROM UserAuthEntity u WHERE u.emailId = ?1 AND u.otp = ?2")
	Optional<UserAuthEntity> findByEmailIdAndOTP(String emailId, String otp);

	Optional<UserAuthEntity> findByPhoneNumber(String phoneNumber);

}
