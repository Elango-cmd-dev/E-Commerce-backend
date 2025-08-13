package com.elan.replica.e_commerce.ordermanagement;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is repository class for order management detail....
 * 
 * @author ELANGO
 */

@Repository
public interface OrderManagementRepository extends JpaRepository<OrderManagementEntity, Integer>{

	Optional<OrderManagementEntity> findByProductId(Integer productId);

	Optional<OrderManagementEntity> findByOrderId(Integer userId);

}
