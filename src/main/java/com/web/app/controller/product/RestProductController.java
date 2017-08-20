package com.web.app.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.web.app.controller.BaseController;
import com.web.app.entity.Pictures;
import com.web.app.entity.Product;
import com.web.app.service.PicturesService;
import com.web.app.service.ProductService;

/**
 * @Title:CategoryController     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月8日 下午10:43:26       
 * @version V1.0
 */
@RestController
@RequestMapping("/products")
public class RestProductController extends BaseController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private PicturesService picturesService;
	
	/**
	 * @Title: getAllproduct
	 * @Description:
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @retrun JSONObject
	 * @author LiangRui
	 * @throws
	 * @Time 2017年8月14日 下午10:29:51
	 */
	@RequestMapping("/getAllProducts")
	public JSONObject getAllproduct(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String orderBy = request.getParameter("OrderBy");
		String size = request.getParameter("Size");
		JSONObject jsonObj = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != orderBy && !orderBy.equals("")){
			if(orderBy.endsWith("createDate")){
				map.put("createDate", "createDate");
			}else if(orderBy.endsWith("isHot")){
				map.put("isHot", "isHot");
			}else if(orderBy.endsWith("sellCount")){
				map.put("sellCount", "sellCount");
			}
		}
		if(null != size && !size.equals("")){
			map.put("startIndex", 0);
			map.put("endIndex", size);
		}
		List<Product> productList = productService.getAllProduct(map);
		for (int i = 0; i < productList.size(); i++) {
			List<Pictures> pic = picturesService.selectPicturesByProductId(productList.get(i).getId());
			productList.get(i).setPictures(pic);
		}
		jsonObj.put("products",JSONObject.toJSON(productList));
		return jsonObj;
	}
	
	
	@RequestMapping("/getProductsDetail")
	public JSONObject getProductsDetail(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String proId = request.getParameter("productId");
		JSONObject jsonObj = new JSONObject();
		if(null != proId && !proId.equals("")){
			Product productDetail = productService.getProductById(proId);
			if(null != productDetail && !productDetail.equals("")){
				List<Pictures> pic = picturesService.selectPicturesByProductId(productDetail.getId());
				productDetail.setPictures(pic);
			}
			jsonObj.put("products",JSONObject.toJSON(productDetail));
		}else{
			jsonObj.put("message", "product id  not be null");
		}
		return jsonObj;
	}
}


