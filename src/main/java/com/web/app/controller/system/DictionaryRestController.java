package com.web.app.controller.system;

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
import com.web.app.entity.Dictionary;
import com.web.app.service.DictionaryService;

/**
 * @Title:DictionaryController     
 * @Description: 数据字典
 * @Auth:LiangRui   
 * @CreateTime:2017年3月9日 下午3:44:32       
 * @version V1.0
 */
@RestController
@RequestMapping("/dictionarys")
public class DictionaryRestController extends BaseController {
	
	@Autowired
	private DictionaryService dictionaryService;

	//查询所有字典
	@RequestMapping("/getAllDictionarys")
	public JSONObject getAllDictionarys(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObj = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		List<Dictionary> dictionaryList = dictionaryService.getAllDictionary(map);
		if(dictionaryList.size() > 0){
			jsonObj.put("Dictionary",JSONObject.toJSON(dictionaryList));
		}
		return jsonObj;
	}
	
	 
}
