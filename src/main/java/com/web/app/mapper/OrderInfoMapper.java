package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.OrderInfo;



/**
 * @Title:CaseInfoMapper
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月17日 下午16:35:06
 * @version V1.0
 */
public interface OrderInfoMapper {
	// 添加订单
	public int insertOrderInfo(OrderInfo orderInfo);

	// 查询订单
	public List<OrderInfo> getAllOrderInfo(Map<String, Object> map);

	// 查询订单个数
	public int countByCriteria();

	// 删除订单
	public int deleteOrderInfoById(String[] orderIds);

	// 编辑订单
	public int updateOrderInfoById(OrderInfo caseInfo);

	// 根据id查询
	public OrderInfo getOrderInfoById(String id);

}
