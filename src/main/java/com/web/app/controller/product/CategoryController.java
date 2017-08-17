package com.web.app.controller.product;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.app.controller.BaseController;
import com.web.app.entity.Category;
import com.web.app.service.CategoryService;
import com.web.app.tools.Pager;

/**
 * @Title:CategoryController     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月8日 下午10:43:26       
 * @version V1.0
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
	
	@Autowired
	private CategoryService categoryService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllCategory")
	public String getAllCategory(Model model, @RequestParam(required=false) Integer pageNum, 
			@RequestParam(required=false) Integer pageSize, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
		int totalCount = categoryService.countByCriteria();
	    this.initPage(map, pageNum, pageSize, totalCount);
	    map.put("startIndex", (pageNum-1) * pageSize);
		map.put("endIndex", pageSize);
		@SuppressWarnings("rawtypes")
		List cateList = categoryService.getAllCategory(map);
		this.initResult(model, cateList, map);
		if(request.getParameter("sub") == null){
			request.setAttribute("sub", 2);
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "product/category_manage";
	}
	
	//添加商品类别
	@RequestMapping("/addCategory")
	public String addCategory(Model model, Category category,HttpServletRequest request) {
		category.setId(UUID.randomUUID().toString());
		category.setStatus(0);
		category.setIsDelete(0);
		categoryService.insertCategory(category);	
		try {
			Log("新增操作", "新增一条名为"+category.getCatName()+"的商品类别", request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/category/getAllCategory";
	}
	
	
	//根据id删除
	@RequestMapping("/deleteCategoryById")
	public String deleteCategoryById(HttpServletRequest request,HttpServletResponse response) {
		String dicId = request.getParameter("dicId");
		String[] dicIds = dicId.split(",");
		categoryService.deleteCategoryById(dicIds);
		try {
			Log("删除操作", "删除分类,ID为"+dicId, request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/category/getAllCategory";
	}
	
}


