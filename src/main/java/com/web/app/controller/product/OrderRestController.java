package com.web.app.controller.product;

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
import com.web.app.service.OrderInfoService;
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
		orderInfo.setId(UUID.randomUUID().toString());
		orderInfo.setOrderId(DateTools.getTimes());
		int count = orderInfoService.insertOrderInfo(orderInfo);
		if(count > 0){
			jsonObj.put("status", "200");
			jsonObj.put("return", "success");
		}else{
			jsonObj.put("status", "500");
			jsonObj.put("return", "error");
		}
		return jsonObj;
	}
}


