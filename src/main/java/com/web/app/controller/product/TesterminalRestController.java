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
import com.web.app.entity.Testerminal;
import com.web.app.service.TesterMinalService;
import com.web.app.tools.DateTools;

/**
 * @Title:TesterminalRestController     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年9月2日 下午9:23:34       
 * @version V1.0
 */
@RestController
@RequestMapping("/testerminals")
public class TesterminalRestController extends BaseController {
	@Autowired
	private TesterMinalService testerMinalService;
	
	@RequestMapping("/postAddTesterMinals")
	public JSONObject postAddTesterMinals(@RequestBody JSONObject jsObj,HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonObj = new JSONObject();
		Testerminal testerminal = (Testerminal) JSON.parseObject(jsObj.toString(), Testerminal.class);
		testerminal.setId(UUID.randomUUID().toString());
		testerminal.setCreateTime(DateTools.getCurrentTime());
		int count = testerMinalService.insertTesterminal(testerminal);
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
