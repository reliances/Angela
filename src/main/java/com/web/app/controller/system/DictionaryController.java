package com.web.app.controller.system;

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
import com.web.app.entity.Dictionary;
import com.web.app.service.DictionaryService;
import com.web.app.tools.DateTools;
import com.web.app.tools.Pager;

/**
 * @Title:DictionaryController     
 * @Description: 数据字典
 * @Auth:LiangRui   
 * @CreateTime:2017年3月9日 下午3:44:32       
 * @version V1.0
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController {
	
	@Autowired
	private DictionaryService dictionaryService;

	//查询所有字典
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getAllDictionary")
	public String getAllDictionary(Model model, @RequestParam(required=false) Integer pageNum, 
			@RequestParam(required=false) Integer pageSize, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
	    int totalCount = dictionaryService.countByCriteria();
	    this.initPage(map, pageNum, pageSize, totalCount);
	    map.put("startIndex", (pageNum-1) * pageSize);
		map.put("endIndex", pageSize);
		List dictionaryList = dictionaryService.getAllDictionary(map);
		this.initResult(model, dictionaryList, map);
		if(request.getParameter("sub") == null){
			request.setAttribute("sub", 1);
		}
		try {
			Log("查询操作", "获取所有数据字典信息.", request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "system/dictionary_manage";
	}
	
	//添加字典
	@RequestMapping("/addDictionary")
	public String addDictionary(Model model, Dictionary dictionary,HttpServletRequest request) {
		dictionary.setDicId(UUID.randomUUID().toString());
		dictionary.setCreateDate(DateTools.getCurrentTime());
		dictionaryService.insertDictionary(dictionary);	
		try {
			Log("新增操作", "新增一条名为"+dictionary.getDicName()+"键为"+dictionary.getDicKey()+"的数据字典.", request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/dictionary/getAllDictionary";
	}
	
	//根据id删除字典
	@RequestMapping("/deleteDictionaryById")
	public String deleteDictionaryById(HttpServletRequest request,HttpServletResponse response) {
		String dicId = request.getParameter("dicId");
		String[] dicIds = dicId.split(",");
		dictionaryService.deleteDictionaryById(dicIds);
		try {
			Log("删除操作", "删除数据字典,ID为"+dicId, request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/dictionary/getAllDictionary";
	}
	
	//编辑字典
	@RequestMapping("/updateDictionaryById")
	public String updateDictionaryById(Dictionary dictionary,HttpServletRequest request) {
		dictionaryService.updateDictionaryById(dictionary);
		try {
			Log("修改操作", "修改数据字典"+dictionary.getDicName(), request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/dictionary/getAllDictionary";
	}
}
