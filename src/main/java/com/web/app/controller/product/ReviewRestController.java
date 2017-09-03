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
import com.web.app.entity.Review;
import com.web.app.service.ReviewService;
import com.web.app.tools.DateTools;

/**
 * @Title:ReviewController
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月18日 下午8:25:40
 * @version V1.0
 */
@RestController
@RequestMapping("/reviews")
public class ReviewRestController extends BaseController {
	@Autowired
	private ReviewService reviewService;

	@RequestMapping("/postAddReviews")
	public JSONObject postAddReviews(@RequestBody JSONObject jsObj,HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObj = new JSONObject();
		Review review = (Review) JSON.parseObject(jsObj.toString(), Review.class);
		review.setId(UUID.randomUUID().toString());
		review.setCreateDate(DateTools.getCurrentTime());
		int count = reviewService.insertReview(review);
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
