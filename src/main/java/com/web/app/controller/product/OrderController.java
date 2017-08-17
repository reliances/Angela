package com.web.app.controller.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.app.controller.BaseController;
import com.web.app.entity.OrderInfo;
import com.web.app.entity.User;
import com.web.app.service.OrderInfoService;
import com.web.app.tools.DateTools;
import com.web.app.tools.Pager;

/**
 * @Title:OrderController
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月17日 下午16:11:40
 * @version V1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderInfoService orderInfoService;

	// 查询订单
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllOrder")
	public String getAllOrder(Model model, @RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
		int totalCount = orderInfoService.countByCriteria();
		this.initPage(map, pageNum, pageSize, totalCount);
		map.put("startIndex", (pageNum - 1) * pageSize);
		map.put("endIndex", pageSize);
		@SuppressWarnings("rawtypes")
		List caseList = orderInfoService.getAllOrderInfo(map);
		this.initResult(model, caseList, map);
		if (request.getParameter("sub") == null) {
			request.setAttribute("sub", 2);
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "product/order_manage";
	}

	// 添加订单
	@RequestMapping("/addOrder")
	public String addOrder(Model model, OrderInfo orderInfo, HttpServletRequest request) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		orderInfo.setId(UUID.randomUUID().toString());
		orderInfo.setCreateUser(user.getUserId());
		orderInfo.setCreateDate(DateTools.getCurrentTime());
		orderInfoService.insertOrderInfo(orderInfo);
		try {
			Log("新增操作", "新增一条名为" + orderInfo.getOrderId() + "的订单", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/order/getAllOrder";
	}

	// 到订单添加页面
	@RequestMapping("/toAddOrderPage")
	public String toAddOrderPage(Model model, HttpServletRequest request) {
		return "product/order_add";
	}

	// 到订单编辑页面
	@RequestMapping("/toEditOrderPage")
	public String toEditOrderPage(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		OrderInfo orderInfo = orderInfoService.getOrderInfoById(id);
		request.setAttribute("orderInfo", orderInfo);
		return "product/order_edit";
	}

	// 删除操作
	@RequestMapping("/deleteOrder")
	public String deleteOrder(Model model, HttpServletRequest request) {
		String ids = request.getParameter("Ids");
		String[] id = ids.split(",");
		orderInfoService.deleteOrderInfoById(id);
		return "redirect:/order/getAllOrder";
	}

	// 修改订单
	@RequestMapping("/updateOrder")
	public String updateOrder(Model model, OrderInfo orderInfo, HttpServletRequest request) {
		orderInfoService.updateOrderInfoById(orderInfo);
		try {
			Log("修改操作", "修改一条名为" + orderInfo.getOrderId() + "的订单", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/order/getAllOrder";
	}
}
