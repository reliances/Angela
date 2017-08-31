package com.web.app.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.app.controller.BaseController;
import com.web.app.entity.JhiUser;
import com.web.app.entity.OrderInfo;
import com.web.app.entity.OrderProduct;
import com.web.app.entity.Pictures;
import com.web.app.entity.Product;
import com.web.app.service.JhiUserService;
import com.web.app.service.OrderInfoService;
import com.web.app.service.OrderProductService;
import com.web.app.service.PicturesService;
import com.web.app.service.ProductService;
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
	@Autowired
	private JhiUserService jhiUserService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PicturesService pictureService;

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
		for (int i = 0; i < caseList.size(); i++) {
			OrderInfo order = (OrderInfo) caseList.get(i);
			if(null != order.getUserId() && !order.getUserId().equals("")){
				JhiUser jUser = jhiUserService.getJhiuserInfo(order.getUserId());
				if(null != jUser){
					if(null != jUser.getCompanyName()){
						order.setCompanyName(jUser.getCompanyName());
					}
					if(null != jUser.getFirstName()){
						order.setCustomName(jUser.getFirstName());
					}
					if(null != jUser.getEmail()){
						order.setEmail(jUser.getEmail());
					}
					if(null != jUser.getTelNumber()){
						order.setPhoneNumber(jUser.getTelNumber());
					}
					if(null != jUser.getAsiSageNumber()){
						order.setAsi(jUser.getAsiSageNumber());
					}
					if(null != jUser.getFaxNumber()){
						order.setFax(jUser.getFaxNumber());
					}
				}
			}
		}
		this.initResult(model, caseList, map);
		if (request.getParameter("sub") == null) {
			request.setAttribute("sub", 2);
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "product/order_manage";
	}

	
	// 到订单详情
	@RequestMapping("/getOrderDetail")
	public String getOrderDetail(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		OrderInfo order = orderInfoService.getOrderInfoById(orderId);
		if(null != order.getUserId() && !order.getUserId().equals("")){
			JhiUser jUser = jhiUserService.getJhiuserInfo(order.getUserId());
			if(null != jUser){
				if(null != jUser.getCompanyName()){
					order.setCompanyName(jUser.getCompanyName());
				}
				if(null != jUser.getFirstName()){
					order.setCustomName(jUser.getFirstName());
				}
				if(null != jUser.getEmail()){
					order.setEmail(jUser.getEmail());
				}
				if(null != jUser.getTelNumber()){
					order.setPhoneNumber(jUser.getTelNumber());
				}
				if(null != jUser.getAsiSageNumber()){
					order.setAsi(jUser.getAsiSageNumber());
				}
				if(null != jUser.getFaxNumber()){
					order.setFax(jUser.getFaxNumber());
				}
			}
		}
		List<OrderProduct> orderProduct = orderProductService.getOrderProductById(order.getId());
		for (int i = 0; i < orderProduct.size(); i++) {
			Product product = productService.getProductById(orderProduct.get(i).getProductId());
			List<Pictures> pic = pictureService.selectPicturesByProductId(product.getId());
			product.setPictures(pic);
			orderProduct.get(i).setProduct(product);
		}
		if(null != orderProduct){
			order.setOrderProduct(orderProduct);
		}
		request.setAttribute("orderInfo", order);
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "product/order_detail";
	}

	
	// 添加订单
	/*@RequestMapping("/addOrder")
	public String addOrder(OrderInfo orderInfo, HttpServletRequest request) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		orderInfo.setId(UUID.randomUUID().toString());
		orderInfo.setUserId(user.getUserId());
		orderInfo.setCreateDate(DateTools.getCurrentTime());
		orderInfoService.insertOrderInfo(orderInfo);
		try {
			Log("新增操作", "新增一条名为" + orderInfo.getId() + "的订单", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/order/getAllOrder";
	}*/
	
	
	// 删除操作
	@RequestMapping("/deleteOrder")
	public String deleteOrder(Model model, HttpServletRequest request) {
		String ids = request.getParameter("Ids");
		String[] id = ids.split(",");
		orderInfoService.deleteOrderInfoById(id);
		return "redirect:/order/getAllOrder";
	}
	
	// 到订单添加页面
	/*@RequestMapping("/toAddOrderPage")
	public String toAddOrderPage(Model model, HttpServletRequest request) {
		return "product/order_add";
	}*/
	
	// 修改订单
	@RequestMapping("/updateOrder")
	public String updateOrder(Model model, OrderInfo orderInfo, HttpServletRequest request) {
		orderInfoService.updateOrderInfoById(orderInfo);
		try {
			Log("修改操作", "修改一条名为" + orderInfo.getId() + "的订单", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/order/getAllOrder";
	}
}
