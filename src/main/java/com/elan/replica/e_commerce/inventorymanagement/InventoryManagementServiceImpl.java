package com.elan.replica.e_commerce.inventorymanagement;

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
 * This is service implement class for inventory management detail....
 * 
 * @author ELANGO
 */

@Service
public class InventoryManagementServiceImpl implements InventoryManagementService{
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private InventoryManagementRepository inventoryManagementRepository;
	
	@Override
	public List<InventoryManagementDTO> findInventoryManagementDetails() {
		List<InventoryManagementEntity> inventoryManagementEntities = inventoryManagementRepository.findAll();
		List<InventoryManagementDTO> inventoryManagementDTOs = inventoryManagementEntities.stream().map(this::inventoryManagementEntityToDTO).collect(Collectors.toList());
		return inventoryManagementDTOs;
	}

	private InventoryManagementDTO inventoryManagementEntityToDTO(InventoryManagementEntity inventoryManagementEntity) {
		InventoryManagementDTO inventoryManagementDTO = new InventoryManagementDTO();
		inventoryManagementDTO.setInventoryId(inventoryManagementEntity.getInventoryId());
		inventoryManagementDTO.setProductId(inventoryManagementEntity.getProductId());
		inventoryManagementDTO.setMovementType(inventoryManagementEntity.getMovementType());
		inventoryManagementDTO.setProductQuantity(inventoryManagementEntity.getProductQuantity());
		inventoryManagementDTO.setMovementDate(inventoryManagementEntity.getMovementDate());
		inventoryManagementDTO.setWhenCreatedIST(inventoryManagementEntity.getWhenCreatedIST());
		inventoryManagementDTO.setCreatedBy(inventoryManagementEntity.getCreatedBy());
		inventoryManagementDTO.setWhenUpdatedIST(inventoryManagementEntity.getWhenUpdatedIST());
		inventoryManagementDTO.setUpdatedBy(inventoryManagementEntity.getUpdatedBy());
		return inventoryManagementDTO;
	}

	@Override
	public ResponseEntity<Object> findInventoryManagementDetailByInventoryId(Integer inventoryId) {
		Optional<InventoryManagementEntity> i;
		try {
			i = inventoryManagementRepository.findByInventoryId(inventoryId);
			if (!i.isPresent()) {
				InventoryManagementDTO inventoryManagementDTO = inventoryManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(inventoryManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Inventory management detail does not exist.Please enter correct inventory id");
			}
		} catch (Exception e) {
			String message = "Failed to get inventory management detail through inventory id";
			log.error(message + " {} ", inventoryId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", inventoryId));
		}
	}

	@Override
	public ResponseEntity<Object> findInventoryManagementDetailByProductId(Integer productId) {
		Optional<InventoryManagementEntity> i;
		try {
			i = inventoryManagementRepository.findByProductId(productId);
			if (!i.isPresent()) {
				InventoryManagementDTO inventoryManagementDTO = inventoryManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(inventoryManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Inventory management detail does not exist.Please enter correct product id");
			}
		} catch (Exception e) {
			String message = "Failed to get inventory management detail through product id";
			log.error(message + " {} ", productId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", productId));
		}
	}

	@Override
	public ResponseEntity<Object> saveInventoryManagementDetail(InventoryManagementDTO inventoryManagementDTO) {
		InventoryManagementEntity inventoryManagementEntity = new InventoryManagementEntity();
		inventoryManagementEntity.setProductId(inventoryManagementDTO.getProductId());
		inventoryManagementEntity.setMovementType(inventoryManagementDTO.getMovementType());
		inventoryManagementEntity.setProductQuantity(inventoryManagementDTO.getProductQuantity());
		inventoryManagementEntity.setMovementDate(inventoryManagementDTO.getMovementDate());
		inventoryManagementEntity.setWhenCreatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
		inventoryManagementEntity.setCreatedBy(CommonConstant.ADMIN);
		InventoryManagementEntity savedInventoryManagementEntity = inventoryManagementRepository.save(inventoryManagementEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedInventoryManagementEntity);
	}

	@Override
	public ResponseEntity<Object> updateInventoryManagementDetailByInventoryId(Integer inventoryId,
			InventoryManagementDTO inventoryManagementDTO) {
		return inventoryManagementRepository.findByInventoryId(inventoryId).map(record -> {
		record.setMovementType(inventoryManagementDTO.getMovementType());
		record.setProductQuantity(inventoryManagementDTO.getProductQuantity());
		record.setMovementDate(inventoryManagementDTO.getMovementDate());
		record.setWhenUpdatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
		record.setUpdatedBy(inventoryManagementDTO.getUpdatedBy());
		return ResponseEntity.ok().body((Object) inventoryManagementRepository.save(record));
	}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(String.format("Inventory management detail by inventory id - %d does not exists", inventoryId)));
	}

	@Override
	public ResponseEntity<String> deleteInventoryManagementDetailByInventoryId(Integer inventoryId) {
		return inventoryManagementRepository.findByInventoryId(inventoryId).map(record -> {
			inventoryManagementRepository.delete(record);
			return ResponseEntity.status(HttpStatus.OK).body("Inventory management detail deleted successfully");
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("Inventory management detail by inventory id - %d does not exists", inventoryId)));
	}

}
