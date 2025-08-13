package com.elan.replica.e_commerce.shoppingcartmanagement;

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

/**
 * This is service implement class for shopping cart management details. ...
 * 
 * @author ELANGO
 */

@Service
public class ShoppingCartManagementServiceImpl implements ShoppingCartManagementService{

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private ShoppingCartManagementRepository shoppingCartManagementRepository;
	@Override
	public List<ShoppingCartManagementDTO> findCartManagementDetails() {
		List<ShoppingCartManagementEntity> shoppingCartManagementEntities = shoppingCartManagementRepository.findAll();
		List<ShoppingCartManagementDTO> shoppingCartManagementDTOs = shoppingCartManagementEntities.stream().map(this::cartManagementEntityToDTO).collect(Collectors.toList());
		return shoppingCartManagementDTOs;
	}

	private ShoppingCartManagementDTO cartManagementEntityToDTO(ShoppingCartManagementEntity shoppingCartManagementEntity) {
		ShoppingCartManagementDTO shoppingCartManagementDTO = new ShoppingCartManagementDTO();
		shoppingCartManagementDTO.setProductId(shoppingCartManagementEntity.getProductId());
		shoppingCartManagementDTO.setUserId(shoppingCartManagementEntity.getUserId());
		shoppingCartManagementDTO.setQuantity(shoppingCartManagementEntity.getQuantity());
		shoppingCartManagementDTO.setTotalPrice(shoppingCartManagementEntity.getTotalPrice());
		shoppingCartManagementDTO.setWhenCreatedIST(shoppingCartManagementEntity.getWhenCreatedIST());
		shoppingCartManagementDTO.setCreatedBy(shoppingCartManagementEntity.getCreatedBy());
		shoppingCartManagementDTO.setWhenUpdatedIST(shoppingCartManagementEntity.getWhenUpdatedIST());
		shoppingCartManagementDTO.setUpdatedBy(shoppingCartManagementEntity.getUpdatedBy());
		return shoppingCartManagementDTO;
	}

	@Override
	public ResponseEntity<Object> findCartManagementDetailByCartId(Integer cartId) {
		Optional<ShoppingCartManagementEntity> i;
		try {
			i = shoppingCartManagementRepository.findById(cartId);
			if (!i.isPresent()) {
				ShoppingCartManagementDTO shoppingCartManagementDTO = cartManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(shoppingCartManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Shopping cart management detail does not exist.Please enter correct cart id");
			}
		} catch (Exception e) {
			String message = "Failed to get shopping cart management detail through cart id";
			log.error(message + " {} ", cartId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", cartId));
		}
	}

	@Override
	public ResponseEntity<Object> saveCartManagementDetail(ShoppingCartManagementDTO shoppingCartManagementDTO) {
		ShoppingCartManagementEntity shoppingCartManagementEntity = new ShoppingCartManagementEntity();
		shoppingCartManagementEntity.setCartId(shoppingCartManagementDTO.getCartId());
		shoppingCartManagementEntity.setProductId(shoppingCartManagementDTO.getProductId());
		shoppingCartManagementEntity.setUserId(shoppingCartManagementDTO.getUserId());
		shoppingCartManagementEntity.setQuantity(shoppingCartManagementDTO.getQuantity());
		shoppingCartManagementEntity.setTotalPrice(shoppingCartManagementDTO.getTotalPrice());
		shoppingCartManagementEntity.setWhenCreatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
		shoppingCartManagementEntity.setCreatedBy(shoppingCartManagementDTO.getUserId().getUserName());
		ShoppingCartManagementEntity savedShoppingCartEntity = shoppingCartManagementRepository.save(shoppingCartManagementEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedShoppingCartEntity);
	}

	@Override
	public ResponseEntity<String> deleteCartManagementDetailByCartId(Integer cartId) {
		return shoppingCartManagementRepository.findById(cartId).map(record -> {
			shoppingCartManagementRepository.delete(record);
			return ResponseEntity.status(HttpStatus.OK).body("Shopping cart management detail deleted successfully");
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("Shopping cart management detail by cart id - %d does not exists", cartId)));
	}

	@Override
	public ResponseEntity<Object> findCartManagementDetailByProductId(Integer productId) {
		Optional<ShoppingCartManagementEntity> i;
		try {
			i = shoppingCartManagementRepository.findById(productId);
			if (!i.isPresent()) {
				ShoppingCartManagementDTO shoppingCartManagementDTO = cartManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(shoppingCartManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Shopping cart management detail does not exist.Please enter correct product id");
			}
		} catch (Exception e) {
			String message = "Failed to get shopping cart management detail through product id";
			log.error(message + " {} ", productId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", productId));
		}
	}

	@Override
	public ResponseEntity<Object> findCartManagementDetailByUserId(Integer userId) {
		Optional<ShoppingCartManagementEntity> i;
		try {
			i = shoppingCartManagementRepository.findById(userId);
			if (!i.isPresent()) {
				ShoppingCartManagementDTO shoppingCartManagementDTO = cartManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(shoppingCartManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Shopping cart management detail does not exist.Please enter correct user id");
			}
		} catch (Exception e) {
			String message = "Failed to get shopping cart management detail through user id";
			log.error(message + " {} ", userId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", userId));
		}
	}

}
