package com.web.app.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.app.controller.BaseController;
import com.web.app.entity.SystemLog;
import com.web.app.service.SystemLogService;
import com.web.app.tools.Constant;
import com.web.app.tools.Pager;

/**
 * @Title:SystemLogController     
 * @Description: 用户操作日志
 * @Auth:LiangRui   
 * @CreateTime:2017年3月9日 上午9:38:02       
 * @version V1.0
 */
@Controller
@RequestMapping("/syslog")
public class SystemLogController extends BaseController {
	@Autowired
	private SystemLogService systemLogService;// 注入Service
	
	 
	//查询所有日志
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getAllSysLog")
	public String getAllSysLog(Model model,SystemLog sysLog, @RequestParam(required=false) Integer pageNum, 
			@RequestParam(required=false) Integer pageSize,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
		map.put("userName", sysLog.getUserName());
		map.put("ipAddress", sysLog.getIpAddress());
		map.put("operationType", sysLog.getOperationType());
		map.put("logComment", sysLog.getLogComment());
		if(!sysLog.getCreateDate().equals("")){
			map.put("createDate", sysLog.getCreateDate());
		}
		if(!sysLog.getCreateDate2().equals("")){
			map.put("createDate2", sysLog.getCreateDate2());
		}
		//总页数
	    int totalCount = systemLogService.getSysLogCount(map);
	    this.initPage(map, pageNum, pageSize, totalCount);
	    map.put("startIndex", (pageNum-1) * pageSize);
		map.put("endIndex", pageSize);
		//数据
		List logList = systemLogService.getAllSysLog(map);
		this.initResult(model, logList, map);
		request.getSession().removeAttribute("sub");
		request.setAttribute("sysLog",sysLog);
		if(request.getParameter("sub") == null){
			request.setAttribute("sub", Constant.MENU_LEVEL_THREE);
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "system/system_log";
	}
}

































