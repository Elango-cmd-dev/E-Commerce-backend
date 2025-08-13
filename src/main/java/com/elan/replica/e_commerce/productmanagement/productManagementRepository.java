package com.elan.replica.e_commerce.productmanagement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This is repository class for product management details. ...
 * 
 * @author ELANGO
 */

@Repository
public interface productManagementRepository extends JpaRepository<ProductManagementEntity, Integer>{

	Optional<ProductManagementEntity> findByProductName(String productName);

	@Query("SELECT u FROM ProductManagementEntity u WHERE u.productPrice BETWEEN productStartPrice = ?1  AND productEndPrice = ?2")
	List<ProductManagementEntity> findByProductPrice(Integer productStartPrice, Integer productEndPrice);

	List<ProductManagementEntity> findByProductCategory(String productCategory);

}
