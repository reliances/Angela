package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.OrderProduct;


/**
 * @Title:OrderProductMapper     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月29日 下午9:39:42       
 * @version V1.0
 */
public interface OrderProductMapper {
	// 添加订单产品
	public int insertOrderProduct(OrderProduct orderProduct);

	// 查询订单产品
	public List<OrderProduct> getAllOrderProduct(Map<String, Object> map);

	// 查询订单产品个数
	public int countByCriteria();

	// 删除订单产品
	public int deleteOrderProductById(String[] orderIds);

	// 编辑订单产品
	public int updateOrderProductById(OrderProduct orderProduct);

	// 根据id查询产品
	public List<OrderProduct> getOrderProductById(String orderId);

}
