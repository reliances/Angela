package com.web.app.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.web.app.entity.SystemLog;
import com.web.app.entity.User;
import com.web.app.service.SystemLogService;
import com.web.app.tools.Pager;

public class BaseController {
	@Autowired
	private SystemLogService systemLogService;
	
	//初始化分页相关信息
	protected void initPage(Map<String,Object> map, Integer pageNum, Integer pageSize, Integer totalCount){
		if(null==pageSize || pageSize.equals("")){
			pageSize = 5;
		}
		if(pageSize>50){
			pageSize = 50;
		}
		Integer totalPage = (totalCount+pageSize-1)/pageSize;
		if(null==pageNum){
			pageNum = 1;
		}else if(pageNum>totalPage){
			pageNum = totalPage;
		}
		map.put("startIndex", Pager.getStartIndex(pageNum, pageSize));
		map.put("pageNum", pageNum);
		map.put("totalPage", totalPage);
		map.put("pageSize", pageSize);
		map.put("totalCount", totalCount);
	}
	
	//将相关数据放入model
	@SuppressWarnings("rawtypes")
	protected void initResult(Model model, List<Object> list, Map<String,Object> map){
		model.addAttribute("list", list);
		Iterator it = map.entrySet().iterator(); 
		while(it.hasNext()){ 
			Map.Entry m = (Map.Entry)it.next(); 
			model.addAttribute(m.getKey().toString(), m.getValue());
	   } 
	}
	
	//添加系统日志
	protected void Log(String orgType,String orgDes,HttpServletRequest request) throws UnknownHostException{
		InetAddress addr = InetAddress.getLocalHost();
		SystemLog log = new SystemLog();
		User use = (User) request.getSession().getAttribute("user");
		log.setUuid(UUID.randomUUID().toString());
		log.setCreateDate(new Date());
		log.setIpAddress(addr.getHostAddress().toString());
		log.setOperationType(orgType);
		log.setLogComment(orgDes);
		if(use != null){
			log.setUserId(use.getUuid());
		}else{
			log.setUserId("");
		}
		systemLogService.insertLog(log);
	}
	
}
