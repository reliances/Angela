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
public class ProductRestController extends BaseController {
	
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
	@RequestMapping("/getAllproducts")
	public JSONObject getAllproduct(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObj = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		List<Product> productList = productService.getAllProduct(map);
		for (int i = 0; i < productList.size(); i++) {
			List<Pictures> pic = picturesService.selectPicturesByProductId(productList.get(i).getId());
			productList.get(i).setPictures(pic);
		}
		jsonObj.put("products",JSONObject.toJSON(productList));
		return jsonObj;
	}
}


