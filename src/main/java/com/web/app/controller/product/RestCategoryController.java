package com.web.app.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.web.app.controller.BaseController;
import com.web.app.entity.Category;
import com.web.app.service.CategoryService;

/**
 * @Title:RestCategoryController     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月20日 下午5:20:00       
 * @version V1.0
 */
@RestController
@RequestMapping("/categorys")
public class RestCategoryController extends BaseController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/getAllCategorys")
	public JSONObject getAllCategorys(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObj = new JSONObject();
		List<Category> categorytList = categoryService.selectCategoryParent();
		if(categorytList.size() > 0){
			for (int i = 0; i < categorytList.size(); i++) {
				List<Category> cates = categoryService.selectCategoryByParentId(categorytList.get(i).getId());
				categorytList.get(i).setCategory(cates);
			}
			jsonObj.put("Categorys",JSONObject.toJSON(categorytList));
		}
		return jsonObj;
	}
	
	
	@RequestMapping("/getCategorysByName")
	public JSONObject getCategorysByName(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObj = new JSONObject();
		String categoryName = request.getParameter("categoryName");
		if(null != categoryName && !categoryName.equals("")){
			Category category = categoryService.selectCategoryByName(categoryName);
			if(null !=category.getId() && !category.getId().equals("")){
				List<Category> cates = categoryService.selectCategoryByParentId(category.getId());
				category.setCategory(cates);
				jsonObj.put("Categorys",JSONObject.toJSON(category));
			}
		}else{
			jsonObj.put("message", "category name not be null");
		}
		return jsonObj;
	}
}


