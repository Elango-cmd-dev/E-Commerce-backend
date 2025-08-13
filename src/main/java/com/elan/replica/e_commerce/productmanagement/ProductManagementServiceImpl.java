package com.elan.replica.e_commerce.productmanagement;

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
 * This is service implement class for product management details. ...
 * 
 * @author ELANGO
 */

@Service
public class ProductManagementServiceImpl implements ProductManagementService{
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private productManagementRepository productManagementRepository;

	@Override
	public List<ProductManagementDTO> findProductManagementDetails() {
		List<ProductManagementEntity> productManagementEntities = productManagementRepository.findAll();
		List<ProductManagementDTO> productManagementDTOs = productManagementEntities.stream().map(this::productManagementEntityToDTO).collect(Collectors.toList());
		return productManagementDTOs;
	}

	private ProductManagementDTO productManagementEntityToDTO(ProductManagementEntity productManagementEntity) {
		ProductManagementDTO productManagementDTO = new ProductManagementDTO();
		productManagementDTO.setProductId(productManagementEntity.getProductId());
		productManagementDTO.setProductName(productManagementEntity.getProductName());
		productManagementDTO.setProductDescription(productManagementEntity.getProductDescription());
		productManagementDTO.setAvailableQuentity(productManagementEntity.getAvailableQuentity());
		productManagementDTO.setProductPrice(productManagementEntity.getProductPrice());
		productManagementDTO.setProductImage(productManagementEntity.getProductImage());
		productManagementDTO.setWhenCreatedIST(productManagementEntity.getWhenCreatedIST());
		productManagementDTO.setCreatedBy(productManagementEntity.getCreatedBy());
		productManagementDTO.setWhenUpdatedIST(productManagementEntity.getWhenUpdatedIST());
		productManagementDTO.setUpdatedBy(productManagementEntity.getUpdatedBy());
		return productManagementDTO;
	}

	@Override
	public ResponseEntity<Object> findProductManagementDetailByProductId(Integer productId) {
		Optional<ProductManagementEntity> i;
		try {
			i = productManagementRepository.findById(productId);
			if (!i.isPresent()) {
				ProductManagementDTO productManagementDTO = productManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(productManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Product management detail does not exist.Please enter correct product id");
			}
		} catch (Exception e) {
			String message = "Failed to get product management detail through product id";
			log.error(message + " {} ", productId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", productId));
		}
	}

	@Override
	public ResponseEntity<Object> saveProductManagementDetail(ProductManagementDTO productManagementDTO) {
		ProductManagementEntity productManagementEntity = new ProductManagementEntity();
		productManagementEntity.setProductName(productManagementDTO.getProductName());
		productManagementEntity.setProductCategories(productManagementDTO.getProductCategories());
		productManagementEntity.setProductDescription(productManagementDTO.getProductDescription());
		productManagementEntity.setProductPrice(productManagementDTO.getProductPrice());
		productManagementEntity.setWhenCreatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
		productManagementEntity.setCreatedBy(CommonConstant.ADMIN);
		ProductManagementEntity savedProductManagementEntity = productManagementRepository.save(productManagementEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProductManagementEntity);
	}

	@Override
	public ResponseEntity<Object> updateProductManagementDetailByProductId(Integer productId,
			ProductManagementDTO productManagementDTO) {
		return productManagementRepository.findById(productId).map(record -> {
		record.setProductName(productManagementDTO.getProductName());
		record.setProductCategories(productManagementDTO.getProductCategories());
		record.setProductDescription(productManagementDTO.getProductDescription());
		record.setProductPrice(productManagementDTO.getProductPrice());
		record.setWhenUpdatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
		record.setUpdatedBy(productManagementDTO.getUpdatedBy());
		return ResponseEntity.ok().body((Object) productManagementRepository.save(record));
	}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(String.format("Product management detail by product id - %d does not exists", productId)));
	}

	@Override
	public ResponseEntity<String> deleteProductManagementDetailByProductId(Integer productId) {
		return productManagementRepository.findById(productId).map(record -> {
			productManagementRepository.delete(record);
			return ResponseEntity.status(HttpStatus.OK).body("Product management detail deleted successfully");
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("Product management detail by product id - %d does not exists", productId)));
	}

	@Override
	public ResponseEntity<Object> findProductManagementDetailByProductName(String productName) {
		Optional<ProductManagementEntity> i;
		try {
			i = productManagementRepository.findByProductName(productName);
			if (!i.isPresent()) {
				ProductManagementDTO productManagementDTO = productManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(productManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Product management detail does not exist.Please enter correct product name");
			}
		} catch (Exception e) {
			String message = "Failed to get product management detail through product name";
			log.error(message + " {} ", productName);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", productName));
		}
	}

	@Override
	public ResponseEntity<Object> findProductManagementDetailByProductPrice(Integer productStartPrice,
			Integer productEndPrice) {
		List<ProductManagementEntity> i;
		try {
			i = productManagementRepository.findByProductPrice(productStartPrice,productEndPrice);
			if (i != null) {
				List<ProductManagementDTO> productManagementDTOs = i.stream().map(this::productManagementEntityToDTO).collect(Collectors.toList());
				return ResponseEntity.status(HttpStatus.OK).body(productManagementDTOs);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Product management detail does not exist.Please enter correct product price");
			}
		} catch (Exception e) {
			String message = "Failed to get product management detail through product price";
			log.error(message + " {} ", productStartPrice + "to"+ productStartPrice);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again"+ productStartPrice + "to" + productStartPrice));
		}
	}

	@Override
	public ResponseEntity<Object> findProductManagementDetailByProductCategory(String productCategory) {
		List<ProductManagementEntity> i;
		try {
			i = productManagementRepository.findByProductCategory(productCategory);
			if (i != null) {
				List<ProductManagementDTO> productManagementDTOs = i.stream().map(this::productManagementEntityToDTO).collect(Collectors.toList());
				return ResponseEntity.status(HttpStatus.OK).body(productManagementDTOs);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Product management detail does not exist.Please enter correct product category");
			}
		} catch (Exception e) {
			String message = "Failed to get product management detail through product category";
			log.error(message + " {} ", productCategory);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again"+ productCategory));
		}
	}

}
