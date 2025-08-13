package com.elan.replica.e_commerce.ordermanagement;

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
 * This is service implement class for order management detail....
 * 
 * @author ELANGO
 */

@Service
public class OrderManagementServiceImpl implements OrderManagementService{
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private OrderManagementRepository orderManagementRepository;

	@Override
	public List<OrderManagementDTO> findOrderManagementDetails() {
		List<OrderManagementEntity> orderManagementEntities = orderManagementRepository.findAll();
		List<OrderManagementDTO> orderManagementDTOs = orderManagementEntities.stream().map(this::orderManagementEntityToDTO).collect(Collectors.toList());
		return orderManagementDTOs;
	}

	private OrderManagementDTO orderManagementEntityToDTO(OrderManagementEntity orderManagementEntity) {
		OrderManagementDTO orderManagementDTO = new OrderManagementDTO();
		orderManagementDTO.setOrderId(orderManagementEntity.getOrderId());
		orderManagementDTO.setProductId(orderManagementEntity.getProductId());
		orderManagementDTO.setUserId(orderManagementEntity.getUserId());
		orderManagementDTO.setQuantity(orderManagementEntity.getQuantity());
		orderManagementDTO.setTotalPrice(orderManagementEntity.getTotalPrice());
		orderManagementDTO.setOrderDate(orderManagementEntity.getOrderDate());
		orderManagementDTO.setWhenCreatedIST(orderManagementEntity.getWhenCreatedIST());
		orderManagementDTO.setCreatedBy(orderManagementEntity.getCreatedBy());
		orderManagementDTO.setWhenUpdatedIST(orderManagementEntity.getWhenUpdatedIST());
		orderManagementDTO.setUpdatedBy(orderManagementEntity.getUpdatedBy());
		return orderManagementDTO;
	}

	@Override
	public ResponseEntity<Object> findOrderManagementDetailByOrderId(Integer orderId) {
		Optional<OrderManagementEntity> i;
		try {
			i = orderManagementRepository.findById(orderId);
			if (!i.isPresent()) {
				OrderManagementDTO orderManagementDTO = orderManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(orderManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Order management detail does not exist.Please enter correct order id");
			}
		} catch (Exception e) {
			String message = "Failed to get order management detail through order id";
			log.error(message + " {} ", orderId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", orderId));
		}
	}

	@Override
	public ResponseEntity<Object> findOrderManagementDetailByProductId(Integer productId) {
		Optional<OrderManagementEntity> i;
		try {
			i = orderManagementRepository.findByProductId(productId);
			if (!i.isPresent()) {
				OrderManagementDTO orderManagementDTO = orderManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(orderManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Order management detail does not exist.Please enter correct product id");
			}
		} catch (Exception e) {
			String message = "Failed to get order management detail through product id";
			log.error(message + " {} ", productId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", productId));
		}
	}

	@Override
	public ResponseEntity<Object> findOrderManagementDetailByUserId(Integer userId) {
		Optional<OrderManagementEntity> i;
		try {
			i = orderManagementRepository.findByOrderId(userId);
			if (!i.isPresent()) {
				OrderManagementDTO orderManagementDTO = orderManagementEntityToDTO(i.get());
				return ResponseEntity.status(HttpStatus.OK).body(orderManagementDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Order management detail does not exist.Please enter correct user id");
			}
		} catch (Exception e) {
			String message = "Failed to get order management detail through user id";
			log.error(message + " {} ", userId);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format(message + "%d, Try Again", userId));
		}
	}

	@Override
	public ResponseEntity<Object> saveOrderManagementDetail(OrderManagementDTO orderManagementDTO) {
		OrderManagementEntity orderManagementEntity = new OrderManagementEntity();
		orderManagementEntity.setProductId(orderManagementDTO.getProductId());
		orderManagementEntity.setUserId(orderManagementDTO.getUserId());
		orderManagementEntity.setQuantity(orderManagementDTO.getQuantity());
		orderManagementEntity.setTotalPrice(orderManagementDTO.getTotalPrice());
		orderManagementEntity.setOrderDate(orderManagementDTO.getOrderDate());
		orderManagementEntity.setWhenCreatedIST(Timestamp.valueOf(CommonConstant.getWhenUpdatedIST()));
		orderManagementEntity.setCreatedBy(orderManagementDTO.getUserId().getUserName());
		OrderManagementEntity savedOrdermanagementEntity = orderManagementRepository.save(orderManagementEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrdermanagementEntity);
	}

	@Override
	public ResponseEntity<String> deleteOrderManagementDetailByOrderId(Integer orderId) {
		return orderManagementRepository.findById(orderId).map(record -> {
			orderManagementRepository.delete(record);
			return ResponseEntity.status(HttpStatus.OK).body("Order management detail deleted successfully");
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("Order management detail by order id - %d does not exists", orderId)));
	}

}
