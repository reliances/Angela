package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.OrderProduct;
import com.web.app.mapper.OrderProductMapper;

@Service("OrderProductService")
public class OrderProductService {
	@Autowired
	private OrderProductMapper orderProductMapper;

	public int insertOrderProduct(OrderProduct orderProduct) {
		return orderProductMapper.insertOrderProduct(orderProduct);
	}

	public List<OrderProduct> getAllOrderProduct(Map<String, Object> map) {
		return orderProductMapper.getAllOrderProduct(map);
	}

	public int countByCriteria() {
		return orderProductMapper.countByCriteria();
	}

	public int deleteOrderProductById(String[] orderIds) {
		return orderProductMapper.deleteOrderProductById(orderIds);
	}

	public int updateOrderProductById(OrderProduct orderProduct) {
		return orderProductMapper.updateOrderProductById(orderProduct);
	}

	public List<OrderProduct> getOrderProductById(String orderId) {
		return orderProductMapper.getOrderProductById(orderId);
	}
	
 
	
}
