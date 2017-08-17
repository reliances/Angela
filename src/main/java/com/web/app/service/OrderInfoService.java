package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.OrderInfo;
import com.web.app.mapper.OrderInfoMapper;

@Service("OrderInfoService")
public class OrderInfoService {
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	public int insertOrderInfo(OrderInfo orderInfo) {
		return orderInfoMapper.insertOrderInfo(orderInfo);
	}

	public List<OrderInfo> getAllOrderInfo(Map<String, Object> map) {
		return orderInfoMapper.getAllOrderInfo(map);
	}

	public int countByCriteria() {
		return orderInfoMapper.countByCriteria();
	}

	public int deleteOrderInfoById(String[] orderIds) {
		return orderInfoMapper.deleteOrderInfoById(orderIds);
	}

	public int updateOrderInfoById(OrderInfo caseInfo) {
		return orderInfoMapper.updateOrderInfoById(caseInfo);
	}

	public OrderInfo getOrderInfoById(String id) {
		return orderInfoMapper.getOrderInfoById(id);
	}

}
