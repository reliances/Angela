package com.web.app.controller.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.web.app.controller.BaseController;
import com.web.app.entity.Dictionary;
import com.web.app.entity.Pictures;
import com.web.app.entity.Product;
import com.web.app.entity.Review;
import com.web.app.service.DictionaryService;
import com.web.app.service.PicturesService;
import com.web.app.service.ProductService;
import com.web.app.service.ReviewService;

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
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private DictionaryService dictionaryService;
	
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
			}else if(orderBy.endsWith("priceUp")){
				map.put("priceUp", "priceUp");
			}else if(orderBy.endsWith("priceDown")){
				map.put("priceDown", "priceDown");
			}
		}
		if(null != size && !size.equals("")){
			map.put("startIndex", 0);
			map.put("endIndex", size);
		}
		List<Product> productList = productService.getAllProduct(map);
		for (int i = 0; i < productList.size(); i++) {
			if(null != productList.get(i).getProTag() && !productList.get(i).getProTag().equals("")){
				String[] proTag = productList.get(i).getProTag().split(",");
				String dicName = dictionaryService.getDictionaryByIds(proTag);
				productList.get(i).setProTagName(dicName);
			}
			List<Pictures> pic = picturesService.selectPicturesByProductId(productList.get(i).getId());
			Dictionary dicColor = dictionaryService.selectDicById(productList.get(i).getProductColor());
			if(null != dicColor){
				productList.get(i).setProductColor(dicColor.getDicName());
			}
			productList.get(i).setPictures(pic);
		}
		jsonObj.put("products",JSONObject.toJSON(productList));
		return jsonObj;
	}
	
	
	@RequestMapping("/getAllProductsByParms")
	public JSONObject getAllProductsByParms(@RequestBody JSONObject jsObj, HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String size = request.getParameter("Size");
		JSONObject jsonObj = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != jsObj.get("categoryId") && !jsObj.get("categoryId").equals("")){
			map.put("categoryId", jsObj.get("categoryId"));
		}
		if(null != jsObj.get("productColor") && !jsObj.get("productColor").equals("")){
			map.put("productColor", jsObj.get("productColor"));
		}
		if(null != jsObj.get("material") && !jsObj.get("material").equals("")){
			map.put("material", jsObj.get("material"));
		}
		if(null != jsObj.get("orderBy") && !jsObj.get("orderBy").equals("")){
			if(jsObj.get("orderBy").equals("priceUp")){
				map.put("priceUp", "priceUp");
			}else{
				map.put("priceDown", "priceDown");
			}
		}
		if(null != size && !size.equals("")){
			map.put("startIndex", 0);
			map.put("endIndex", size);
		}
		List<Product> productList = productService.getAllProduct(map);
		for (int i = 0; i < productList.size(); i++) {
			if(null != productList.get(i).getProTag() && !productList.get(i).getProTag().equals("")){
				String[] proTag = productList.get(i).getProTag().split(",");
				String dicName = dictionaryService.getDictionaryByIds(proTag);
				productList.get(i).setProTagName(dicName);
			}
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
		List<Review> review = reviewService.getReviewByProductId(proId);
		int total = 0;
		int count = review.size();
		BigDecimal avg = null;
		for (int i = 0; i < review.size(); i++) {
			total= total + review.get(i).getSatisfaction()
						 + review.get(i).getProductQuality()
						 + review.get(i).getResponsiveness()
						 + review.get(i).getDelivery()
						 + review.get(i).getProblemResolution()
						 + review.get(i).getImprinting();
		}
		if(review.size() > 0){
			BigDecimal avgDcm = new BigDecimal(total+"");
			BigDecimal one = new BigDecimal(count * 6);   
			avg = avgDcm.divide(one,1,BigDecimal.ROUND_HALF_UP);
		}
		JSONObject jsonObj = new JSONObject();
		if(null != proId && !proId.equals("")){
			Product productDetail = productService.getProductById(proId);
			productDetail.setReviews(review);
			if(null != avg){
				productDetail.setAvgScore(avg.doubleValue());
				productDetail.setTotalReview(count);
			}
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
	
	@RequestMapping("/getAdPictures")
	public JSONObject getAdPicture(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObj = new JSONObject();
		List<Pictures> pic = picturesService.selectPicturesByType("3");
		jsonObj.put("products",JSONObject.toJSON(pic));
		return jsonObj;
	}
	
	
}


