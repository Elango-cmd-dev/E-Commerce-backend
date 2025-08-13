package com.elan.replica.e_commerce.inventorymanagement;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is repository class for inventory management detail....
 * 
 * @author ELANGO
 */

@Repository
public interface InventoryManagementRepository extends JpaRepository<InventoryManagementEntity, Integer>{

	Optional<InventoryManagementEntity> findByInventoryId(Integer inventoryId);

	Optional<InventoryManagementEntity> findByProductId(Integer productId);

}
