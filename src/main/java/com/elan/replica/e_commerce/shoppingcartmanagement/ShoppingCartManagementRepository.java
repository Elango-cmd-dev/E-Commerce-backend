package com.elan.replica.e_commerce.shoppingcartmanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is repository class for shopping cart management details. ...
 * 
 * @author ELANGO
 */

@Repository
public interface ShoppingCartManagementRepository extends JpaRepository<ShoppingCartManagementEntity, Integer> {

}
