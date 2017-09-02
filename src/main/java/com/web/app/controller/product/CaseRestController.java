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
import com.web.app.entity.CaseInfo;
import com.web.app.entity.Pictures;
import com.web.app.service.CaseInfoService;
import com.web.app.service.PicturesService;

/**
 * @Title:CaseController     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月15日 下午8:25:40       
 * @version V1.0
 */
@RestController
@RequestMapping("/cases")
public class CaseRestController extends BaseController {
	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private PicturesService picturesService;
	
	//查询案例
	@RequestMapping("/getAllCases")
	public JSONObject getAllCase(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String size = request.getParameter("Size");
		JSONObject jsonObj = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != size && !size.equals("")){
			map.put("startIndex", 0);
			map.put("endIndex", size);
		}
		List<CaseInfo> casetList = caseInfoService.getAllCaseInfo(map);
		for (int i = 0; i < casetList.size(); i++) {
			List<Pictures> pic = picturesService.selectPicturesByProductId(casetList.get(i).getId());
			casetList.get(i).setPictures(pic);
		}
		jsonObj.put("cases",JSONObject.toJSON(casetList));
		return jsonObj;
	}
	
	
	//查询案例
	@RequestMapping("/getCasesById")
	public JSONObject getCasesById(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String caseId = request.getParameter("caseId");
		JSONObject jsonObj = new JSONObject();
		if(null != caseId && !caseId.equals("")){
			CaseInfo caseDetail = caseInfoService.getCaseInfoById(caseId);
			if(null != caseDetail && !caseDetail.equals("")){
				List<Pictures> pic = picturesService.selectPicturesByProductId(caseDetail.getId());
				caseDetail.setPictures(pic);
			}
			jsonObj.put("products",JSONObject.toJSON(caseDetail));
		}else{
			jsonObj.put("message", "case id  not be null");
		}
		return jsonObj;
	}
}


