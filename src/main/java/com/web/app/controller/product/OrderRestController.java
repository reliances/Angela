package com.web.app.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.app.controller.BaseController;
import com.web.app.entity.OrderInfo;
import com.web.app.entity.OrderProduct;
import com.web.app.entity.Product;
import com.web.app.service.OrderInfoService;
import com.web.app.service.OrderProductService;
import com.web.app.service.ProductService;
import com.web.app.tools.DateTools;

/**
 * @Title:OrderRestController     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月18日 下午1:49:03       
 * @version V1.0
 */
@RestController
@RequestMapping("/orders")
public class OrderRestController extends BaseController {
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private ProductService productService;
	
	/**
	 * @Title: postAddOrders
	 * @Description:
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @retrun JSONObject
	 * @author LiangRui
	 * @throws
	 * @Time 2017年8月18日 下午2:00:38
	 */
	@RequestMapping("/postAddOrders")
	public JSONObject postAddOrders(@RequestBody JSONObject jsObj,HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObj = new JSONObject();
		OrderInfo orderInfo = (OrderInfo) JSON.parseObject(jsObj.toString(), OrderInfo.class);
		String orderId = UUID.randomUUID().toString();
		orderInfo.setId(orderId);
		orderInfo.setCreateDate(DateTools.getCurrentTime());
		int count = orderInfoService.insertOrderInfo(orderInfo);
		for (int i = 0; i < orderInfo.getOrderProduct().size(); i++) {
			OrderProduct odp = orderInfo.getOrderProduct().get(i);
			odp.setOrderId(orderId);
			odp.setId(UUID.randomUUID().toString());
			odp.setCreateDate(DateTools.getCurrentTime());
			orderProductService.insertOrderProduct(orderInfo.getOrderProduct().get(i));
		}
		if(count > 0){
			jsonObj.put("status", "200");
			jsonObj.put("return", "success");
		}else{
			jsonObj.put("status", "500");
			jsonObj.put("return", "error");
		}
		return jsonObj;
	}
	
	
	/**
	 * @Title: getOrders
	 * @Description:
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @retrun JSONObject
	 * @author LiangRui
	 * @throws
	 * @Time 2017年8月31日 下午4:21:48
	 */
	@RequestMapping("/getOrders")
	public JSONObject getOrders(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObj = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		String userId = request.getParameter("userId");
		map.put("userId", userId);
		map.put("createDate", "createDate");
		if(null != userId && !userId.equals("")){
			List<OrderInfo> order = orderInfoService.getAllOrderInfo(map);
			for (int i = 0; i < order.size(); i++) {
				List<OrderProduct> product = orderProductService.getOrderProductById(order.get(i).getId());
				if(null != product){
					for (int j = 0; j < product.size(); j++) {
						Product pd = new Product();
						pd = productService.getProductById(product.get(j).getProductId());
						product.get(j).setProduct(pd);
					}
					order.get(i).setOrderProduct(product);
				}
			}
			jsonObj.put("OrderInfo", JSON.toJSON(order));
		}else{
			jsonObj.put("error", "用户ID不能为空!");
		}
		return jsonObj;
	}
	 
}


